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
package com.wemirr.framework.robot.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.Objects;

/**
 * 默认异常消息处理器
 * <p>
 *
 * @author Levin
 */
public class DefaultIErrorMessageHandler implements IErrorMessageHandler {
    
    /**
     * 换行
     */
    private final String LINE_BREAK = "\n";
    
    @Override
    public String message(JoinPoint joinPoint, Exception e) {
        StringBuilder error = new StringBuilder();
        error.append("Time: ").append(Instant.now());
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        final String ip = JakartaServletUtil.getClientIP(request);
        error.append(LINE_BREAK).append("IP: ").append(ip);
        Signature signature = joinPoint.getSignature();
        error.append(LINE_BREAK).append("Method: ").append(signature.getDeclaringTypeName()).append(".").append(signature.getName());
        error.append(LINE_BREAK).append("Args: ").append(JSON.toJSONString(joinPoint.getArgs()));
        error.append(LINE_BREAK).append("Exception: ").append(ExceptionUtil.stacktraceToString(e));
        return error.toString();
    }
    
    @Override
    public String message(Exception e) {
        return "Time: " + Instant.now() +
                LINE_BREAK + "Exception: " + ExceptionUtil.stacktraceToString(e);
    }
}
