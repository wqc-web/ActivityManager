package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Role;
import com.zhongzhou.api.service.impl.RoleServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.base.Pager;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
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
 * 前端控制器--角色
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController extends BaseController {
    private static final long serialVersionUID = -89336766494885147L;
    @Resource
    private RoleServiceImpl roleService;

    /**
     * 分页查询列表
     *
     * @param pager 分页信息
     * @param role  角色实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<Role> pager, Role role,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(role.getRoleName()), "role_name", role.getRoleName());
            List<Role> records = roleService.page(pager, wrapper).getRecords();
            int count = roleService.count(wrapper);
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
     * @param role 角色实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(Role role,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(role.getRoleName()), "role_name", role.getRoleName());
            List<Role> list = roleService.list(wrapper);
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
            Role role = roleService.getById(id);
            if (null != role) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, role);
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
     * @param role   角色实体类
     * @param result BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody Role role, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, role);
        } else {
            try {
                QueryWrapper<Role> wrapper = new QueryWrapper<>();
                wrapper.like("role_name", role.getRoleName());
                if (roleService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, role);
                } else {
                    role.setCreateTime(LocalDateTime.now());
                    role.setCreateUserId(tokenController.getUserId(request, response));
                    if (roleService.save(role)) {
                        roleService.initList();
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, role);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, role);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, role);
            }
        }
    }

    /**
     * 修改
     *
     * @param id     主键
     * @param role   角色实体类
     * @param result BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody Role role, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), role);
        } else {
            try {
                if (null == roleService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, role);
                } else {
                    role.setId(id);
                    role.setLastUpdateTime(LocalDateTime.now());
                    role.setLastUpdateUserId(tokenController.getUserId(request, response));
                    if (roleService.updateById(role)) {
                        roleService.initList();
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, role);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, role);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, role);
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
            if (null == roleService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (roleService.removeById(id)) {
                    roleService.initList();
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
     * 授权
     *
     * @param id            角色ID
     * @param permissionIds 权限菜单ID集合
     * @return ReturnEntity
     */
    @PutMapping("/grant/{id}")
    public ReturnEntity grant(@PathVariable("id") Long id, @RequestBody List<Long> permissionIds) {
        try {
            roleService.grant(id, permissionIds);
            return new ReturnEntitySuccess(Constants.MSG_GRANT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_GRANT_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_GRANT_FAILED, id);
        }
    }
}
