package com.zhongzhou.api.service;

import com.zhongzhou.api.entity.Attachment;
import com.zhongzhou.common.base.BaseService;

/**
 * <p>
 *  服务类--附件
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
public interface IAttachmentService extends BaseService<Attachment> {

    void initListForMenu();

}
