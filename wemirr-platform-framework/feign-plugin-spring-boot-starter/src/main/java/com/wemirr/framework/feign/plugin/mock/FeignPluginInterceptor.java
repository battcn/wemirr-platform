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

import java.util.Collection;
import java.util.Enumeration;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class FeignPluginInterceptor implements RequestInterceptor {

    /**
     * 需要排除的头部字段
     */
    public static final String[] EXCLUDED_DEFAULT_HEADERS = {HttpHeaders.CONTENT_LENGTH, HttpHeaders.COOKIE};
    public final String X_MOCK_APPLICATION = "x-mock-application";
    public final String IGNORE_HEADER = "ignore-header";
    private final FeignPluginProperties properties;

    private boolean isExcluded(String headerKey) {
        for (String header : EXCLUDED_DEFAULT_HEADERS) {
            if (headerKey.contains(header) || StrUtil.equalsIgnoreCase(headerKey, header)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void apply(RequestTemplate template) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        final Collection<String> ignoreHeader = template.headers().get(IGNORE_HEADER);
        if (requestAttributes != null && properties.isEnabled()) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            final Enumeration<?> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                final String headerKey = (String) headerNames.nextElement();
                final String headerValue = request.getHeader(headerKey);
                if (isExcluded(headerKey) || ignoreHeader != null && ignoreHeader.contains(headerKey)) {
                    continue;
                }
                template.header(headerKey, headerValue);
            }
        }
        if (properties.getMock() != null && properties.getMock().isEnabled()) {
            log.debug("mock interceptor .....");
            template.header(X_MOCK_APPLICATION, "true");
        }
    }
}
