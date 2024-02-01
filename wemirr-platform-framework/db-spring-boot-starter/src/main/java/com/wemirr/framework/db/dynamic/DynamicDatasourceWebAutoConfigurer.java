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

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.db.properties.DatabaseProperties;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Levin
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "ds-interceptor", havingValue = "false")
public class DynamicDatasourceWebAutoConfigurer implements WebMvcConfigurer {
    
    @Resource
    private DatabaseProperties properties;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            
            /**
             * 在请求处理之前进行调用（Controller方法调用之前）
             */
            @Override
            public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
                String requestUri = request.getRequestURI();
                DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
                String tenantCode = request.getHeader(multiTenant.getTenantCodeColumn());
                if (StringUtils.isBlank(tenantCode)) {
                    tenantCode = request.getParameter(multiTenant.getTenantCodeColumn());
                }
                String prefix = multiTenant.getDsPrefix();
                String dsKey = multiTenant.getDefaultDsName();
                if (!multiTenant.isSuperTenant(tenantCode)) {
                    dsKey = prefix + tenantCode;
                }
                log.info("经过多数据源Interceptor,数据源是{},路径是{}", dsKey, requestUri);
                DynamicDataSourceContextHolder.push(dsKey);
                return true;
            }
            
            /**
             * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
             */
            @Override
            public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) {
                DynamicDataSourceContextHolder.clear();
            }
        });
    }
}