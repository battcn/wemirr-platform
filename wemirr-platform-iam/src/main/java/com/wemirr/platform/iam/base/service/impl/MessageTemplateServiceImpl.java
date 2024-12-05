package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.MapHelper;
import com.wemirr.framework.commons.MvelHelper;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.domain.dto.req.MessageNotifyReq;
import com.wemirr.platform.iam.base.domain.dto.req.MessageTemplatePageReq;
import com.wemirr.platform.iam.base.domain.dto.req.MessageTemplateSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageTemplateDetailResp;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageTemplatePageResp;
import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import com.wemirr.platform.iam.base.domain.entity.MessageTemplate;
import com.wemirr.platform.iam.base.repository.MessageChannelMapper;
import com.wemirr.platform.iam.base.repository.MessageNotifyMapper;
import com.wemirr.platform.iam.base.service.MessageTemplateService;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.repository.MessageTemplateMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

    private final AuthenticationContext context;
    private final UserMapper userMapper;
    private final MessageChannelMapper messageChannelMapper;
    private final MessageNotifyMapper messageNotifyMapper;

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
        if (CollUtil.isNotEmpty(req.getType())) {
            bean.setType(StrUtil.join(",", req.getType()));
        }
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, MessageTemplateSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模板不存在"));
        MessageTemplate bean = BeanUtilPlus.toBean(id, req, MessageTemplate.class);
        if (CollUtil.isNotEmpty(req.getType())) {
            bean.setType(StrUtil.join(",", req.getType()));
        }
        this.baseMapper.updateById(bean);
    }

    private static final String T_TYPE = "sms";

    @Override
    public void notify(MessageNotifyReq req) {
        log.warn("========== [ 如果服务器资源充足,消息推送较大的情况,请将消息丢到 Redis 或者 MQ 中进行异步推送] ==========");
        MessageTemplate template = this.baseMapper.selectOne(Wraps.<MessageTemplate>lbQ()
                .eq(MessageTemplate::getCode, req.getCode()).last("limit 1"));
        JSONObject variables = Optional.ofNullable(req.getVariables()).orElse(new JSONObject());
        List<User> userList = this.userMapper.selectByIds(req.getSubscriberIdList());
        if (CollUtil.isEmpty(userList)) {
            log.warn("订阅信息不存在");
            return;
        }
        List<MessageChannel> channels = this.messageChannelMapper.selectList(Wraps.<MessageChannel>lbQ()
                .in(MessageChannel::getType, StrUtil.split(template.getType(), ","))
                .eq(MessageChannel::getStatus, true));
        String content = MvelHelper.format(template.getContent(), variables);
        var list = userList.stream().map(user -> MessageNotify.builder().userId(user.getId())
                .templateId(template.getId()).variables(JSON.toJSONString(variables))
                .title(template.getSubject()).type(template.getType())
                .content(content).nickname(user.getNickName())
                .tenantId(context.tenantId())
                .deleted(false).createdBy(context.userId())
                .createdName(context.nickName()).createdTime(Instant.now())
                .build()).toList();
        Map<String, MessageChannel> channelMap = MapHelper.toHashMap(channels, MessageChannel::getType, x -> x);
        // 需要调整,用设计模式调整
        for (MessageNotify notify : list) {
            List<String> typeList = StrUtil.split(notify.getType(), ",");
            if (CollUtil.isEmpty(typeList)) {
                continue;
            }
            for (String type : typeList) {
                MessageChannel channel = channelMap.get(type);
                if (channel == null) {
                    continue;
                }
                // 理论上如果海量用户同一个通道只需要初始化一次,所以这地方应该要结合本地缓存,再不济也要结合 thread-local 进行对象优化
                JavaMailSenderImpl mailSender = createMailSender(channel);
                // 读取各个消息渠道的配置
                String setting = channel.getSetting();
                // 后续会将 IAM 消息抽离到一个单独的服务 与 File 共存, 通用解耦,然后方便开发同学二开过程中 IAM 不需要删太多东西
                if (channel.getType().equals("email")) {
                    log.debug("邮箱消息发送 => {}", setting);
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom(mailSender.getUsername());
                    message.setTo(mailSender.getUsername());
                    message.setSubject(template.getSubject());
                    message.setText(content);
                    // 发送邮件
                    mailSender.send(message);
                }
                if (channel.getType().equals("sms")) {
                    log.debug("短信消息发送 => {}", setting);
                }
                if (channel.getType().equals("system")) {
                    log.debug("系统消息发送 => {}", setting);
                }
            }
        }
        CollUtil.split(list, 600).forEach(messageNotifyMapper::insertBatchSomeColumn);
    }


    public JavaMailSenderImpl createMailSender(MessageChannel channel) {
        JSONObject setting = JSON.parseObject(channel.getSetting());
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(setting.getString("host"));
        mailSender.setPort(setting.getInteger("port"));
        mailSender.setUsername(setting.getString("username"));
        mailSender.setPassword(setting.getString("password"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true"); // 默认开启 SSL
        return mailSender;
    }

    @Override
    public MessageTemplateDetailResp detail(Long id) {
        var template = Optional.ofNullable(baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("模板不存在"));
        var bean = BeanUtil.toBean(template, MessageTemplateDetailResp.class);
        if (StrUtil.isBlank(template.getContent())) {
            return bean;
        }
        List<String> variables = MvelHelper.getVariables(template.getContent());
        bean.setVariables(new HashSet<>(variables));
        return bean;
    }
}
