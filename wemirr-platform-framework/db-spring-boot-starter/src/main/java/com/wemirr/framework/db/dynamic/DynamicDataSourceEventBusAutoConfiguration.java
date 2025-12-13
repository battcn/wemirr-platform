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

package com.wemirr.framework.db.dynamic;

import com.baomidou.dynamic.datasource.processor.DsJakartaHeaderProcessor;
import com.baomidou.dynamic.datasource.processor.DsJakartaSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.wemirr.framework.db.dynamic.core.DynamicDataSourceEventPublisher;
import com.wemirr.framework.db.dynamic.core.local.DynamicDataSourceLocalListener;
import com.wemirr.framework.db.dynamic.core.local.DynamicInstanceApplicationEvent;
import com.wemirr.framework.db.dynamic.core.redis.RedisDynamicDataSourceEventListener;
import com.wemirr.framework.db.dynamic.core.redis.RedisDynamicDataSourceEventPublisher;
import com.wemirr.framework.db.dynamic.feign.TenantFeignClient;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.redis.plus.listener.MessageEventListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 如果要实现自定义 database 库名的话 也很容易
 *
 * @author levin
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "type", havingValue = "datasource")
@EnableConfigurationProperties(DatabaseProperties.class)
public class DynamicDataSourceEventBusAutoConfiguration {

    private static final String UNDEFINED = "undefined";

    @Bean
    public DynamicDataSourceHandler tenantDynamicDataSourceProcess() {
        return new DynamicDataSourceHandler();
    }

    @Bean(initMethod = "init")
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "strategy", havingValue = "feign")
    public DynamicDataSourceLoad tenantDynamicDataSourceLoad(DynamicDataSourceHandler handler, TenantFeignClient tenantFeignClient) {
        return new DynamicDataSourceLoad(handler, tenantFeignClient);
    }

    @Bean
    @Order(value = Integer.MIN_VALUE)
    public ApplicationListener<DynamicInstanceApplicationEvent> applicationListener(DynamicDataSourceHandler handler) {
        return new DynamicDataSourceLocalListener(handler);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "db-notify", havingValue = "redis")
    public DynamicDataSourceEventPublisher redisDynamicDatasourcePublish(StringRedisTemplate redisTemplate) {
        return new RedisDynamicDataSourceEventPublisher(redisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "db-notify", havingValue = "redis")
    @Order(value = Integer.MIN_VALUE)
    public MessageEventListener redisDynamicDataSourceEventListener(DynamicDataSourceHandler handler) {
        return new RedisDynamicDataSourceEventListener(handler);
    }

    @Bean
    @Primary
    public DsProcessor dsProcessor() {
        DsJakartaHeaderProcessor headerProcessor = new DsJakartaHeaderProcessor();
        DsJakartaSessionProcessor sessionProcessor = new DsJakartaSessionProcessor();
        DsSpelExpressionProcessor expressionProcessor = new DsSpelExpressionProcessor();
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return headerProcessor;
    }

    private String getTenantDb(HttpServletRequest request, DatabaseProperties.MultiTenant multiTenant, String tenantCode) {
        if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, UNDEFINED)) {
            log.debug("TenantCode is null,Switch the default datasource => {}", multiTenant.getDefaultDsName());
            return multiTenant.getDefaultDsName();
        }
        if (StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
            log.debug("TenantCode is super tenant,Switch the default datasource => {}", multiTenant.getDefaultDsName());
            return multiTenant.getDefaultDsName();
        }
        String db = multiTenant.getDsPrefix() + tenantCode;
        log.debug("TenantCode is => {} ,Switch the => {}, Uri => {}", tenantCode, db, request.getRequestURI());
        return db;
    }

}
