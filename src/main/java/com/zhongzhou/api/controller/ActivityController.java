package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Activity;
import com.zhongzhou.api.entity.ActivityUser;
import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.api.service.impl.ActivityServiceImpl;
import com.zhongzhou.api.service.impl.SysUserServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.base.Pager;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 活动
 * </p>
 *
 * @author wqc
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/api/activity")
@Slf4j
public class ActivityController extends BaseController {

    @Resource
    private ActivityServiceImpl activityService;
    @Resource
    private SysUserServiceImpl sysUserService;

    /**
     * 分页查询列表
     *
     * @param pager    分页信息
     * @param activity Activity
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<Activity> pager, Activity activity,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Activity> wrapper = new QueryWrapper<>();
            wrapper.eq(activity.getStatus() != null, "status", activity.getStatus());
            wrapper.like(StringUtils.isNotBlank(activity.getTitle()), "title", activity.getTitle());
            List<Activity> records = activityService.page(pager, wrapper).getRecords();
            int count = activityService.count(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, count, records);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 查询所有列表
     *
     * @param activity Activity
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(Activity activity,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Activity> wrapper = new QueryWrapper<>();
            wrapper.eq(activity.getStatus() != null, "status", activity.getStatus());
            wrapper.like(StringUtils.isNotBlank(activity.getTitle()), "title", activity.getTitle());
            List<Activity> list = activityService.list(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @GetMapping("/detail/{id}")
    public ReturnEntity selectById(@PathVariable("id") Long id) {
        try {
            Activity activity = activityService.getActivityDetail(id);
            if (null != activity) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, activity);
            } else {
                return new ReturnEntitySuccess(Constants.MSG_FIND_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_FIND_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED);
        }
    }

    /**
     * 新增
     *
     * @param activity Activity
     * @param result   BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody Activity activity, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, activity);
        } else {
            try {
                activity.setCreateTime(LocalDateTime.now());
                activity.setCreateUserId(tokenController.getUserId(ServletUtils.getRequest()));
                if (activityService.save(activity)) {
                    return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, activity);
                } else {
                    return new ReturnEntityError(Constants.MSG_INSERT_FAILED, activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, activity);
            }
        }
    }

    /**
     * 修改
     *
     * @param id       主键
     * @param activity Activity
     * @param result   BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody Activity activity, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), activity);
        } else {
            try {
                activity.setId(id);
                activity.setLastUpdateTime(LocalDateTime.now());
                activity.setLastUpdateUserId(tokenController.getUserId(ServletUtils.getRequest()));
                if (null == activityService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, activity);
                } else {
                    if (activityService.updateById(activity)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, activity);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, activity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, activity);
            }
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @DeleteMapping("/delete/{id}")
    public ReturnEntity deleteById(@PathVariable("id") Long id) {
        try {
            if (null == activityService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (activityService.removeById(id)) {
                    return new ReturnEntitySuccess(Constants.MSG_DELETE_SUCCESS, id);
                } else {
                    return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_DELETE_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
        }
    }

    /**
     * 发布
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @PutMapping("/publish/{id}")
    public ReturnEntity publish(@PathVariable("id") Long id,
                                HttpServletRequest request, HttpServletResponse response) {
        try {
            if (activityService.publish(id)) {
                return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, id);
            } else {
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, id);
        }
    }

    /**
     * 撤回
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @PutMapping("/recall/{id}")
    public ReturnEntity recall(@PathVariable("id") Long id,
                               HttpServletRequest request, HttpServletResponse response) {
        try {
            if (activityService.recall(id)) {
                return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, id);
            } else {
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, id);
        }
    }

    /**
     * 查询所有列表
     *
     * @param activity Activity
     * @return ReturnEntity
     */
    @GetMapping("/userList")
    public ReturnEntity userList(Activity activity,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
            //判断活动参数是否传递
            if (activity.getId() == null) {
                return new ReturnEntityError("活动id不能为空");
            }
            //查询活动关联的用户
            List<SysUser> sysUserList = sysUserService.queryActivityUser(activity.getId());
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, sysUserList.size(), sysUserList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 加入活动
     *
     * @param activityUser 活动与用户
     * @return ReturnEntity
     */
    @PostMapping("/joinActivity")
    public ReturnEntity joinActivity(@Validated @RequestBody ActivityUser activityUser,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            if (activityService.joinActivity(activityUser)) {
                return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, activityUser);
            } else {
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, activityUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
            return new ReturnEntityError(e.getMessage(), activityUser);
        }
    }

    /**
     * 最新的活动
     *
     * @return ReturnEntity
     */
    @GetMapping("/lastActivity")
    public ReturnEntity lastActivity(HttpServletRequest request, HttpServletResponse response) {
        try {
            //最新的活动
            Activity activity = activityService.lastActivity();
            //
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, activity);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

}
