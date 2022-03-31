package com.zhongzhou.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @ClassName: BaseDao
 * @Description: Dao接口基类
 * @Author: wj
 * @Date: 2020-06-19 11:13:17
 **/
public interface BaseDao<T> extends BaseMapper<T>, Serializable {
}
