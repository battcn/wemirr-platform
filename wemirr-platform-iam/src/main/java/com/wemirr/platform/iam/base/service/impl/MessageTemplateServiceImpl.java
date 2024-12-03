package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.service.MessageTemplateService;
import com.wemirr.platform.iam.system.domain.dto.req.MessageNotifyReq;
import com.wemirr.platform.iam.system.domain.dto.req.MessageTemplatePageReq;
import com.wemirr.platform.iam.system.domain.dto.req.MessageTemplateSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.MessageTemplatePageResp;
import com.wemirr.platform.iam.system.domain.entity.MessageTemplate;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.repository.MessageTemplateMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

    private final UserMapper userMapper;

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

    private static final String T_TYPE = "sms";

    @Override
    public void notify(MessageNotifyReq req) {
        MessageTemplate template = this.baseMapper.selectOne(Wraps.<MessageTemplate>lbQ()
                .eq(MessageTemplate::getCode, req.getCode()).last("limit 1"));
        JSONObject variables = Optional.ofNullable(req.getVariables()).orElse(new JSONObject());
        String content = StrUtil.format(template.getContent(), variables);
        List<User> list = this.userMapper.selectByIds(req.getSubscriberIdList());
        if (CollUtil.isEmpty(list)) {
            log.warn("订阅信息不存在");
            return;
        }
        // todo 通过模板模式调用不同的通知实现
        if (StrUtil.equals(template.getType(), T_TYPE)) {
        }
        for (User user : list) {
            log.debug("向用户 - {},发送一条通知 - {}", user.getNickName(), content);
        }
    }
}
