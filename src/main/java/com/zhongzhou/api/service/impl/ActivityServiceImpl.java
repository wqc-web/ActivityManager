package com.zhongzhou.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.common.TokenController;
import com.zhongzhou.api.entity.Activity;
import com.zhongzhou.api.entity.ActivityUser;
import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.api.mapper.ActivityMapper;
import com.zhongzhou.api.mapper.ActivityUserMapper;
import com.zhongzhou.api.mapper.SysUserMapper;
import com.zhongzhou.api.service.IActivityService;
import com.zhongzhou.api.service.WebSocketServer;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author wqc
 * @since 2021-05-24
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Resource
    protected TokenController tokenController;

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private ActivityUserMapper activityUserMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private ActivityUserServiceImpl activityUserService;
    @Resource
    WebSocketServer webSocketServer;


    @Override
    public boolean publish(Long id) {
        Activity activity = getById(id);
        if (activity != null) {
            //0未发布==>1已发布
            if (activity.getStatus() == Constants.STATUS_NO) {
                activity.setStatus(Constants.STATUS_YES);
                activity.setLastUpdateTime(LocalDateTime.now());
                activity.setLastUpdateUserId(tokenController.getUserId(ServletUtils.getRequest()));
                if (updateById(activity)) {
                    return true;
                } else {
                    return false;
                }
            } else { //已发布
                log.error("活动已发布:{}", id);
                throw new RuntimeException("活动已发布");
            }
        } else {
            log.error("活动不存在:{}", id);
            throw new RuntimeException("活动不存在");
        }
    }

    @Override
    public boolean recall(Long id) {
        Activity activity = getById(id);
        if (activity != null) {
            //1已发布==>0未发布
            if (activity.getStatus() == Constants.STATUS_YES) {
                activity.setStatus(Constants.STATUS_NO);
                activity.setLastUpdateTime(LocalDateTime.now());
                activity.setLastUpdateUserId(tokenController.getUserId(ServletUtils.getRequest()));
                if (updateById(activity)) {
                    return true;
                } else {
                    return false;
                }
            } else { //未发布
                log.error("活动未发布:{}", id);
                throw new RuntimeException("活动未发布");
            }
        } else {
            log.error("活动不存在:{}", id);
            throw new RuntimeException("活动不存在");
        }
    }

    @Override
    public Activity getActivityDetail(Long id) {
        Activity activity = getById(id);
        if (activity != null) {
            //查询活动关联的用户
            List<SysUser> sysUserList = sysUserMapper.queryActivityUser(id);
            activity.setActivityUserList(sysUserList);
        }
        return activity;
    }

    @Override
    public boolean joinActivity(ActivityUser activityUser) {
        //验证必传参数
        if (activityUser.getActivityId() == null || activityUser.getUserId() == null) {
            log.error("缺少传递参数:{}", activityUser);
            throw new RuntimeException("缺少传递参数");
        }
        //查询用户
        SysUser sysUser = sysUserMapper.selectById(activityUser.getUserId());
        //查询活动
        Activity activity = getById(activityUser.getActivityId());
        //验证用户是否存在
        if (sysUser == null) {
            log.error("用户不存在:{}", activityUser);
            throw new RuntimeException("用户不存在");
        }
        //验证活动是否存在
        if (activity == null) {
            log.error("活动不存在:{}", activityUser);
            throw new RuntimeException("活动不存在");
        }
        //验证活动是否发布
        //状态：0未发布，1已发布
        if(activity.getStatus() == Constants.STATUS_NO){
            log.error("活动未发布:{}", activityUser);
            throw new RuntimeException("活动未发布");
        }
        //验证时间是否过期
        LocalDateTime nowTime = LocalDateTime.now();
        //现在时间 在 开始时间之前
        if(nowTime.isBefore(activity.getStartTime())){
            log.error("活动未开始:{}", activityUser);
            throw new RuntimeException("活动未开始");
        }
        //现在时间 在 结束时间之后
        if(nowTime.isAfter(activity.getEndTime())){
            log.error("活动已结束:{}", activityUser);
            throw new RuntimeException("活动已结束");
        }
        //未加入->加入
        if (!activityUserService.existActivityUser(activityUser.getActivityId(), activityUser.getUserId())) {
            //添加用户加入活动
            activityUser.setCreateTime(LocalDateTime.now());
            if(activityUserService.save(activityUser)){
                //实体类转换成json格式
                String messageJson = JSON.toJSONString(sysUser);
                //通过socket发送数据-->群发消息
                webSocketServer.sendRegion(activityUser.getActivityId()+"",  messageJson);
            }else{
                log.error("添加用户加入活动失败:{}", activityUser);
                throw new RuntimeException("添加用户加入活动失败");
            }
        }
        return true;
    }

    @Override
    public Activity lastActivity() {
        //获取当前时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowTime = dtf.format(LocalDateTime.now());
        //condition
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        //<=
        wrapper.le("start_time", nowTime);
        //>=
        wrapper.ge("end_time", nowTime);
        //发布
        wrapper.eq("status", Constants.STATUS_YES);
        //开始时间降序
        wrapper.orderByDesc("start_time");
        //
        List<Activity> list = list(wrapper);
        //
        Activity activity = null;
        //存在活动
        if (list != null && list.size() > 0) {
            activity = list.get(0);
            //查询活动关联的用户
            List<SysUser> sysUserList = sysUserMapper.queryActivityUser(activity.getId());
            activity.setActivityUserList(sysUserList);
        }
        //return data
        return activity;
    }

}
