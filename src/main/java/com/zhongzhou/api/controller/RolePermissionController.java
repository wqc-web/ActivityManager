package com.zhongzhou.api.controller;


import org.springframework.web.bind.annotation.*;
import com.zhongzhou.common.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.base.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.zhongzhou.api.service.impl.RolePermissionServiceImpl;
import com.zhongzhou.api.entity.RolePermission;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器--角色和权限菜单关联关系
 * </p>
 *
 * @author wj
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/api/rolePermission")
@Slf4j
public class RolePermissionController extends BaseController {
    private static final long serialVersionUID = -3037270593966432378L;
    @Resource
    private RolePermissionServiceImpl rolePermissionService;

    /**
     * 分页查询列表
     *
     * @param pager          分页信息
     * @param rolePermission 角色权限关系实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<RolePermission> pager, RolePermission rolePermission,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
            wrapper.eq(null != rolePermission.getRoleId(), "role_id", rolePermission.getRoleId());
            wrapper.eq(null != rolePermission.getPerId(), "per_id", rolePermission.getPerId());
            List<RolePermission> records = rolePermissionService.page(pager, wrapper).getRecords();
            int count = rolePermissionService.count(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, count, records);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 查询所有列表
     *
     * @param rolePermission 角色权限关系实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(RolePermission rolePermission,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
            wrapper.eq(null != rolePermission.getRoleId(), "role_id", rolePermission.getRoleId());
            wrapper.eq(null != rolePermission.getPerId(), "per_id", rolePermission.getPerId());
            List<RolePermission> list = rolePermissionService.list(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @GetMapping("/detail/{id}")
    public ReturnEntity selectById(@PathVariable("id") Long id) {
        try {
            RolePermission rolePermission = rolePermissionService.getById(id);
            if (null != rolePermission) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, rolePermission);
            } else {
                return new ReturnEntitySuccess(Constants.MSG_FIND_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_FIND_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED);
        }
    }

    /**
     * 新增
     *
     * @param rolePermission 角色权限关系实体类
     * @param result         BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody RolePermission rolePermission, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, rolePermission);
        } else {
            try {
                QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
                wrapper.eq("role_id", rolePermission.getRoleId());
                wrapper.eq("per_id", rolePermission.getPerId());
                if (rolePermissionService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, rolePermission);
                } else {
                    if (rolePermissionService.save(rolePermission)) {
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, rolePermission);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, rolePermission);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, rolePermission);
            }
        }
    }

    /**
     * 修改
     *
     * @param id             主键
     * @param rolePermission 角色权限关系实体类
     * @param result         BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody RolePermission rolePermission, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), rolePermission);
        } else {
            try {
                if (null == rolePermissionService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, rolePermission);
                } else {
                    rolePermission.setId(id);
                    if (rolePermissionService.updateById(rolePermission)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, rolePermission);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, rolePermission);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, rolePermission);
            }
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @DeleteMapping("/delete/{id}")
    public ReturnEntity deleteById(@PathVariable("id") Long id) {
        try {
            if (null == rolePermissionService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (rolePermissionService.removeById(id)) {
                    return new ReturnEntitySuccess(Constants.MSG_DELETE_SUCCESS, id);
                } else {
                    return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_DELETE_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
        }
    }

}
