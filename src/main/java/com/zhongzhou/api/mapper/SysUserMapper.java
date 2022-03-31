package com.zhongzhou.api.mapper;

import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface SysUserMapper extends BaseDao<SysUser> {

    /**
     * 根据角色查询用户列表
     *
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<SysUser> listByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据部门查询用户列表
     *
     * @param depId 部门ID
     * @return 用户列表
     */
    List<SysUser> listBydepId(@Param("depId") Long depId);

    /**
     * 根据活动id查询关联用户
     *
     * @param activityId 活动id
     * @return 用户列表
     */
    List<SysUser> queryActivityUser(@Param("activityId") Long activityId);

    /**
     * 成员用户列表
     *
     * @param startPage  分页
     * @param size       用户
     * @param activityId 活动id
     * @param userName   用户名称
     * @return List<SysUser>
     */
    List<SysUser> userPage(@Param("startPage") long startPage, @Param("size") long size, @Param("userName") String userName, @Param("activityId") Long activityId);

    /**
     * 成员用户总数
     *
     * @param userName   用户名称
     * @param activityId 活动id
     * @return 总数
     */
    Integer userCount(@Param("userName") String userName, @Param("activityId") Long activityId);
}
