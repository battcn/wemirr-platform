package com.wemirr.framework.i18n.core;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.framework.i18n.domain.I18nRedisKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class I18nRedisTemplate {

    private final RedisTemplate<String, Object> redisTemplate;

    public void loadI18nMessage(List<I18nMessage> messages) {
        if (messages == null) {
            return;
        }
        redisTemplate.delete(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        final Map<String, I18nMessage> map = messages.stream().collect(Collectors.toMap(I18nMessage::buildKey, Function.identity()));
        redisTemplate.opsForHash().putAll(I18nRedisKeyConstants.I18N_DATA_PREFIX, map);
    }

    public void publish(List<I18nMessage> list) {
        if (list == null) {
            return;
        }
        for (I18nMessage message : list) {
            publish(message);
        }
    }

    public void publish(I18nMessage message) {
        redisTemplate.convertAndSend(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED, JSON.toJSONString(message));
    }


}
