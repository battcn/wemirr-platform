package com.wemirr.framework.i18n.configuration;

import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.framework.i18n.domain.I18nRedisKeyConstants;
import lombok.RequiredArgsConstructor;
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

    private final StringRedisTemplate redisTemplate;

    public void loadI18nMessage(List<I18nMessage> messages) {
        if (messages == null) {
            return;
        }
        final Map<String, I18nMessage> map = messages.stream().collect(Collectors.toMap(I18nMessage::buildKey, Function.identity()));
        redisTemplate.opsForHash().putAll(I18nRedisKeyConstants.I18N_DATA_PREFIX, map);
    }

    public void publish(String message) {
        redisTemplate.convertAndSend(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED, message);
    }


}
