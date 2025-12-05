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

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.threadlocal.ThreadLocalHolder;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

/**
 * 专门用于设置 ThreadLocal 值的拦截器
 *
 * @author Levin
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    private static final String TRACE_ID_HEADER = "x-request-id";
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        // 从请求头中获取地区信息
        Locale locale = request.getLocale();
        ThreadLocalHolder.setLocal(locale);
        String traceId = request.getHeader(TRACE_ID_HEADER);
        if (StrUtil.isNotBlank(traceId)) {
            MDC.put(TRACE_ID_HEADER, traceId);
        }
        log.debug("http header locale - {}", locale);
        return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object object, Exception exception) {
        ThreadLocalHolder.clear();
        MDC.remove(TRACE_ID_HEADER);
    }
}
