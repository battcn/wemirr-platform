package com.wemirr.platform.iam.base.service.strategy;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

/**
 * @author levin
 */
@Component
@RequiredArgsConstructor
public class EmailMessageNotifyStrategy implements MessageNotifyStrategy {

    private static final Map<Long, MailSendFactory> SENDER_QUEUE = MapUtil.newConcurrentHashMap();

    @Override
    public String channelType() {
        // 定义枚举
        return "email";
    }

    public JavaMailSenderImpl getMailSender(MessageChannel channel) {
        // 要判断配置是否又被覆盖过,如果配置覆盖过应该刷新配置
        ChannelSetting setting = JSON.parseObject(channel.getSetting(), ChannelSetting.class);
        MailSendFactory factory = SENDER_QUEUE.get(channel.getId());
        if (factory != null && factory.getSetting() == setting) {
            log.debug("检测到配置未发生变更,当前采用已创建的对象进行短信发送");
            return factory.getSender();
        }
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(setting.getHost());
        mailSender.setPort(setting.getPort());
        mailSender.setUsername(setting.getUsername());
        mailSender.setPassword(setting.getPassword());
        mailSender.setProtocol(setting.getProtocol());
        Properties props = mailSender.getJavaMailProperties();
        ChannelSetting.Smtp smtp = setting.getSmtp();
        if (smtp != null) {
            props.put("mail.smtp.auth", smtp.getAuth());
            props.put("mail.smtp.ssl.enable", smtp.getSsl());
            // 默认开启
            props.put("mail.smtp.starttls.enable", true);
        }
        SENDER_QUEUE.put(channel.getId(), MailSendFactory.builder().setting(setting).sender(mailSender).build());
        return mailSender;
    }

    @Override
    public void handler(MessageChannel channel, MessageNotify notify) {
        JavaMailSenderImpl mailSender = getMailSender(channel);
        String setting = channel.getSetting();
        log.debug("邮箱消息发送配置 => {},通知消息 => {}", setting, JSON.toJSONString(notify));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getUsername());
        message.setTo(notify.getSubscribe());
        message.setSubject(notify.getTitle());
        message.setText(notify.getContent());
        mailSender.send(message);
    }

    @Data
    @Builder
    public static class MailSendFactory {

        private ChannelSetting setting;

        private JavaMailSenderImpl sender;
    }

    @Data
    @EqualsAndHashCode
    public static class ChannelSetting {
        private Integer port;
        private String host;
        private String username;
        private String password;
        private String protocol;
        private Smtp smtp;

        @Data
        public static class Smtp {
            private Boolean auth;
            private Boolean ssl;
        }
    }
}
