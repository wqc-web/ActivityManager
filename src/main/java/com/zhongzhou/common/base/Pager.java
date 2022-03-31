package com.zhongzhou.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @ClassName: Pager
 * @Description: 分页实体类
 * @Author: wj
 * @Date: 2020-06-19 11:13:57
 **/
public class Pager<T> extends Page<T> {
    private static final long serialVersionUID = -7470155927383371268L;

    public Pager() {
        super();
    }

    public Pager(long page, long limit) {
        super(page, limit);
    }

    public Pager(long page, long limit, long count) {
        super(page, limit, count);
    }
}
