package com.wemirr.framework.websocket.memory;

import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.configuration.WebSocketHeartBeatChecker;
import com.wemirr.framework.websocket.configuration.WebSocketProperties;
import com.wemirr.framework.websocket.utils.SpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 内存管理websocket配置
 *
 * @author Levin
 */
@Configuration
@EnableConfigurationProperties(WebSocketProperties.class)
public class MemoryWebSocketConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean(WebSocketManager.WEBSOCKET_MANAGER_NAME)
    @ConditionalOnMissingBean(name = WebSocketManager.WEBSOCKET_MANAGER_NAME)
    public WebSocketManager webSocketManager() {
        return new MemWebSocketManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketHeartBeatChecker webSocketHeartBeatChecker() {
        return new WebSocketHeartBeatChecker();
    }
}