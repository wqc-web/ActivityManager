package com.zhongzhou.api.mapper;

import com.zhongzhou.api.entity.Role;
import com.zhongzhou.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
public interface RoleMapper extends BaseDao<Role> {

    /**
     * 查询用户角色信息
     *
     * @param sysUserId 用户ID
     * @return 角色详情
     */
    Role findBySysUserId(@Param("sysUserId") Long sysUserId);
}
