package com.wemirr.framework.websocket.redis;

import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.configuration.WebSocketHeartBeatChecker;
import com.wemirr.framework.websocket.configuration.WebSocketProperties;
import com.wemirr.framework.websocket.redis.action.ActionConfig;
import com.wemirr.framework.websocket.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Levin
 * redis管理websocket配置，利用redis的发布订阅功能实现，具备集群功能
 * 可以扩展此类，添加listener和topic及相应的receiver，使用自己的Enable注解导入即可
 * @see EnableRedisWebSocket
 */
@Configuration
@Import(ActionConfig.class)
@EnableConfigurationProperties(WebSocketProperties.class)
public class RedisWebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    @Bean(WebSocketManager.WEBSOCKET_MANAGER_NAME)
    @ConditionalOnMissingBean(name = WebSocketManager.WEBSOCKET_MANAGER_NAME)
    public WebSocketManager webSocketManager(@Autowired StringRedisTemplate stringRedisTemplate) {
        return new RedisWebSocketManager(stringRedisTemplate);
    }

    @Bean(RedisReceiver.REDIS_RECEIVER_NAME)
    public RedisReceiver redisReceiver(ApplicationContext applicationContext) {
        return new DefaultRedisReceiver(applicationContext);
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(@Qualifier(RedisReceiver.REDIS_RECEIVER_NAME) RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, RedisReceiver.RECEIVER_METHOD_NAME);
    }


    @Bean("redisMessageListenerContainer")
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisWebSocketManager.CHANNEL));
        return container;
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }


    @Bean
    @ConditionalOnMissingBean
    public WebSocketHeartBeatChecker webSocketHeartBeatChecker() {
        return new WebSocketHeartBeatChecker();
    }
}