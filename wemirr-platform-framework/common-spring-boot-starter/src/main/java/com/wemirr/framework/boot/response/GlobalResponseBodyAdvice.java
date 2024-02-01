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
package com.wemirr.framework.boot.response;

import com.google.common.collect.Lists;
import com.wemirr.framework.commons.annotation.IgnoreGlobalResponse;
import com.wemirr.framework.commons.entity.Result;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @author Levin
 **/
@Slf4j
@Configuration
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    
    private final static String REWRITE = "1";
    public final static String RESPONSE_DATA_REWRITE = "rewrite";
    private final static List<String> IGNORE_URLS = Lists.newArrayList("/v3/api-docs", "/v3/api-docs/swagger-config");
    
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> aClass,
                                  @NonNull ServerHttpRequest serverHttpRequest, @NonNull ServerHttpResponse serverHttpResponse) {
        final IgnoreGlobalResponse methodAnnotation = methodParameter.getMethodAnnotation(IgnoreGlobalResponse.class);
        if (methodAnnotation != null) {
            log.info("忽略全局包装Result 对象,原因是 -{}", methodAnnotation.description());
            return body;
        }
        final HttpHeaders requestHeaders = serverHttpRequest.getHeaders();
        String path = serverHttpRequest.getURI().getPath();
        // 判单当前请求是否需要经过Response统一结果封装
        String isReWrite = requestHeaders.containsKey(RESPONSE_DATA_REWRITE) ? requestHeaders.getFirst(RESPONSE_DATA_REWRITE) : REWRITE;
        serverHttpResponse.getHeaders().add(RESPONSE_DATA_REWRITE, REWRITE);
        if (IGNORE_URLS.contains(path)) {
            return body;
        }
        if (REWRITE.equals(isReWrite) || StringUtils.isBlank(isReWrite)) {
            if (body == null) {
                return Result.success();
            }
            if (body instanceof Result) {
                return body;
            }
            if (body instanceof Byte[]) {
                return body;
            }
            return Result.success(body);
        } else {
            return body;
        }
    }
    
    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
}