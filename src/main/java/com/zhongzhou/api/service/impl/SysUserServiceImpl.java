package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.entity.*;
import com.zhongzhou.api.mapper.SysUserMapper;
import com.zhongzhou.api.service.ISysUserService;
import com.zhongzhou.common.base.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类--系统用户
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private static final long serialVersionUID = 5346581904328792647L;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    private DepartmentServiceImpl departmentService;
    @Resource
    private UserRoleServiceImpl userRoleService;
    @Resource
    private UserDepartmentServiceImpl userDepartmentService;

    @Override
    public List<SysUser> pageSysUserList(Pager<SysUser> pager, QueryWrapper<SysUser> wrapper) {
        List<SysUser> records = page(pager, wrapper).getRecords();
        if (null != records && records.size() > 0) {
            records.forEach(sysUser -> {
                Role role = roleService.findBySysUserId(sysUser.getId());
                if (null != role) {
                    sysUser.setRoleId(role.getId());
                    sysUser.setRoleName(role.getRoleName());
                }
                Department department = departmentService.findBySysUserId(sysUser.getId());
                if (null != department) {
                    sysUser.setDepId(department.getId());
                    sysUser.setDepName(department.getDepName());
                }
            });
        }
        return records;
    }

    @Override
    public SysUser findDetailById(Long id) {
        SysUser sysUser = getById(id);
        if (null != sysUser) {
            Role role = roleService.findBySysUserId(sysUser.getId());
            if (null != role) {
                sysUser.setRoleId(role.getId());
                sysUser.setRoleName(role.getRoleName());
            }
            Department department = departmentService.findBySysUserId(sysUser.getId());
            if (null != department) {
                sysUser.setDepId(department.getId());
                sysUser.setDepName(department.getDepName());
            }
        }
        return sysUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public boolean saveSysUser(SysUser sysUser) {
        try {
            if (save(sysUser)) {
                log.info("用户信息保存成功");
                if (null != sysUser.getRoleId()) {
                    //保存用户和角色关联关系
                    UserRole userRole = new UserRole();
                    userRole.setUserId(sysUser.getId());
                    userRole.setRoleId(sysUser.getRoleId());
                    if (!userRoleService.save(userRole)) {
                        log.error("用户角色保存失败");
                        throw new RuntimeException("用户角色保存失败");
                    }
                }
                if (null != sysUser.getDepId()) {
                    //保存用户和部门关联关系
                    UserDepartment userDepartment = new UserDepartment();
                    userDepartment.setUserId(sysUser.getId());
                    userDepartment.setDepId(sysUser.getDepId());
                    if (!userDepartmentService.save(userDepartment)) {
                        log.error("用户部门保存失败");
                        throw new RuntimeException("用户部门保存失败");
                    }
                }
                return true;
            } else {
                throw new RuntimeException("用户信息保存失败");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("用户添加失败:{}", e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public boolean updateUserById(SysUser sysUser) {
        if (updateById(sysUser)) {
            log.info("用户信息编辑成功");
            Long sysUserId = sysUser.getId();
            //删除原有用户与角色关联关系
            userRoleService.deleteBySysUserId(sysUserId);
            //删除原有用户与部门关联关系
            if (null != sysUser.getRoleId()) {
                //保存用户和角色关联关系
                UserRole userRole = new UserRole();
                userRole.setUserId(sysUserId);
                userRole.setRoleId(sysUser.getRoleId());
                if (!userRoleService.save(userRole)) {
                    log.error("用户角色保存失败");
                    throw new RuntimeException("用户角色保存失败");
                }
            }

            userDepartmentService.deleteBySysUserId(sysUserId);
            if (null != sysUser.getDepId()) {
                //保存用户和部门关联关系
                UserDepartment userDepartment = new UserDepartment();
                userDepartment.setUserId(sysUserId);
                userDepartment.setDepId(sysUser.getDepId());
                if (!userDepartmentService.save(userDepartment)) {
                    log.error("用户部门保存失败");
                    throw new RuntimeException("用户部门保存失败");
                }
            }
            return true;
        } else {
            throw new RuntimeException("用户信息编辑失败");
        }
    }


    @Override
    public List<SysUser> listByRoleId(Long roleId) {
        return sysUserMapper.listByRoleId(roleId);
    }

    @Override
    public List<SysUser> listByDepId(Long depId) {
        return sysUserMapper.listBydepId(depId);
    }

    @Override
    public List<SysUser> queryActivityUser(Long activityId) {
        return sysUserMapper.queryActivityUser(activityId);
    }

    @Override
    public List<SysUser> userPage(Pager<SysUser> pager, SysUser sysUser) {
        long current = pager.getCurrent();
        long size = pager.getSize();
        long startPage = (current - 1) * size;
        return sysUserMapper.userPage(startPage, size, sysUser.getUserName(), sysUser.getActivityId());
    }

    @Override
    public Integer userCount(SysUser sysUser) {
        return sysUserMapper.userCount(sysUser.getUserName(), sysUser.getActivityId());
    }


}
