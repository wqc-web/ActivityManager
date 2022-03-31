package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.ActivityUser;
import com.zhongzhou.common.base.BaseService;

/**
 * <p>
 * 活动用户 服务类
 * </p>
 *
 * @author wqc
 * @since 2021-05-24
 */
public interface IActivityUserService extends BaseService<ActivityUser> {

    /**
     * 用户是否存在该活动
     *
     * @param activityId 活动id
     * @param userId     用户id
     * @return true存在 false不存在
     */
    boolean existActivityUser(Long activityId, Long userId);

}
