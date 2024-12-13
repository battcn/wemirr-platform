package com.wemirr.platform.suite.file.event;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiao1
 * @date 2024-12
 */
@RequiredArgsConstructor
@Component
public class RedisStorageConfigPublish implements RedisStorageConfigEventPublish {
    Logger log = LoggerFactory.getLogger(RedisStorageConfigPublish.class);

    private final StringRedisTemplate redisTemplate;



    @Override
    public void publish(RedisStorageConfigEvent message) {
        log.info("redis publish - {}", message);
        redisTemplate.convertAndSend(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC, JSON.toJSONString(message));
    }
}
