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

package com.wemirr.framework.boot.base;

import com.google.common.collect.Lists;
import com.wemirr.framework.commons.exception.CheckedException;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * 演示环境拦截器
 *
 * @author Levin
 */
@Slf4j
public class DemoProfileInterceptor implements HandlerInterceptor {
    
    private static final long MAX_ID = 10000000;
    private static final List<String> REJECT_POST = Lists.newArrayList("resources", "dictionaries");
    private static final List<String> REJECT_OPTION = Lists.newArrayList("users", "roles", "stations", "tenants", "databases", "applications", "change_password", "resources", "dictionaries");
    
    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
        if (request == null) {
            throw CheckedException.notFound("request is null");
        }
        final HttpMethod method = HttpMethod.valueOf(request.getMethod());
        final String uri = request.getRequestURI();
        if (method == HttpMethod.GET) {
            return true;
        }
        if (method == HttpMethod.POST) {
            for (String url : REJECT_POST) {
                if (StringUtils.contains(uri, url)) {
                    throw CheckedException.notFound("演示环境,禁止破坏性的数据新增");
                }
            }
        }
        for (String url : REJECT_OPTION) {
            if (StringUtils.contains(uri, url)) {
                throw CheckedException.notFound("禁止操作演示环境的核心数据,");
            }
        }
        final String strId = StringUtils.substringAfterLast(uri, "/");
        try {
            final int id = Integer.parseInt(strId);
            if (id < MAX_ID) {
                throw CheckedException.notFound("禁止操作演示环境的核心数据");
            }
        } catch (Exception e) {
            log.error("转换失败 - {}", e.getMessage());
        }
        log.debug("method - {},uri - {} - id - {}", method, uri, strId);
        return true;
    }
    
}
