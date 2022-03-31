package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.Role;
import com.zhongzhou.common.base.BaseService;

import java.util.List;

/**
 * <p>
 * 服务类--角色
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
public interface IRoleService extends BaseService<Role> {
    /**
     * 初始化列表
     */
    void initList();

    /**
     * 查询用户的角色信息
     *
     * @param sysUserId 用户ID
     * @return 角色详情
     */
    Role findBySysUserId(Long sysUserId);

    /**
     * 授权
     *
     * @param id            角色ID
     * @param permissionIds 权限菜单ID集合
     */
    void grant(Long id, List<Long> permissionIds);

}
