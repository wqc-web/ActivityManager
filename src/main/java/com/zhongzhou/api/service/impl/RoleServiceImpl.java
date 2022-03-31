package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.entity.Role;
import com.zhongzhou.api.entity.RolePermission;
import com.zhongzhou.api.mapper.RoleMapper;
import com.zhongzhou.api.service.IRoleService;
import com.zhongzhou.common.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private static final long serialVersionUID = -8287686459989627322L;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionServiceImpl rolePermissionService;

    @Override
    public void initList() {
        List<Role> roleList = list();
        roleList.forEach(role -> {
            Constants.MAP_ROLE.put(role.getId(), role.getRoleName());
        });
    }

    @Override
    public Role findBySysUserId(Long sysUserId) {
        return roleMapper.findBySysUserId(sysUserId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void grant(Long id, List<Long> permissionIds) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", id);
        //删除原有关联关系
        rolePermissionService.remove(wrapper);
        //添加新的关联关系
        if (null != permissionIds && permissionIds.size() > 0) {
            List<RolePermission> rolePermissionList = new ArrayList<>();
            permissionIds.forEach(permissionId -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(id);
                rolePermission.setPerId(permissionId);
                rolePermissionList.add(rolePermission);
            });
            if (!rolePermissionService.saveBatch(rolePermissionList)) {
                throw new RuntimeException("角色和权限菜单关联关系保存失败");
            }
        }
    }
}
