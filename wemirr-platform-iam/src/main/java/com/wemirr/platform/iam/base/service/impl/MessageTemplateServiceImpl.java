package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.service.MessageTemplateService;
import com.wemirr.platform.iam.system.domain.dto.req.MessageTemplatePageReq;
import com.wemirr.platform.iam.system.domain.dto.req.MessageTemplateSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.MessageTemplatePageResp;
import com.wemirr.platform.iam.system.domain.entity.MessageTemplate;
import com.wemirr.platform.iam.system.repository.MessageTemplateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {
    @Override
    public IPage<MessageTemplatePageResp> pageList(MessageTemplatePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<MessageTemplate>lbQ()
                        .eq(MessageTemplate::getType, req.getType())
                        .eq(MessageTemplate::getSubject, req.getSubject())
                        .eq(MessageTemplate::getName, req.getName()))
                .convert(x -> BeanUtil.toBean(x, MessageTemplatePageResp.class));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(MessageTemplateSaveReq req) {
        MessageTemplate bean = BeanUtil.toBean(req, MessageTemplate.class);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, MessageTemplateSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模板不存在"));
        MessageTemplate bean = BeanUtilPlus.toBean(id, req, MessageTemplate.class);
        this.baseMapper.updateById(bean);
    }
}
