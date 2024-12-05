package com.wemirr.platform.iam.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wemirr.platform.iam.base.domain.dto.req.MessageNotifyReq;
import com.wemirr.platform.iam.base.domain.dto.req.MessageTemplatePageReq;
import com.wemirr.platform.iam.base.domain.dto.req.MessageTemplateSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageTemplateDetailResp;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageTemplatePageResp;
import com.wemirr.platform.iam.base.domain.entity.MessageTemplate;

/**
 * @author Levin
 */
public interface MessageTemplateService extends IService<MessageTemplate> {


    /**
     * 分页列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<MessageTemplatePageResp> pageList(MessageTemplatePageReq req);

    /**
     * 创建模板
     *
     * @param req req
     */
    void create(MessageTemplateSaveReq req);

    /**
     * 修改模板
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, MessageTemplateSaveReq req);

    void notify(MessageNotifyReq req);

    MessageTemplateDetailResp detail(Long id);
}
