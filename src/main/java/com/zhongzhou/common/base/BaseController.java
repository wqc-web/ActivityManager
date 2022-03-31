package com.zhongzhou.common.base;

import com.zhongzhou.api.common.TokenController;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @ClassName: BaseController
 * @Description: Controller基类
 * @Author: wj
 * @Date: 2020-06-19 11:13:24
 **/
public class BaseController implements Serializable {
    private static final long serialVersionUID = -5589089959393348668L;
    @Resource
    protected TokenController tokenController;
}
