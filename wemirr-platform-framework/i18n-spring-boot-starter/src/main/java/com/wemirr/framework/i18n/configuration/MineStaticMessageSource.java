package com.wemirr.framework.i18n.configuration;

import cn.hutool.core.util.TypeUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.framework.i18n.domain.I18nRedisKeyConstants;
import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class MineStaticMessageSource extends StaticMessageSource implements InitializingBean, AbstractMessageEventListener<I18nMessage> {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() {
        final String i18nData = redisTemplate.opsForValue().get(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        final List<I18nMessage> messages = JSON.parseArray(i18nData, I18nMessage.class);
        if (messages == null) {
            return;
        }
        for (I18nMessage message : messages) {
            addMessage(message.getCode(), LocaleUtils.toLocale(message.getLanguage()), message.getMessage());
        }
    }

    @Override
    public void handleMessage(I18nMessage message) {
        if (message == null) {
            return;
        }
        addMessage(message.getCode(), LocaleUtils.toLocale(message.getLanguage()), message.getMessage());
    }


    @Override
    public Topic topic() {
        return new ChannelTopic(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED);
    }

    @Override
    public Type type() {
        return TypeUtil.getTypeArgument(I18nMessage.class);
    }
}