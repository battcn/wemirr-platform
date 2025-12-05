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

package com.wemirr.framework.feign.plugin.mock;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.feign.plugin.FeignPluginProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class FeignPluginInterceptor implements RequestInterceptor {

    private final FeignPluginProperties properties;

    /**
     * 定义这一组“永远需要透传”的默认 Header
     */
    private static final Set<String> CORE_HEADERS = new HashSet<>();

    static {
        CORE_HEADERS.add(HttpHeaders.ACCEPT_LANGUAGE);
        CORE_HEADERS.add(HttpHeaders.AUTHORIZATION);
        CORE_HEADERS.add(HttpHeaders.DATE);

        // --- 链路追踪 ---
        CORE_HEADERS.add("x-request-id");
        CORE_HEADERS.add("TraceId");
        CORE_HEADERS.add("SpanId");
        // 时区
        CORE_HEADERS.add("x-time-zone");
        CORE_HEADERS.add("x-tenant-id");
        CORE_HEADERS.add("x-mock-application");
    }

    @Override
    public void apply(RequestTemplate template) {
        if (!properties.isEnabled()) {
            return;
        }

        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Set<String> finalAllowedHeaders = new HashSet<>(CORE_HEADERS);
            // 追加 YAML 里配置的 Header (如果有)
            List<String> configHeaders = properties.getAllowedHeaders();
            if (configHeaders != null && !configHeaders.isEmpty()) {
                finalAllowedHeaders.addAll(configHeaders);
            }
            for (String headerName : finalAllowedHeaders) {
                String headerValue = request.getHeader(headerName);
                // 只有上游传了，才透传。上游没传，不瞎传。
                if (StrUtil.isBlank(headerValue)) {
                    continue;
                }
                template.header(headerName, headerValue);
            }
        }
        // Mock 逻辑
        if (properties.getMock() != null && properties.getMock().isEnabled()) {
            template.header("x-mock-application", "true");
        }
    }
}