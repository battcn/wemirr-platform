/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.framework.websocket.redis;

import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.configuration.WebSocketHeartBeatChecker;
import com.wemirr.framework.websocket.configuration.WebSocketProperties;
import com.wemirr.framework.websocket.redis.action.ActionConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
    
    @Bean(WebSocketManager.WEBSOCKET_MANAGER_NAME)
    @ConditionalOnMissingBean(name = WebSocketManager.WEBSOCKET_MANAGER_NAME)
    public WebSocketManager webSocketManager() {
        return new RedisWebSocketManager();
    }
    
    @Bean(RedisReceiver.REDIS_RECEIVER_NAME)
    public RedisReceiver redisReceiver(ApplicationContext applicationContext) {
        return new DefaultRedisReceiver(applicationContext);
    }
    
    @Bean
    public MessageListenerAdapter listenerAdapter(@Qualifier(RedisReceiver.REDIS_RECEIVER_NAME) RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, RedisReceiver.RECEIVER_METHOD_NAME);
    }
    
    // @Bean("redisMessageListenerContainer")
    // public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
    // MessageListenerAdapter listenerAdapter) {
    //
    // RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    // container.setConnectionFactory(connectionFactory);
    // container.addMessageListener(listenerAdapter, new PatternTopic(RedisWebSocketManager.CHANNEL));
    // return container;
    // }
    
    @Bean
    @ConditionalOnMissingBean
    public WebSocketHeartBeatChecker webSocketHeartBeatChecker() {
        return new WebSocketHeartBeatChecker();
    }
}