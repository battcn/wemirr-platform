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

package com.wemirr.platform.gateway.filter;

import cn.hutool.core.util.IdUtil;
import com.wemirr.platform.gateway.configuration.rule.BlacklistHelper;
import com.wemirr.platform.gateway.configuration.rule.LimitHelper;
import com.wemirr.platform.gateway.utils.MonoHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Primary;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * 平台网关策略路由过滤器
 * 集成了 TraceId、黑白名单、限流、请求耗时记录等功能
 *
 * @author Levin
 */
@Slf4j
@Primary
@Component
public class PlatformGatewayStrategyRouteFilter implements GlobalFilter {

    private static final String TRACE_ID = "n-d-trace-id";
    private static final long SLOW_REQUEST_THRESHOLD = 1000;

    @Resource
    private BlacklistHelper blacklistHelper;
    @Resource
    private LimitHelper limitHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 黑名单校验
        if (blacklistHelper.valid(exchange)) {
            return MonoHelper.wrap(exchange, "访问失败,您已进入黑名单");
        }

        // 限流校验
        if (limitHelper.hostTrace(exchange)) {
            return MonoHelper.wrap(exchange, "访问失败,已达到最大阈值");
        }
        // 生成并设置 TraceId
        final String traceId = IdUtil.fastSimpleUUID();
        MDC.put(TRACE_ID, traceId);

        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        // 获取请求信息（用于日志记录）
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();
        String requestMethod = request.getMethod().name();
        String remoteAddress = request.getRemoteAddress() != null ? request.getRemoteAddress().getAddress().getHostAddress() : "unknown";
        // 执行过滤链并记录请求耗时
        return chain.filter(exchange)
                .contextWrite(Context.of(TRACE_ID, traceId))
                .then(Mono.fromRunnable(() -> {
                    long executeTime = System.currentTimeMillis() - startTime;
                    int statusCode = exchange.getResponse().getStatusCode() != null ?
                            exchange.getResponse().getStatusCode().value() : 0;

                    // 慢请求使用 warn 级别，正常请求使用 info 级别
                    if (executeTime >= SLOW_REQUEST_THRESHOLD) {
                        log.warn("[慢请求] TraceId={}, 方法={}, 路径={}, 状态码={}, 耗时={}ms, IP={}",
                                traceId, requestMethod, requestPath, statusCode, executeTime, remoteAddress);
                    } else if (log.isInfoEnabled()) {
                        log.info("[请求] TraceId={}, 方法={}, 路径={}, 状态码={}, 耗时={}ms, IP={}",
                                traceId, requestMethod, requestPath, statusCode, executeTime, remoteAddress);
                    }
                }));
    }
}
