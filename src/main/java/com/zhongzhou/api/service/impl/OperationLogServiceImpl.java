package com.zhongzhou.api.service.impl;

import com.zhongzhou.api.entity.OperationLog;
import com.zhongzhou.api.mapper.OpeartionLogMapper;
import com.zhongzhou.api.service.IOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类--操作日志
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OpeartionLogMapper, OperationLog> implements IOperationLogService {

    private static final long serialVersionUID = 607428386680433128L;
}
