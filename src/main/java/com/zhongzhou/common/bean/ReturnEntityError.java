package com.zhongzhou.common.bean;

import com.zhongzhou.common.utils.Constants;
import lombok.Data;

/**
 * @ClassName: ReturnEntityError
 * @Description: 接口调用出错时返回实体
 * @Author: wj
 * @Date: 020-03-08 17:09:37
 **/
@Data
public class ReturnEntityError extends ReturnEntity {

    private static final long serialVersionUID = -1821372766651550968L;

    public ReturnEntityError() {
    }

    public ReturnEntityError(Integer code, String msg, Integer count, Object data) {
        super(code, msg, count, data);
    }

    public ReturnEntityError(String msg) {
        super(Constants.CODE_ERROR, msg, null, null);
    }

    public ReturnEntityError(Integer code, String msg) {
        super(code, msg, null, null);
    }

    public ReturnEntityError(String msg, Object data) {
        super(Constants.CODE_ERROR, msg, null, data);
    }

    public ReturnEntityError(Integer code, String msg, Object data) {
        super(code, msg, null, data);
    }

    public ReturnEntityError(String msg, Integer count, Object data) {
        super(Constants.CODE_ERROR, msg, count, data);
    }
}
