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

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.SaTokenContext;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import com.wemirr.framework.commons.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Levin
 */
@Slf4j
@Order(-100)
@RestControllerAdvice
public class OAuth2ExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Result<?>> handlerException(NotLoginException e) {
        log.error("no-login => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.fail(HttpStatus.UNAUTHORIZED.value(), "登录会话失效,请重新登录"));
    }

    @ExceptionHandler(SaTokenException.class)
    public ResponseEntity<Result<?>> handlerException(SaTokenException e) {
        SaTokenContext context = SaHolder.getContext();
        if (context != null && context.isValid()) {
            log.error("sa-token => http request uri => {},message => {}", context.getRequest().getUrl(), e.getLocalizedMessage());
            return ResponseEntity.ok(Result.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
        return ResponseEntity.ok(Result.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }
}