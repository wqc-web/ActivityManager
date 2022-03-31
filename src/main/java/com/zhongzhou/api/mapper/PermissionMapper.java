package com.zhongzhou.api.mapper;

import com.zhongzhou.api.entity.Permission;
import com.zhongzhou.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口--权限菜单
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface PermissionMapper extends BaseDao<Permission> {

    /**
     * 查询权限菜单列表
     *
     * @param roleId   角色ID
     * @param parentId 父级ID
     * @param type     类型
     * @param level    级别
     * @return 权限菜单列表
     */
    List<Permission> findListByRoleId(@Param("roleId") Long roleId, @Param("parentId") Long parentId, @Param("type") Integer type, @Param("level") Integer level);

    /**
     * 根据用户ID查询菜单列表
     *
     * @param sysUserId 用户ID
     * @return 权限菜单列表
     */
    List<Permission> findListBySysUserId(@Param("sysUserId") Long sysUserId);

}
