package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.entity.DictionaryType;
import com.zhongzhou.api.mapper.DictionaryTypeMapper;
import com.zhongzhou.api.service.IDictionaryTypeService;
import com.zhongzhou.common.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Service
public class DictionaryTypeServiceImpl extends ServiceImpl<DictionaryTypeMapper, DictionaryType> implements IDictionaryTypeService {

    private static final long serialVersionUID = -48002356814488214L;

    @Override
    public void initList() {
        List<DictionaryType> dictionaryTypeList = list();
        dictionaryTypeList.forEach(dictionaryType -> {
            Constants.MAP_DICTIONARY_TYPE.put(dictionaryType.getId(), dictionaryType);
        });
    }
}
