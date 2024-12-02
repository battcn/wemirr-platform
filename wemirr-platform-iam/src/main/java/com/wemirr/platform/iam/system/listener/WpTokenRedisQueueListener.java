package com.wemirr.platform.iam.system.listener;

import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * TODO 方案暂时废弃,还是不优雅
 * 在线用户队列监听
 *
 * @author Levin
 */

@Slf4j
@Deprecated
@RequiredArgsConstructor
public class WpTokenRedisQueueListener implements AbstractMessageEventListener<String> {

    private final RedisProperties properties;

    @Override
    public void handleMessage(String message) {
        if (Objects.isNull(message)) {
            log.warn("event wp-token-redis-queue is null....");
            return;
        }
        log.info("receiver wp-token redis message: - {}", message);
    }

    @Override
    public Topic topic() {
        int database = properties.getDatabase();
        return new PatternTopic("__keyevent@" + database + "__:expired");
    }

    @Override
    public Type type() {
        return String.class;
    }
}
