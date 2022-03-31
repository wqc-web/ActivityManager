package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.entity.Dictionary;
import com.zhongzhou.api.mapper.DictionaryMapper;
import com.zhongzhou.api.service.IDictionaryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类--数据字典
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    private static final long serialVersionUID = 3599708998613109114L;

}
