package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.Department;
import com.zhongzhou.common.base.BaseService;

import java.util.List;

/**
 * <p>
 * 服务类--部门
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface IDepartmentService extends BaseService<Department> {
    /**
     * 初始化列表
     */
    void initList();

    /**
     * 查询用户部门信息
     *
     * @param sysUserId 用户ID
     * @return 部门详情
     */
    Department findBySysUserId(Long sysUserId);

    /**
     * 获取部门树
     *
     * @return 部门列表
     */
    List<Department> findDepartmentTree();
}
