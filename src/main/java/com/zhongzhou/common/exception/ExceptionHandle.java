/**
 * Copyright (C), 2015-2018, 江苏物合智联科技有限公司
 * FileName: ExceptionHandle
 * Author:   wj
 * Date:     2018/11/22 0022 16:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zhongzhou.common.exception;

import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ExceptionHandle
 * @Description: 统一错误处理
 * @Author: wj
 * @Date: 2020-06-29 12:20:26
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 未知异常
     *
     * @param e Exception
     * @return ReturnEntity
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnEntity handle(Exception e) {
        e.printStackTrace();
        log.error("[" + Constants.MSG_UNKNOWN_ERROR_MSG + "]:{}", e);
        if (e instanceof RuntimeException) {
            return new ReturnEntityError(e.getMessage());
        } else {
            return new ReturnEntityError(Constants.CODE_UNKNOWN_ERROR_MSG, Constants.MSG_UNKNOWN_ERROR_MSG);
        }
    }
}
