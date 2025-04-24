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

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.wemirr.framework.commons.exception.ResourceNotFoundException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Levin
 */
@EnableConfigurationProperties(SecurityExtProperties.class)
public class OAuth2AutoConfiguration implements WebMvcConfigurer {

    @Resource
    private SecurityExtProperties extProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 第一个拦截器：检查404
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
                if (!(handler instanceof HandlerMethod)) {
                    // 可以添加更多检查
                    String method = request.getMethod();
                    String uri = request.getRequestURI();
                    throw new ResourceNotFoundException(String.format("资源不存在: %s %s", method, uri));
                }
                return true; // 继续执行后续拦截器和控制器
            }
        }).addPathPatterns("/**");
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 拦截的 path 列表，可以写多个 /**
            SaRouter.match("/**")
                    .notMatch(extProperties.getDefaultIgnoreUrls())
                    .notMatch(extProperties.getIgnore().getResourceUrls())
                    .check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
    }
}
