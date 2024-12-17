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

package com.wemirr.framework.boot.log.configuration;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.wemirr.framework.boot.log.AccessLogInfo;
import com.wemirr.framework.boot.log.AccessLogProperties;
import com.wemirr.framework.boot.log.AccessLogUtil;
import com.wemirr.framework.boot.log.event.AccessLogEvent;
import com.wemirr.framework.boot.log.handler.AbstractLogHandler;
import com.wemirr.framework.commons.RegionUtils;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 操作日志切面，使用Spring事件机制异步入库。
 * <p>
 * 该类用于拦截带有 @AccessLog 注解的方法，并记录相关操作日志。日志信息包括请求的参数、响应结果、异常信息等。
 * 所有日志记录过程通过异步事件处理，避免影响业务流程。
 *
 * @author Levin
 */
@Aspect
public class AccessLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(AccessLogAspect.class);
    private static final int MAX_LENGTH = 65535; // 限制日志内容的最大长度
    private static final TransmittableThreadLocal<AccessLogInfo> THREAD_LOCAL = new TransmittableThreadLocal<>();
    private static final String USER_AGENT = "User-Agent";

    @Resource
    private AuthenticationContext context; // 认证上下文，用于获取当前用户信息
    @Resource
    private AbstractLogHandler abstractLogHandler; // 日志处理器，用于处理和存储日志
    @Resource
    private AccessLogProperties accessLogProperties; // 配置文件，获取日志相关配置

    /**
     * 定义切点：拦截所有 public 方法，且该方法带有 @AccessLog 注解。
     */
    @Pointcut("execution(public * com.wemirr..*.*(..)) && @annotation(com.wemirr.framework.commons.annotation.log.AccessLog)")
    public void accessLogAspect() {
    }

    /**
     * 获取当前线程的日志信息，如果线程本地没有记录则创建一个新的日志信息。
     *
     * @return 当前线程的 AccessLogInfo 对象
     */
    private AccessLogInfo getLogInfo() {
        return Optional.ofNullable(THREAD_LOCAL.get()).orElseGet(AccessLogInfo::new);
    }

    /**
     * 安全执行日志记录操作，捕获异常避免影响主流程。
     *
     * @param action 执行的操作
     */
    private void safeExecute(Consumer<Void> action) {
        try {
            action.accept(null);
        } catch (Exception e) {
            logger.warn("日志记录异常", e);
            // 清除线程局部变量
            THREAD_LOCAL.remove();
        }
    }

    /**
     * 处理正常返回结果，记录日志信息。
     *
     * @param joinPoint 切点信息
     * @param ret       返回结果
     */
    @AfterReturning(returning = "ret", pointcut = "accessLogAspect()")
    public void handleReturn(JoinPoint joinPoint, Object ret) {
        safeExecute(ignore -> {
            AccessLog annotation = AccessLogUtil.getTargetAnnotation(joinPoint);
            if (annotation == null) {
                return;
            }
            AccessLogInfo log = getLogInfo();
            log.setStartTime(Instant.now()); // 记录操作开始时间
            if (ret instanceof Result<?> result && !result.isSuccessful()) {
                log.setMessage(result.getMessage()); // 记录失败的消息
            }
            if (annotation.response()) {
                log.setResponse(getSafeText(JSON.toJSONString(ret, JSONWriter.Feature.WriteMapNullValue)));
            }
            publishEvent(log); // 发布日志事件
        });
    }

    /**
     * 处理异常情况，记录异常日志信息。
     *
     * @param joinPoint 切点信息
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "accessLogAspect()", throwing = "e")
    public void handleException(JoinPoint joinPoint, Throwable e) {
        safeExecute(ignore -> {
            AccessLog annotation = AccessLogUtil.getTargetAnnotation(joinPoint);
            if (annotation == null) {
                return;
            }
            AccessLogInfo log = getLogInfo();
            HttpServletRequest request = getRequest();
            log.setRequest(getArgs(annotation, joinPoint.getArgs(), request));
            log.setMessage(getErrorMessage(e)); // 记录错误信息
            log.setResponse(e.getMessage()); // 记录异常消息
            publishEvent(log); // 发布日志事件
        });
    }

    /**
     * 记录日志信息，在方法执行前准备好相关数据。
     *
     * @param joinPoint 切点信息
     */
    @Before("accessLogAspect()")
    public void recordLog(JoinPoint joinPoint) {
        safeExecute(ignore -> {
            AccessLog annotation = AccessLogUtil.getTargetAnnotation(joinPoint);
            if (annotation == null) {
                return;
            }
            populateLogDetails(annotation, joinPoint); // 填充日志详细信息
        });
    }

    /**
     * 填充日志的详细信息，包括用户信息、请求参数、IP等。
     *
     * @param annotation AccessLog 注解对象
     * @param joinPoint  切点信息
     */
    private void populateLogDetails(AccessLog annotation, JoinPoint joinPoint) {
        AccessLogInfo log = getLogInfo();
        if (context != null) {
            log.setTenantId(context.tenantId());
            log.setTenantCode(context.tenantCode());
            log.setCreatedBy(context.userId());
            log.setCreatedName(context.nickName());
        }
        HttpServletRequest request = getRequest();
        log.setTrace(MDC.get(accessLogProperties.getTrace()));
        log.setModule(annotation.module());
        log.setDescription(annotation.description());
        log.setAction(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        log.setRequest(getArgs(annotation, joinPoint.getArgs(), request));
        log.setToken(getRequestToken(request));
        log.setIp(JakartaServletUtil.getClientIP(request));
        log.setLocation(RegionUtils.getRegion(log.getIp()));
        log.setUri(URLUtil.getPath(request.getRequestURI()));
        log.setHttpMethod(request.getMethod());
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(USER_AGENT));
        log.setEngine(userAgent.getEngine().getName());
        log.setOs(userAgent.getOs().getName());
        log.setPlatform(userAgent.getPlatform().getName());
        log.setBrowser(userAgent.getBrowser().getName());
        log.setStartTime(Instant.now());
        // 设置线程局部变量
        THREAD_LOCAL.set(log);
    }

    /**
     * 获取当前请求对象。
     *
     * @return HttpServletRequest 请求对象
     */
    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取请求参数，转换为JSON格式。
     *
     * @param annotation AccessLog 注解对象
     * @param args       方法参数
     * @param request    请求对象
     * @return JSON 格式的请求参数
     */
    private String getArgs(AccessLog annotation, Object[] args, HttpServletRequest request) {
        if (!annotation.request() || StrUtil.contains(request.getContentType(), MediaType.MULTIPART_FORM_DATA_VALUE)) {
            return "";
        }
        try {
            return JSONObject.toJSONString(args, JSONWriter.Feature.WriteMapNullValue);
        } catch (Exception e) {
            logger.warn("参数解析失败", e);
            return "参数解析异常";
        }
    }

    /**
     * 获取异常信息的详细描述。
     *
     * @param e 异常对象
     * @return 错误信息
     */
    private String getErrorMessage(Throwable e) {
        return e instanceof CheckedException ? e.getLocalizedMessage() : ExceptionUtil.stacktraceToString(e, MAX_LENGTH);
    }

    /**
     * 截取指定长度的字符串，防止日志内容过长。
     *
     * @param text 要处理的文本
     * @return 截取后的字符串
     */
    private String getSafeText(String text) {
        if (text != null && text.length() > MAX_LENGTH) {
            logger.warn("响应内容过长，长度: {}", text.length());
            return text.substring(0, MAX_LENGTH); // 截取最大长度
        }
        return text;
    }

    /**
     * 获取请求中的Token，可能从header、参数或者请求属性中获取。
     *
     * @param request 请求对象
     * @return Token值
     */
    private String getRequestToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessLogProperties.getToken()))
                .orElseGet(() -> Optional.ofNullable(request.getParameter(accessLogProperties.getToken()))
                        .orElse((String) request.getAttribute(accessLogProperties.getToken())));
    }

    /**
     * 发布日志事件，异步处理日志。
     *
     * @param log 日志信息
     */
    private void publishEvent(AccessLogInfo log) {
        log.setEndTime(Instant.now());
        log.setDuration(Duration.between(log.getStartTime(), log.getEndTime()).toMillis());
        abstractLogHandler.handler(log);
        // 发布日志事件
        SpringUtil.publishEvent(new AccessLogEvent(log));
    }
}
