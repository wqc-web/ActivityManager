package com.zhongzhou.common.bean;

import com.zhongzhou.common.utils.Constants;
import lombok.Data;

/**
 * @ClassName: ReturnEntitySuccess
 * @Description: 成功时返回实体
 * @Author: wj
 * @Date: 020-03-08 17:09:37
 **/
@Data
public class ReturnEntitySuccess extends ReturnEntity {

    private static final long serialVersionUID = 8063914801766963383L;

    public ReturnEntitySuccess() {
    }

    public ReturnEntitySuccess(Integer code, String msg, Integer count, Object data) {
        super(code, msg, count, data);
    }

    public ReturnEntitySuccess(String msg) {
        super(Constants.CODE_SUCCESS, msg, null, null);
    }

    public ReturnEntitySuccess(Integer code, String msg) {
        super(code, msg, null, null);
    }

    public ReturnEntitySuccess(String msg, Object data) {
        super(Constants.CODE_SUCCESS, msg, null, data);
    }

    public ReturnEntitySuccess(String msg, Integer count, Object data) {
        super(Constants.CODE_SUCCESS, msg, count, data);
    }
}
