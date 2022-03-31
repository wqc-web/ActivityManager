package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Attachment;
import com.zhongzhou.api.service.impl.AttachmentServiceImpl;
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
 * 前端控制器--附件
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/attachment")
@Slf4j
public class AttachmentController extends BaseController {
    private static final long serialVersionUID = 7860065745334106738L;
    @Resource
    private AttachmentServiceImpl attachmentService;

    /**
     * 分页查询列表
     *
     * @param pager      分页信息
     * @param attachment 附件实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<Attachment> pager, Attachment attachment,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Attachment> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(attachment.getName()), "name", attachment.getName());
            wrapper.eq(null != attachment.getType(), "type", attachment.getType());
            wrapper.eq(null != attachment.getSourceId(), "source_id", attachment.getSourceId());
            List<Attachment> records = attachmentService.page(pager, wrapper).getRecords();
            int count = attachmentService.count(wrapper);
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
     * @param attachment 附件实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(Attachment attachment,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<Attachment> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(attachment.getName()), "name", attachment.getName());
            wrapper.eq(null != attachment.getType(), "type", attachment.getType());
            wrapper.eq(null != attachment.getSourceId(), "source_id", attachment.getSourceId());
            List<Attachment> list = attachmentService.list(wrapper);
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
            Attachment attachment = attachmentService.getById(id);
            if (null != attachment) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, attachment);
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
     * @param attachment 附件实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody Attachment attachment, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, attachment);
        } else {
            try {
                attachment.setCreateTime(LocalDateTime.now());
                attachment.setCreateUserId(tokenController.getUserId(request, response));
                if (attachmentService.save(attachment)) {
                    return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, attachment);
                } else {
                    return new ReturnEntityError(Constants.MSG_INSERT_FAILED, attachment);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, attachment);
            }
        }
    }

    /**
     * 修改
     *
     * @param id         主键
     * @param attachment 附件实体类
     * @param result     BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody Attachment attachment, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), attachment);
        } else {
            try {
                if (null == attachmentService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, attachment);
                } else {
                    attachment.setId(id);
                    if (attachmentService.updateById(attachment)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, attachment);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, attachment);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, attachment);
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
            if (null == attachmentService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (attachmentService.removeById(id)) {
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
