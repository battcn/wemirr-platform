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

package com.wemirr.platform.gateway.configuration.dynamic;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.wemirr.platform.gateway.route.NacosRouteDefinitionRepository;
import com.wemirr.platform.gateway.route.RedisRouteDefinitionRepository;
import com.wemirr.platform.gateway.route.RedisRouteDynamicGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 动态路由自动装配
 *
 * @author Levin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicRouteProperties.class)
@ConditionalOnProperty(prefix = "spring.cloud.gateway", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DynamicRouteAutoConfiguration {
    
    private final ApplicationEventPublisher publisher;
    
    @Bean
    @ConditionalOnProperty(prefix = "spring.cloud.gateway.dynamic-route", name = "type", havingValue = "redis", matchIfMissing = true)
    public RedisRouteDefinitionRepository redisRouteDefinitionRepository(StringRedisTemplate redisTemplate) {
        log.info(" init redisRouteDefinitionRepository ");
        return new RedisRouteDefinitionRepository(redisTemplate);
    }
    
    @Bean
    @ConditionalOnProperty(prefix = "spring.cloud.gateway.dynamic-route", name = "type", havingValue = "redis", matchIfMissing = true)
    public RedisRouteDynamicGatewayService redisRouteDynamicGatewayService(RedisRouteDefinitionRepository redisRouteDefinitionRepository) {
        log.info(" init redisRouteDynamicGatewayService ");
        return new RedisRouteDynamicGatewayService(publisher, redisRouteDefinitionRepository);
    }
    
    @Bean
    @ConditionalOnProperty(prefix = "spring.cloud.gateway.dynamic-route", name = "type", havingValue = "nacos")
    public NacosRouteDefinitionRepository nacosRouteDefinitionRepository(NacosConfigManager nacosConfigManager) {
        log.info(" init nacosRouteDefinitionRepository ");
        return new NacosRouteDefinitionRepository(publisher, nacosConfigManager);
    }
    
}
