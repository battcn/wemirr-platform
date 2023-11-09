package com.wemirr.framework.db.dynamic.core.redis;

import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceHandler;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Objects;

import static com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish.DEFAULT_EVENT_TOPIC;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class RedisDynamicDatasourceListener implements AbstractMessageEventListener<TenantDynamicDatasource> {


    private final TenantDynamicDataSourceHandler tenantDynamicDataSourceHandler;

    @Override
    public void handleMessage(TenantDynamicDatasource message) {
        if (Objects.isNull(message)) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        log.info("接收租户事件消息: - {}", message);
        tenantDynamicDataSourceHandler.handler(EventAction.of(message.getAction()), message);
    }

    @Override
    public Topic topic() {
        return new ChannelTopic(DEFAULT_EVENT_TOPIC);
    }

    @Override
    public Type type() {
        return TenantDynamicDatasource.class;
    }
}
