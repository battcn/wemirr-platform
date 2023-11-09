package com.wemirr.framework.db.dynamic.core.redis;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class RedisDynamicDatasourcePublish implements DynamicDatasourceEventPublish {

    private final StringRedisTemplate redisTemplate;


    @Override
    public void publish(TenantDynamicDatasource message) {
        log.info("redis publish - {}", message);
        redisTemplate.convertAndSend(DEFAULT_EVENT_TOPIC, JSON.toJSONString(message));
    }


}
