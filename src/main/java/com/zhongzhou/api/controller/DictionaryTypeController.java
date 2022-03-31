package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.DictionaryType;
import com.zhongzhou.api.service.impl.DictionaryTypeServiceImpl;
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
 * 前端控制器--数据字典类型
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/dictionaryType")
@Slf4j
public class DictionaryTypeController extends BaseController {
    private static final long serialVersionUID = -3897661477441106451L;
    @Resource
    private DictionaryTypeServiceImpl dictionaryTypeService;

    /**
     * 分页查询列表
     *
     * @param pager          分页信息
     * @param dictionaryType 数据字典类型实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<DictionaryType> pager, DictionaryType dictionaryType,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<DictionaryType> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(dictionaryType.getTypeName()), "type_name", dictionaryType.getTypeName());
            wrapper.eq(StringUtils.isNotBlank(dictionaryType.getCode()), "code", dictionaryType.getCode());
            List<DictionaryType> records = dictionaryTypeService.page(pager, wrapper).getRecords();
            int count = dictionaryTypeService.count(wrapper);
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
     * @param dictionaryType 数据字典类型实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(DictionaryType dictionaryType,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<DictionaryType> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(dictionaryType.getTypeName()), "type_name", dictionaryType.getTypeName());
            wrapper.eq(StringUtils.isNotBlank(dictionaryType.getCode()), "code", dictionaryType.getCode());
            List<DictionaryType> list = dictionaryTypeService.list(wrapper);
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
            DictionaryType dictionaryType = dictionaryTypeService.getById(id);
            if (null != dictionaryType) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, dictionaryType);
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
     * @param dictionaryType 数据字典类型实体类
     * @param result         BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody DictionaryType dictionaryType, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, dictionaryType);
        } else {
            try {
                QueryWrapper<DictionaryType> wrapper = new QueryWrapper<>();
                wrapper.eq("code", dictionaryType.getTypeName());
                if (dictionaryTypeService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, dictionaryType);
                } else {
                    dictionaryType.setCreateTime(LocalDateTime.now());
                    dictionaryType.setCreateUserId(tokenController.getUserId(request, response));
                    if (dictionaryTypeService.save(dictionaryType)) {
                        dictionaryTypeService.initList();
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, dictionaryType);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, dictionaryType);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, dictionaryType);
            }
        }
    }

    /**
     * 修改
     *
     * @param id             主键
     * @param dictionaryType 数据字典类型实体类
     * @param result         BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody DictionaryType dictionaryType, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), dictionaryType);
        } else {
            try {
                if (null == dictionaryTypeService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, dictionaryType);
                } else {
                    dictionaryType.setId(id);
                    dictionaryType.setLastUpdateTime(LocalDateTime.now());
                    dictionaryType.setLastUpdateUserId(tokenController.getUserId(request, response));
                    if (dictionaryTypeService.updateById(dictionaryType)) {
                        dictionaryTypeService.initList();
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, dictionaryType);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, dictionaryType);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, dictionaryType);
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
            if (null == dictionaryTypeService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (dictionaryTypeService.removeById(id)) {
                    dictionaryTypeService.initList();
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
