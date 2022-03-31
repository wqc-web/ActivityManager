package com.zhongzhou.common.bean;

import com.zhongzhou.common.utils.Constants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @ClassName: ReturnEntity
 * @Description: 返回实体
 * @Author: wj
 * @Date: 2020-03-08 17:09:37
 **/
@Data
@Slf4j
public class ReturnEntity implements Serializable {
    private static final long serialVersionUID = 1275584334145317101L;

    private Integer code;

    private String msg;

    private Integer count;

    private Object data;

    public ReturnEntity() {
    }

    public ReturnEntity(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
