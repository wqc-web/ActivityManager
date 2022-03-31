package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Dictionary;
import com.zhongzhou.api.service.impl.DictionaryServiceImpl;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器--数据字典
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/dictionary")
@Slf4j
public class DictionaryController extends BaseController {
    private static final long serialVersionUID = -183693441511990045L;
    @Resource
    private DictionaryServiceImpl dictionaryService;

    /**
     * 分页查询列表
     *
     * @param pager      分页信息
     * @param dictionary 数据字典实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<Dictionary> pager, Dictionary dictionary,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(dictionary.getDicName()), "dic_name", dictionary.getDicName());
            wrapper.like(StringUtils.isNotBlank(dictionary.getDicCode()), "dic_code", dictionary.getDicCode());
            wrapper.like(StringUtils.isNotBlank(dictionary.getDicValue()), "dic_value", dictionary.getDicValue());
            wrapper.eq(null != dictionary.getType(), "type", dictionary.getType());
            wrapper.eq(StringUtils.isNotBlank(dictionary.getTypeCode()), "type_code", dictionary.getTypeCode());
            wrapper.eq(StringUtils.isNotBlank(dictionary.getTypeName()), "type_name", dictionary.getTypeName());
            List<Dictionary> records = dictionaryService.page(pager, wrapper).getRecords();
            int count = dictionaryService.count(wrapper);
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
     * @param dictionary 数据字典实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(Dictionary dictionary,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(dictionary.getDicName()), "dic_name", dictionary.getDicName());
            wrapper.like(StringUtils.isNotBlank(dictionary.getDicCode()), "dic_code", dictionary.getDicCode());
            wrapper.like(StringUtils.isNotBlank(dictionary.getDicValue()), "dic_value", dictionary.getDicValue());
            wrapper.eq(null != dictionary.getType(), "type", dictionary.getType());
            wrapper.eq(StringUtils.isNotBlank(dictionary.getTypeCode()), "type_code", dictionary.getTypeCode());
            wrapper.eq(StringUtils.isNotBlank(dictionary.getTypeName()), "type_name", dictionary.getTypeName());
            List<Dictionary> list = dictionaryService.list(wrapper);
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
            Dictionary dictionary = dictionaryService.getById(id);
            if (null != dictionary) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, dictionary);
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
     * @param dictionary 数据字典实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody Dictionary dictionary, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, dictionary);
        } else {
            try {
                QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
                wrapper.eq("dic_code", dictionary.getDicCode());
                wrapper.eq("dic_value", dictionary.getDicValue());
                wrapper.eq("type", dictionary.getType());
                if (dictionaryService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, dictionary);
                } else {
                    Long type = dictionary.getType();
                    if (null != type) {
                        dictionary.setTypeName(Constants.MAP_DICTIONARY_TYPE.get(type).getTypeName());
                        dictionary.setTypeCode(Constants.MAP_DICTIONARY_TYPE.get(type).getCode());
                    }
                    dictionary.setCreateTime(LocalDateTime.now());
                    dictionary.setCreateUserId(tokenController.getUserId(request, response));
                    if (dictionaryService.save(dictionary)) {
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, dictionary);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, dictionary);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, dictionary);
            }
        }
    }

    /**
     * 修改
     *
     * @param id         主键
     * @param dictionary 数据字典实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody Dictionary dictionary, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), dictionary);
        } else {
            try {
                if (null == dictionaryService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, dictionary);
                } else {
                    dictionary.setId(id);
                    Long type = dictionary.getType();
                    if (null != type) {
                        dictionary.setTypeName(Constants.MAP_DICTIONARY_TYPE.get(type).getTypeName());
                        dictionary.setTypeCode(Constants.MAP_DICTIONARY_TYPE.get(type).getCode());
                    }
                    dictionary.setLastUpdateTime(LocalDateTime.now());
                    dictionary.setLastUpdateUserId(tokenController.getUserId(request, response));
                    if (dictionaryService.updateById(dictionary)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, dictionary);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, dictionary);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, dictionary);
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
            if (null == dictionaryService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (dictionaryService.removeById(id)) {
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
