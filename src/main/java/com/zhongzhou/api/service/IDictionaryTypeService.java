package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.DictionaryType;
import com.zhongzhou.common.base.BaseService;

/**
 * <p>
 * 服务类--数据字典类型
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface IDictionaryTypeService extends BaseService<DictionaryType> {
    /**
     * 初始化列表
     */
    void initList();

}
