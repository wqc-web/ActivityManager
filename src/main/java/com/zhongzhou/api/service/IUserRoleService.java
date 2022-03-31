package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.Role;
import com.zhongzhou.api.entity.UserRole;
import com.zhongzhou.common.base.BaseService;

/**
 * <p>
 * 服务类--用户和角色关联关系
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
public interface IUserRoleService extends BaseService<UserRole> {
    /**
     * 删除用户和角色关联关系
     *
     * @param sysUserId 用户ID
     * @return true成功，false失败
     */
    Boolean deleteBySysUserId(Long sysUserId);

    /**
     * 删除用户和角色关联关系
     *
     * @param roleId 角色ID
     * @return true成功，false失败
     */
    Boolean deleteByRoleId(Long roleId);

}
