package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Permission;
import com.zhongzhou.api.service.impl.PermissionServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.base.Pager;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器--权限菜单
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/permission")
@Slf4j
public class PermissionController extends BaseController {
    private static final long serialVersionUID = -1736520491667891502L;
    @Resource
    private PermissionServiceImpl permissionService;

    /**
     * 分页查询列表
     *
     * @param pager      分页信息
     * @param permission 权限菜单实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<Permission> pager, Permission permission,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Permission> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(permission.getName()), "name", permission.getName());
            wrapper.eq(StringUtils.isNotBlank(permission.getCode()), "code", permission.getCode());
            wrapper.eq(null != permission.getParentId(), "parent_id", permission.getParentId());
            wrapper.eq(null != permission.getType(), "type", permission.getType());
            List<Permission> records = permissionService.page(pager, wrapper).getRecords();
            if (null != records && records.size() > 0){
                records.forEach(per->{
                    if (!Constants.NUM_ZERO.equals(per.getParentId())) {
                        per.setParentName(Constants.MAP_PERMISSION.get(per.getParentId()));
                    }
                });
            }
            int count = permissionService.count(wrapper);
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
     * @param permission 权限菜单实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(Permission permission,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Permission> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(permission.getName()), "name", permission.getName());
            wrapper.eq(StringUtils.isNotBlank(permission.getCode()), "code", permission.getCode());
            wrapper.eq(null != permission.getParentId(), "parent_id", permission.getParentId());
            wrapper.eq(null != permission.getType(), "type", permission.getType());
            List<Permission> list = permissionService.list(wrapper);
            if (null != list && list.size() > 0){
                list.forEach(per->{
                    if (!Constants.NUM_ZERO.equals(per.getParentId())) {
                        per.setParentName(Constants.MAP_PERMISSION.get(per.getParentId()));
                    }
                });
            }
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
            Permission permission = permissionService.getDetailById(id);
            if (null != permission) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, permission);
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
     * @param permission 权限菜单实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody Permission permission, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, permission);
        } else {
            try {
                QueryWrapper<Permission> wrapper = new QueryWrapper<>();
                wrapper.eq(StringUtils.isNotBlank(permission.getCode()), "code", permission.getCode());
                if (permissionService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, permission);
                } else {
                    if (permission.getType() == 0){
                        permission.setParentId(Constants.NUM_ZERO);
                    }
                    permission.setCreateTime(LocalDateTime.now());
                    permission.setCreateUserId(tokenController.getUserId(request, response));
                    if (permissionService.savePermission(permission)) {
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, permission);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, permission);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, permission);
            }
        }
    }

    /**
     * 修改
     *
     * @param id         主键
     * @param permission 权限菜单实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody Permission permission, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), permission);
        } else {
            try {
                if (null == permissionService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, permission);
                } else {
                    permission.setId(id);
                    if (permission.getType() == 0){
                        permission.setParentId(Constants.NUM_ZERO);
                    }
                    permission.setLastUpdateTime(LocalDateTime.now());
                    permission.setLastUpdateUserId(tokenController.getUserId(request, response));
                    if (permissionService.updatePermission(permission)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, permission);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, permission);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, permission);
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
            if (null == permissionService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (permissionService.removeById(id)) {
                    permissionService.initList();
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

    /**
     * 查询本人菜单列表
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ReturnEntity
     */
    @GetMapping("/findMenuListBySysUserId")
    public ReturnEntity findMenuListBySysUserId(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long userId = tokenController.getUserId(request, response);
            List<MenuVO> menuVOList = permissionService.findMenuListBySysUserId(userId);
            return new ReturnEntitySuccess("获取菜单列表成功", menuVOList.size(), menuVOList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[获取菜单列表失败]:{}", e.getMessage());
            return new ReturnEntityError("获取菜单列表失败");
        }
    }

    /**
     * 根据角色查询权限菜单列表
     *
     * @param roleId 角色ID
     * @return ReturnEntity
     */
    @GetMapping("/findInterfaceListByRoleIdWithChecked/{roleId}")
    public ReturnEntity findListByRoleId(@PathVariable("roleId") Long roleId) {
        try {
            List<Permission> permissionList = permissionService.findInterfaceListByRoleIdWithChecked(roleId);
            return new ReturnEntitySuccess("获取权限菜单列表成功", permissionList.size(), permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[获取菜单列表失败]:{}", e.getMessage());
            return new ReturnEntityError("获取权限菜单列表失败");
        }
    }

    /**
     * 查询权限菜单树
     *
     * @return ReturnEntity
     */
    @GetMapping("/findPermissionTree")
    public ReturnEntity findPermissionTree() {
        try {
            List<Permission> permissionTree = permissionService.findPermissionTree();
            if (null != permissionTree && permissionTree.size() > 0) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, permissionTree);
            } else {
                return new ReturnEntitySuccess(Constants.MSG_FIND_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:{}", e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED);
        }
    }
}
