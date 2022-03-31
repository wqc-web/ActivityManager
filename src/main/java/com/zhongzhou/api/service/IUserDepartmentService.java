package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.UserDepartment;
import com.zhongzhou.common.base.BaseService;

/**
 * <p>
 * 服务类--用户和部门关联关系
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
public interface IUserDepartmentService extends BaseService<UserDepartment> {
    /**
     * 删除用户和部门关联关系
     *
     * @param sysUserId 用户ID
     * @return true成功，false失败
     */
    Boolean deleteBySysUserId(Long sysUserId);

    /**
     * 删除用户和部门关联关系
     *
     * @param depId 部门ID
     * @return true成功，false失败
     */
    Boolean deleteByDepId(Long depId);
}
