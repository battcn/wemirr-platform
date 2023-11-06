package com.wemirr.framework.i18n.core;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.TypeUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.framework.i18n.domain.I18nRedisKeyConstants;
import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class DynamicMessageSource extends StaticMessageSource implements InitializingBean, AbstractMessageEventListener<I18nMessage> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void afterPropertiesSet() {
        final Map<Object, Object> entries = redisTemplate.opsForHash().entries(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        if (MapUtil.isEmpty(entries)) {
            return;
        }
        final Collection<Object> values = entries.values();
        for (Object value : values) {
            I18nMessage message = JSON.parseObject(JSON.toJSONString(value), I18nMessage.class);
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