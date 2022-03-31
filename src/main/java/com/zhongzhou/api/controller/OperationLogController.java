package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.OperationLog;
import com.zhongzhou.api.service.impl.OperationLogServiceImpl;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 前端控制器--操作日志
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/api/operationLog")
@Slf4j
public class OperationLogController extends BaseController {
    private static final long serialVersionUID = -8333449967504552270L;
    @Resource
    private OperationLogServiceImpl operationLogService;

    /**
     * 分页查询列表
     *
     * @param pager        分页信息
     * @param operationLog 操作日志实体类
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<OperationLog> pager, OperationLog operationLog,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            wrapper.like(StringUtils.isNotBlank(operationLog.getName()), "name", operationLog.getName());
            wrapper.eq(StringUtils.isNotBlank(operationLog.getIpAddr()), "ip_addr", operationLog.getIpAddr());
            wrapper.eq(StringUtils.isNotBlank(operationLog.getOperationUserName()), "operation_user_name", operationLog.getOperationUserName());
            String opeartionTimeStr = operationLog.getOperationTimeStr();
            if (StringUtils.isNotBlank(opeartionTimeStr)) {
                LocalDateTime opeartionTime = LocalDateTime.parse(opeartionTimeStr, df);
                wrapper.ge("operation_time", opeartionTime);
                wrapper.lt("operation_time", opeartionTime);
            }
            wrapper.orderByDesc("operation_time");
            List<OperationLog> records = operationLogService.page(pager, wrapper).getRecords();
            int count = operationLogService.count(wrapper);
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
     * @param operationLog 操作日志实体类
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(OperationLog operationLog,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            wrapper.like(StringUtils.isNotBlank(operationLog.getName()), "name", operationLog.getName());
            wrapper.eq(StringUtils.isNotBlank(operationLog.getIpAddr()), "ip_addr", operationLog.getIpAddr());
            wrapper.eq(StringUtils.isNotBlank(operationLog.getOperationUserName()), "operation_user_name", operationLog.getOperationUserName());
            String opeartionTimeStr = operationLog.getOperationTimeStr();
            if (StringUtils.isNotBlank(opeartionTimeStr)) {
                LocalDateTime opeartionTime = LocalDateTime.parse(opeartionTimeStr, df);
                wrapper.ge("operation_time", opeartionTime);
                wrapper.lt("operation_time", opeartionTime);
            }
            wrapper.orderByDesc("operation_time");
            List<OperationLog> list = operationLogService.list(wrapper);
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
            OperationLog operationLog = operationLogService.getById(id);
            if (null != operationLog) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, operationLog);
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
     * @param operationLog 操作日志实体类
     * @param result       BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody OperationLog operationLog, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, operationLog);
        } else {
            try {
                operationLog.setOperationTime(LocalDateTime.now());
                operationLog.setOperationUserId(tokenController.getUserId(request, response));
                if (operationLogService.save(operationLog)) {
                    return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, operationLog);
                } else {
                    return new ReturnEntityError(Constants.MSG_INSERT_FAILED, operationLog);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, operationLog);
            }
        }
    }

    /**
     * 修改
     *
     * @param id           主键
     * @param operationLog 操作日志实体类
     * @param result       BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody OperationLog operationLog, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), operationLog);
        } else {
            try {
                if (null == operationLogService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, operationLog);
                } else {
                    operationLog.setId(id);
                    if (operationLogService.updateById(operationLog)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, operationLog);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, operationLog);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, operationLog);
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
            if (null == operationLogService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (operationLogService.removeById(id)) {
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
