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
package com.wemirr.framework.commons.exception;

import com.wemirr.framework.commons.entity.enums.IntEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

/**
 * 检查型异常
 *
 * @author Levin
 * @since 2019-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckedException extends RuntimeException {
    
    private static final int BAD_REQUEST = 400;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    
    /**
     * 规范的HTTP状态响应码,如400/403/503等
     */
    private transient int code;
    private transient Object[] args;
    
    /**
     * badRequest
     *
     * @param message 错误消息
     * @return 异常
     */
    public static CheckedException badRequest(String message) {
        return new CheckedException(BAD_REQUEST, message);
    }
    
    /**
     * badRequest 支持参数占位符（badRequest(订单 {0} 不存在,AP1001) 会被编译成 订单 AP1001 不存在）
     *
     * @param message 错误消息
     * @return 异常
     */
    public static CheckedException badRequest(String message, Object... arguments) {
        return new CheckedException(BAD_REQUEST, MessageFormat.format(message, arguments), arguments);
    }
    
    /**
     * badRequest
     *
     * @param intEnum 实现了 IntEnum 接口的任何类，意味着是可插拔的
     * @return 异常
     */
    public static CheckedException badRequest(IntEnum intEnum) {
        return new CheckedException(intEnum.type(), intEnum.desc());
    }
    
    public static CheckedException badRequest(IntEnum intEnum, String arg) {
        return new CheckedException(intEnum.type(), String.format(intEnum.desc(), arg));
    }
    
    public static CheckedException notFound(String message, Object... arguments) {
        return new CheckedException(NOT_FOUND, MessageFormat.format(message, arguments), arguments);
    }
    
    public static CheckedException notFound(IntEnum intEnum, String arg) {
        return new CheckedException(intEnum.type(), String.format(intEnum.desc(), arg));
    }
    
    public static CheckedException notFound(String message) {
        return new CheckedException(NOT_FOUND, message);
    }
    
    public static CheckedException notFound(IntEnum intEnum) {
        return new CheckedException(intEnum.type(), intEnum.desc());
    }
    
    public static CheckedException forbidden() {
        return forbidden("登录过期,请重新登录");
    }
    
    public static CheckedException forbidden(String message) {
        return new CheckedException(FORBIDDEN, "登录过期,请重新登录");
    }
    
    public CheckedException(String message) {
        super(message);
        this.setCode(BAD_REQUEST);
    }
    
    public CheckedException(String message, Throwable e) {
        super(message, e);
        this.setCode(BAD_REQUEST);
    }
    
    public CheckedException(int code, String message, Object... arguments) {
        super(message);
        this.setCode(code);
        this.setArgs(arguments);
    }
    
    public CheckedException(int code, String message, Object[] arguments, Throwable t) {
        super(message, t);
        this.code = code;
        this.args = arguments;
    }
}