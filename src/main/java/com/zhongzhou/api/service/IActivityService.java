package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.Activity;
import com.zhongzhou.api.entity.ActivityUser;
import com.zhongzhou.common.base.BaseService;

/**
 * <p>
 * 活动 服务类
 * </p>
 *
 * @author wqc
 * @since 2021-05-24
 */
public interface IActivityService extends BaseService<Activity> {

    /**
     * 发布
     *
     * @param id 活动id
     * @return true成功 false失败
     */
    boolean publish(Long id);

    /**
     * 撤回
     *
     * @param id 活动id
     * @return true成功 false失败
     */
    boolean recall(Long id);

    /**
     * 查询详情
     *
     * @param id 活动id
     * @return Activity
     */
    Activity getActivityDetail(Long id);

    /**
     * 加入活动
     *
     * @param activityUser 活动与用户
     * @return true成功 false失败
     */
    boolean joinActivity(ActivityUser activityUser);

    /**
     * 最新的活动
     *
     * @return 活动
     */
    Activity lastActivity();
}
