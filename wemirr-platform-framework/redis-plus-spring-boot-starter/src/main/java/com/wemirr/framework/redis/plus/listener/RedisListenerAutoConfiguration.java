package com.wemirr.framework.redis.plus.listener;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * redisson限流器自动配置项
 *
 * @author Levin
 */
@Order
@Configuration
@AutoConfigureAfter(RedisConnectionFactory.class)
@ConditionalOnProperty(prefix = "extend.redis", name = "enabled", havingValue = "true", matchIfMissing = true)
public class RedisListenerAutoConfiguration {


    /**
     * 消息侦听器容器
     *
     * @param factory 连接工厂
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer redisContainer(final RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        var beans = SpringUtil.getBeansOfType(AbstractMessageEventListener.class);
        for (var entry : beans.entrySet()) {
            var listener = entry.getValue();

            container.addMessageListener(listener, listener.topic());
        }
        return container;
    }
}
