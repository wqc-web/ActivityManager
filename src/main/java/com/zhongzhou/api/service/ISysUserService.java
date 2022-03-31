package com.zhongzhou.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.common.base.BaseService;
import com.zhongzhou.common.base.Pager;

import java.util.List;

/**
 * <p>
 * 服务类--用户
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface ISysUserService extends BaseService<SysUser> {

    /**
     * 分页列表
     *
     * @param pager   分页参数
     * @param wrapper 查询条件
     * @return 用户列表
     */
    List<SysUser> pageSysUserList(Pager<SysUser> pager, QueryWrapper<SysUser> wrapper);

    /**
     * 详情
     *
     * @param id 用户ID
     * @return 用户详情
     */
    SysUser findDetailById(Long id);

    /**
     * 新增系统用户
     *
     * @param sysUser 系统用户实体类
     * @return true成功，false失败
     */
    boolean saveSysUser(SysUser sysUser);

    /**
     * 编辑系统用户信息
     *
     * @param sysUser 系统用户实体类
     * @return true成功，false失败
     */
    boolean updateUserById(SysUser sysUser);

    /**
     * 根据角色查询用户列表
     *
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<SysUser> listByRoleId(Long roleId);

    /**
     * 根据部门查询用户列表
     *
     * @param depId 部门ID
     * @return 用户列表
     */
    List<SysUser> listByDepId(Long depId);

    /**
     * 根据活动查询用户列表
     *
     * @param activityId 活动ID
     * @return 用户列表
     */
    List<SysUser> queryActivityUser(Long activityId);

    /**
     * 成员用户列表
     *
     * @param pager   分页
     * @param sysUser 用户
     * @return List<SysUser>
     */
    List<SysUser> userPage(Pager<SysUser> pager, SysUser sysUser);

    /**
     * 成员用户总数
     *
     * @param sysUser 用户
     * @return 总数
     */
    Integer userCount(SysUser sysUser);

}
