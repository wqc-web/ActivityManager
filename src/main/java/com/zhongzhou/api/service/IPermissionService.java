package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.Permission;
import com.zhongzhou.common.base.BaseService;
import com.zhongzhou.common.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 服务类--权限菜单
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface IPermissionService extends BaseService<Permission> {
    /**
     * 初始化列表
     */
    void initList();

    /**
     * 根据用户ID查询权限菜单列表
     *
     * @param sysUserId 用户ID
     * @return 权限菜单列表
     */
    List<MenuVO> findMenuListBySysUserId(Long sysUserId);

    /**
     * 根据角色查询权限菜单列表
     *
     * @param roleId 角色ID
     * @return 权限菜单列表
     */
    List<Permission> findInterfaceListByRoleIdWithChecked(Long roleId);

    /**
     * 获取权限菜单树
     *
     * @return 权限菜单树
     */
    List<Permission> findPermissionTree();

    /**
     * 新增
     *
     * @param permission 权限菜单实体类
     * @return true成功，false失败
     */
    boolean savePermission(Permission permission);

    /**
     * 编辑
     *
     * @param permission 权限菜单实体类
     * @return true成功，false失败
     */
    boolean updatePermission(Permission permission);

    /**
     * 详情
     *
     * @param id 主键,权限菜单ID
     * @return 权限菜单实体类
     */
    Permission getDetailById(Long id);
}
