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

package com.wemirr.framework.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * 校验验证码异常时抛出
 *
 * @author Levin
 */
@SuppressWarnings("ALL")
@Getter
public class OAuth2InvalidException extends AuthenticationException {
    
    private HttpStatus status;
    
    public OAuth2InvalidException(String msg) {
        super(msg);
        this.status = HttpStatus.BAD_REQUEST;
    }
    
    public OAuth2InvalidException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }
    
    public OAuth2InvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}