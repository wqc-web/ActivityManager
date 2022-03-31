package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.entity.UserRole;
import com.zhongzhou.api.mapper.UserRoleMapper;
import com.zhongzhou.api.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类--用户和角色关联关系
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    private static final long serialVersionUID = 4102382553365835205L;

    @Override
    public Boolean deleteBySysUserId(Long sysUserId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", sysUserId);
        return remove(wrapper);
    }

    @Override
    public Boolean deleteByRoleId(Long roleId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return remove(wrapper);
    }
}
