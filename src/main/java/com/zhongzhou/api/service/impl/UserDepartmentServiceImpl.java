package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.UserDepartment;
import com.zhongzhou.api.mapper.UserDepartmentMapper;
import com.zhongzhou.api.service.IUserDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类--用户和部门关联关系
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@Service
@Slf4j
public class UserDepartmentServiceImpl extends ServiceImpl<UserDepartmentMapper, UserDepartment> implements IUserDepartmentService {

    private static final long serialVersionUID = 3238152866955400295L;

    @Override
    public Boolean deleteBySysUserId(Long sysUserId) {
        QueryWrapper<UserDepartment> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", sysUserId);
        return remove(wrapper);
    }

    @Override
    public Boolean deleteByDepId(Long depId) {
        QueryWrapper<UserDepartment> wrapper = new QueryWrapper<>();
        wrapper.eq("dep_id", depId);
        return remove(wrapper);
    }
}
