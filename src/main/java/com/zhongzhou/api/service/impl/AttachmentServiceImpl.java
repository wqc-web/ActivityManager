package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Attachment;
import com.zhongzhou.api.entity.Dictionary;
import com.zhongzhou.api.entity.Permission;
import com.zhongzhou.api.mapper.AttachmentMapper;
import com.zhongzhou.api.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.common.utils.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

    private static final long serialVersionUID = -846211699268938535L;

    @Resource
    private DictionaryServiceImpl dictionaryService;

    @Override
    public void initListForMenu() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_code", "icon");
        dictionaryQueryWrapper.eq("type_code", "attachmentType");
        Dictionary dictionary = dictionaryService.getOne(dictionaryQueryWrapper);
        if(dictionary != null){
            QueryWrapper<Attachment> attachmentQueryWrapper = new QueryWrapper<>();
            attachmentQueryWrapper.eq("type", dictionary.getId());
            List<Attachment> attachmentList = list(attachmentQueryWrapper);
            attachmentList.forEach(attachment -> {
                Constants.MAP_MENU_ICON.put(attachment.getSourceId(), attachment);
            });
        }
    }
}
