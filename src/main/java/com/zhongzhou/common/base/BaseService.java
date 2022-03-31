package com.zhongzhou.common.base;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @ClassName: BaseService
 * @Description: Service接口基类
 * @Author: wj
 * @Date: 2020-06-19 11:13:47
 **/
@Service
public interface BaseService<T> extends IService<T>, Serializable {
}
