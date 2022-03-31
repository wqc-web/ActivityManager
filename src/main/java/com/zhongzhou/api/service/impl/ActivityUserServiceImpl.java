package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.ActivityUser;
import com.zhongzhou.api.mapper.ActivityUserMapper;
import com.zhongzhou.api.service.IActivityUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动用户 服务实现类
 * </p>
 *
 * @author wqc
 * @since 2021-05-24
 */
@Service
public class ActivityUserServiceImpl extends ServiceImpl<ActivityUserMapper, ActivityUser> implements IActivityUserService {

    @Override
    public boolean existActivityUser(Long activityId, Long userId) {
        QueryWrapper<ActivityUser> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id" , activityId);
        wrapper.eq("user_id" , userId);
        if(count(wrapper) > 0){
            return true;
        }else{
            return false;
        }
    }

}
