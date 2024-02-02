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

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.processor.DsJakartaHeaderProcessor;
import com.baomidou.dynamic.datasource.processor.DsJakartaSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish;
import com.wemirr.framework.db.dynamic.core.local.DynamicDatasourceEvent;
import com.wemirr.framework.db.dynamic.core.local.DynamicDatasourceLocalListener;
import com.wemirr.framework.db.dynamic.core.redis.RedisDynamicDatasourceListener;
import com.wemirr.framework.db.dynamic.core.redis.RedisDynamicDatasourcePublish;
import com.wemirr.framework.db.dynamic.feign.TenantFeignClient;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.redis.plus.listener.MessageEventListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 如果要实现自定义 database 库名的话 也很容易
 *
 * @author levin
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "type", havingValue = "datasource")
@EnableConfigurationProperties(DatabaseProperties.class)
public class TenantDynamicDataSourceEventBusAutoConfiguration {
    
    @Bean
    public TenantDynamicDataSourceHandler tenantDynamicDataSourceProcess() {
        return new TenantDynamicDataSourceHandler();
    }
    
    @Bean(initMethod = "init")
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "strategy", havingValue = "feign")
    public TenantDynamicDataSourceLoad tenantDynamicDataSourceLoad(TenantDynamicDataSourceHandler handler, TenantFeignClient tenantFeignClient) {
        return new TenantDynamicDataSourceLoad(handler, tenantFeignClient);
    }
    
    @Bean
    @Order(value = Integer.MIN_VALUE)
    public ApplicationListener<DynamicDatasourceEvent> applicationListener(TenantDynamicDataSourceHandler handler) {
        return new DynamicDatasourceLocalListener(handler);
    }
    
    @Bean
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "db-notify", havingValue = "redis")
    public DynamicDatasourceEventPublish redisDynamicDatasourcePublish(StringRedisTemplate redisTemplate) {
        return new RedisDynamicDatasourcePublish(redisTemplate);
    }
    
    @Bean
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "db-notify", havingValue = "redis")
    @Order(value = Integer.MIN_VALUE)
    public MessageEventListener redisDynamicDatasourceListener(TenantDynamicDataSourceHandler handler) {
        return new RedisDynamicDatasourceListener(handler);
    }
    
    @Bean
    @Primary
    public DsProcessor dsProcessor(DatabaseProperties properties) {
        // 重写 DsHeaderProcessor
        DsProcessor contentProcessor = new DsProcessor() {
            
            private static final String CUSTOM_PREFIX = "#custom";
            
            @Override
            public boolean matches(String key) {
                return key.startsWith(CUSTOM_PREFIX);
            }
            
            @Override
            public String doDetermineDatasource(MethodInvocation invocation, String key) {
                ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
                DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
                if (attributes == null) {
                    log.debug("attributes为空,切换默认数据源 - {}", multiTenant.getDefaultDsName());
                    return multiTenant.getDefaultDsName();
                }
                HttpServletRequest request = attributes.getRequest();
                if (multiTenant.isUseTenantContent()) {
                    AuthenticationContext authenticationContext = SpringUtil.getBean(AuthenticationContext.class);
                    if (authenticationContext.anonymous()) {
                        log.debug("匿名用户,切换默认数据源 - {}", multiTenant.getDefaultDsName());
                        return multiTenant.getDefaultDsName();
                    }
                    String tenantCode = authenticationContext.tenantCode();
                    return getTenantDb(request, multiTenant, tenantCode);
                }
                String name = key.substring(8);
                String tenantCode = StringUtils.defaultIfBlank(request.getHeader(name), request.getParameter(name));
                log.info("name - {} - {}", name, tenantCode);
                return getTenantDb(request, multiTenant, tenantCode);
            }
        };
        DsJakartaHeaderProcessor headerProcessor = new DsJakartaHeaderProcessor();
        DsJakartaSessionProcessor sessionProcessor = new DsJakartaSessionProcessor();
        DsSpelExpressionProcessor expressionProcessor = new DsSpelExpressionProcessor();
        contentProcessor.setNextProcessor(headerProcessor);
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return contentProcessor;
    }
    
    private static final String UNDEFINED = "undefined";
    
    private String getTenantDb(HttpServletRequest request, DatabaseProperties.MultiTenant multiTenant, String tenantCode) {
        if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, UNDEFINED) || StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
            log.debug("tenantCode 为空或者为超级租户,切换默认数据源 - {}", multiTenant.getDefaultDsName());
            return multiTenant.getDefaultDsName();
        }
        String db = multiTenant.getDsPrefix() + tenantCode;
        log.debug("数据源切换至 - {} - {} - {}", tenantCode, db, request.getRequestURI());
        return db;
    }
    
}
