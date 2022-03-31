package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.api.service.impl.SysUserServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.base.Pager;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.dto.PasswordDTO;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.Md5Util;
import com.zhongzhou.common.utils.ValidateUtil;
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
 * 前端控制器--系统用户
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/sysUser")
@Slf4j
public class SysUserController extends BaseController {
    private static final long serialVersionUID = -1832378059689897871L;
    @Resource
    private SysUserServiceImpl sysUserService;

    /**
     * 分页查询列表
     *
     * @param pager   分页信息
     * @param sysUser 系统用户实体类
     * @return
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<SysUser> pager, SysUser sysUser,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(sysUser.getUserName()), "user_name", sysUser.getUserName());
            wrapper.like(StringUtils.isNotBlank(sysUser.getUserCode()), "user_code", sysUser.getUserCode());
            wrapper.like(StringUtils.isNotBlank(sysUser.getRealName()), "real_name", sysUser.getRealName());
            wrapper.eq(StringUtils.isNotBlank(sysUser.getIdentityNo()), "identity_no", sysUser.getIdentityNo());
            wrapper.eq(StringUtils.isNotBlank(sysUser.getPhone()), "phone", sysUser.getPhone());
            wrapper.isNull("open_id");
            List<SysUser> records = sysUserService.pageSysUserList(pager, wrapper);
            int count = sysUserService.count(wrapper);
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
     * @param sysUser 系统用户实体类
     * @return
     */
    @GetMapping("/list")
    public ReturnEntity selectList(SysUser sysUser,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(sysUser.getUserName()), "user_name", sysUser.getUserName());
            wrapper.like(StringUtils.isNotBlank(sysUser.getUserCode()), "user_code", sysUser.getUserCode());
            wrapper.like(StringUtils.isNotBlank(sysUser.getRealName()), "real_name", sysUser.getRealName());
            wrapper.eq(StringUtils.isNotBlank(sysUser.getIdentityNo()), "identity_no", sysUser.getIdentityNo());
            wrapper.eq(StringUtils.isNotBlank(sysUser.getPhone()), "phone", sysUser.getPhone());
            wrapper.isNull("open_id");
            List<SysUser> list = sysUserService.list(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 成员分页查询列表
     *
     * @param pager   分页信息
     * @param sysUser 系统用户实体类
     * @return
     */
    @GetMapping("/userPage")
    public ReturnEntity userPage(Pager<SysUser> pager, SysUser sysUser,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SysUser> records = sysUserService.userPage(pager , sysUser);
            int count = sysUserService.userCount(sysUser);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, count, records);
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
            SysUser sysUser = sysUserService.findDetailById(id);
            if (null != sysUser) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, sysUser);
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
     * @param sysUser 系统用户实体类
     * @param result  BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody SysUser sysUser, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, sysUser);
        } else {
            try {
                if (!ValidateUtil.identityValidate(sysUser.getIdentityNo())) {
                    return new ReturnEntityError(Constants.ERROR_IDENTITY_VALIDATE);
                }
                if (!ValidateUtil.phoneValidate(sysUser.getPhone())) {
                    return new ReturnEntityError(Constants.ERROR_PHONE_VALIDATE);
                }
                QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
                wrapper.eq(StringUtils.isNotBlank(sysUser.getUserName()), "user_name", sysUser.getUserName())
                        .or().eq(StringUtils.isNotBlank(sysUser.getUserCode()), "user_code", sysUser.getUserCode());
                if (sysUserService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, sysUser);
                } else {
                    Long userId = tokenController.getUserId(request, response);
                    sysUser.setUserPassword(Md5Util.getSaltMD5("123456"));
                    sysUser.setCreateUserId(userId);
                    sysUser.setCreateTime(LocalDateTime.now());
                    if (sysUserService.saveSysUser(sysUser)) {
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, sysUser);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, sysUser);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_UPDATE_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, sysUser);
            }
        }
    }

    /**
     * 修改
     *
     * @param id      主键
     * @param sysUser 系统用户实体类
     * @param result  BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody SysUser sysUser, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), sysUser);
        } else {
            try {
                if (null == sysUserService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, sysUser);
                } else {
                    sysUser.setId(id);
                    Long userId = tokenController.getUserId(request, response);
                    sysUser.setLastUpdateUserId(userId);
                    sysUser.setLastUpdateTime(LocalDateTime.now());
                    if (sysUserService.updateUserById(sysUser)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, sysUser);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, sysUser);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, sysUser);
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
            if (null == sysUserService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (sysUserService.removeById(id)) {
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
     * 根据用户角色查询用户列表
     *
     * @param roleId 角色ID
     * @return ReturnEntity
     */
    @GetMapping("/listByRoleId/{roleId}")
    public ReturnEntity listByRoleId(@PathVariable("roleId") Long roleId) {
        try {
            List<SysUser> sysUserList = sysUserService.listByRoleId(roleId);
            Integer count = sysUserList == null ? 0 : sysUserList.size();
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, count, sysUserList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("roleId:{} [" + Constants.MSG_FIND_FAILED + "]:{}", roleId, e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, roleId);
        }
    }

    /**
     * 根据用户部门查询用户列表
     *
     * @param depId 部门ID
     * @return ReturnEntity
     */
    @GetMapping("/listByDepId/{depId}")
    public ReturnEntity listByDepId(@PathVariable("depId") Long depId) {
        try {
            List<SysUser> sysUserList = sysUserService.listByDepId(depId);
            Integer count = sysUserList == null ? 0 : sysUserList.size();
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, count, sysUserList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("depId:{} [" + Constants.MSG_FIND_FAILED + "]:{}", depId, e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, depId);
        }
    }

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param passwordDTO 密码DTO
     * @param result      BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/password/update/{id}")
    public ReturnEntity updatePassword(@PathVariable("id") Long id, @Validated @RequestBody PasswordDTO passwordDTO,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), passwordDTO);
        } else {
            try {
                SysUser sysUser = sysUserService.getById(id);
                if (null == sysUser) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
                } else {
                    sysUser.setUserPassword(Md5Util.getSaltMD5(passwordDTO.getNewPassword()));
                    if (sysUserService.updateUserById(sysUser)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, id);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, id);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, id);
            }
        }
    }
}
