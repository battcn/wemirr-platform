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

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreFeignAuthorize;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

/**
 * 服务间接口不鉴权处理逻辑
 *
 * @author Levin
 */
@Slf4j
@Aspect
@AllArgsConstructor
@EnableConfigurationProperties(SecurityExtProperties.class)
public class SecurityInnerServiceAspect implements Ordered {
    
    private static final String SEPARATOR = ",";
    private final SecurityExtProperties properties;
    
    @Around("@annotation(authorize)")
    public Object around(ProceedingJoinPoint point, IgnoreFeignAuthorize authorize) throws Throwable {
        if (authorize.global()) {
            return point.proceed();
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ipAddress = JakartaServletUtil.getClientIP(request);
        if (ipAddress.contains(SEPARATOR)) {
            String[] ips = ipAddress.split(SEPARATOR);
            for (String ip : ips) {
                boolean innerIp = NetUtil.isInnerIP(ip);
                if (innerIp) {
                    return point.proceed();
                }
            }
        } else {
            boolean innerIp = NetUtil.isInnerIP(ipAddress);
            if (innerIp) {
                return point.proceed();
            }
        }
        final SecurityExtProperties.InnerService innerService = properties.getInnerService();
        List<String> whiteLists = innerService.getWhiteLists();
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        String signatureName = point.getSignature().getName();
        if (!whiteLists.contains(ipAddress)) {
            log.warn("访问受限，非白名单，[IP] - [{}] - [方法] - [{}]", ipAddress, signatureName);
            throw CheckedException.badRequest(httpStatus.value(), httpStatus.getReasonPhrase());
        }
        return point.proceed();
    }
    
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
    
}
