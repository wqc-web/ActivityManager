package com.zhongzhou.common.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.zhongzhou.common.base.BaseBean;
import lombok.Data;

import java.util.Set;

/**
 * @ClassName: Token
 * @Description: 令牌
 * @Author: wj
 * @Date: 020-03-08 17:09:37
 **/
@Data
public class Token extends BaseBean {

    public static final String OBJECT_KEY = "TOKEN";
    private static final long serialVersionUID = -3976062525832777293L;

    /**
     * 用户Id
     */
    @TableId
    private Long userId;

    /**
     * token有效秒数
     */
    private Long expireSecond;

    /**
     * 资源列表
     */
    private Set<String> resSet;

}
