package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Department;
import com.zhongzhou.api.service.impl.DepartmentServiceImpl;
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
 * 前端控制器--部门
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/department")
@Slf4j
public class DepartmentController extends BaseController {
    private static final long serialVersionUID = 955900784791039552L;
    @Resource
    private DepartmentServiceImpl departmentService;

    /**
     * 分页查询列表
     *
     * @param pager      分页信息
     * @param department 部门实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<Department> pager, Department department,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(department.getDepName()), "dep_name", department.getDepName());
            wrapper.eq(null != department.getParentId(), "dep_name", department.getParentId());
            List<Department> records = departmentService.page(pager, wrapper).getRecords();
            if (null != records && records.size() > 0) {
                records.forEach(dep -> {
                    if (!Constants.NUM_ZERO.equals(dep.getParentId())) {
                        dep.setParentName(Constants.MAP_DEPARTMENT.get(dep.getParentId()));
                    }
                });
            }
            int count = departmentService.count(wrapper);
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
     * @param department 部门实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(Department department,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(department.getDepName()), "dep_name", department.getDepName());
            wrapper.eq(null != department.getParentId(), "dep_name", department.getParentId());
            List<Department> list = departmentService.list(wrapper);
            if (null != list && list.size() > 0) {
                list.forEach(dep -> {
                    if (!Constants.NUM_ZERO.equals(dep.getParentId())) {
                        dep.setParentName(Constants.MAP_DEPARTMENT.get(dep.getParentId()));
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
            Department department = departmentService.getById(id);
            if (null != department) {
                if (!Constants.NUM_ZERO.equals(department.getParentId())) {
                    department.setParentName(Constants.MAP_DEPARTMENT.get(department.getParentId()));
                }
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, department);
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
     * @param department 部门实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody Department department, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, department);
        } else {
            try {
                QueryWrapper<Department> wrapper = new QueryWrapper<>();
                wrapper.eq("dep_name", department.getDepName());
                if (departmentService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, department);
                } else {
                    if (null == department.getParentId()) {
                        department.setParentId(Constants.NUM_ZERO);
                    }
                    department.setCreateTime(LocalDateTime.now());
                    department.setCreateUserId(tokenController.getUserId(request, response));
                    if (departmentService.save(department)) {
                        departmentService.initList();
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, department);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, department);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, department);
            }
        }
    }

    /**
     * 修改
     *
     * @param id         主键
     * @param department 部门实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody Department department, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), department);
        } else {
            try {
                if (null == departmentService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, department);
                } else {
                    department.setId(id);
                    department.setLastUpdateTime(LocalDateTime.now());
                    department.setLastUpdateUserId(tokenController.getUserId(request, response));
                    if (departmentService.updateById(department)) {
                        departmentService.initList();
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, department);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, department);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, department);
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
            if (null == departmentService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (departmentService.removeById(id)) {
                    departmentService.initList();
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
     * 获取部门树
     *
     * @return ReturnEntity
     */
    @GetMapping("/findDepTree")
    public ReturnEntity findDepTree() {
        try {
            List<Department> departmentTree = departmentService.findDepartmentTree();
            if (null != departmentTree && departmentTree.size() > 0) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, departmentTree);
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
