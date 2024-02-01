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
package com.wemirr.framework.security.configuration;

import com.wemirr.framework.security.configuration.client.ResourceAuthExceptionEntryPoint;
import com.wemirr.framework.security.configuration.server.store.RedisTokenStore;
import com.wemirr.framework.security.service.RedisOAuth2AuthorizationServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author Levin
 */
@SuppressWarnings("ALL")
public class OAuth2AutoConfiguration {
    
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.server.registered-client}'.equalsIgnoreCase('jdbc')")
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }
    
    /**
     * 配置基于db的授权确认管理服务
     *
     * @param jdbcTemplate               db数据源信息
     * @param registeredClientRepository 客户端repository
     * @return JdbcOAuth2AuthorizationConsentService
     */
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.server.consent}'.equalsIgnoreCase('jdbc')")
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        // 基于db的授权确认管理服务
        // 基于本地内存的服务实现 InMemoryOAuth2AuthorizationConsentService
        // 基于分布式内存服务实现 RedisOAuth2AuthorizationConsentService - [暂未实现]
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }
    
    /**
     * 配置基于db的oauth2的授权管理服务
     *
     * @param jdbcTemplate               db数据源信息
     * @param registeredClientRepository 上边注入的客户端repository
     * @return JdbcOAuth2AuthorizationService
     */
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.authorization-type}'.equalsIgnoreCase('jdbc')")
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }
    
    /**
     * 配置基于db的oauth2的授权管理服务
     *
     * @param redisTemplate redis数据源信息
     * @return JdbcOAuth2AuthorizationService
     */
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.authorization-type}'.equalsIgnoreCase('redis')")
    public OAuth2AuthorizationService oAuth2AuthorizationService(RedisTokenStore redisTemplate) {
        return new RedisOAuth2AuthorizationServiceImpl(redisTemplate);
    }
    
    @Bean
    public AuthenticationEntryPoint resourceAuthExceptionEntryPoint() {
        return new ResourceAuthExceptionEntryPoint();
    }
    
}
