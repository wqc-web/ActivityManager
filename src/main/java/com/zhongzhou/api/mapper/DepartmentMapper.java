package com.zhongzhou.api.mapper;

import com.zhongzhou.api.entity.Department;
import com.zhongzhou.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface DepartmentMapper extends BaseDao<Department> {

    /**
     * 查询用户部门信息
     *
     * @param sysUserId 用户ID
     * @return 部门详情
     */
    Department findBySysUserId(@Param("sysUserId") Long sysUserId);
}
