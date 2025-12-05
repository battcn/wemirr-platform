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
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Optional;

/**
 * 平台网关策略路由过滤器 (集成 Micrometer Tracing)
 * 集成了 TraceId (Baggage)、黑白名单、限流、请求耗时记录等功能
 *
 * @author Levin
 */
@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class PlatformGatewayStrategyRouteFilter implements GlobalFilter, Ordered {


    /**
     * 业务透传的 Request ID 头
     */
    private static final String HEADER_REQUEST_ID = "x-request-id";
    private static final long SLOW_REQUEST_THRESHOLD = 1000;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String requestId = StrUtil.blankToDefault(exchange.getRequest().getHeaders().getFirst(HEADER_REQUEST_ID), IdUtil.fastSimpleUUID());
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header(HEADER_REQUEST_ID, requestId).build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        long startTime = System.currentTimeMillis();
        return chain.filter(mutatedExchange)
                .then(Mono.fromRunnable(() -> {
                    try {
                        MDC.put(HEADER_REQUEST_ID, requestId);
                        logRequest(mutatedExchange, startTime);
                    } finally {
                        MDC.remove(HEADER_REQUEST_ID);
                    }
                }));
    }

    private void logRequest(ServerWebExchange exchange, long startTime) {
        long executeTime = System.currentTimeMillis() - startTime;
        int statusCode = Optional.ofNullable(exchange.getResponse().getStatusCode()).map(HttpStatusCode::value).orElse(0);
        String method = exchange.getRequest().getMethod().name();
        String path = exchange.getRequest().getURI().getPath();
        String clientIp = getClientIp(exchange.getRequest());

        String serviceName = "unknown";
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        if (route != null) {
            serviceName = route.getUri().getHost();
        }
        URI targetUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        String targetNode = "unknown";
        if (targetUri != null) {
            targetNode = targetUri.getAuthority();
        }
        if (executeTime >= SLOW_REQUEST_THRESHOLD) {
            log.warn("[慢请求] 服务={}, 节点={}, 客户端IP={}, 耗时={}ms, 状态码={}, 方法={}, 路径={}",
                    serviceName, targetNode, clientIp, executeTime, statusCode, method, path);
        } else {
            log.info("[请求] 服务={}, 节点={}, 客户端IP={}, 耗时={}ms, 状态码={}, 方法={}, 路径={}",
                    serviceName, targetNode, clientIp, executeTime, statusCode, method, path);
        }
    }

    private String getClientIp(ServerHttpRequest request) {
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            if (remoteAddress != null) {
                ip = remoteAddress.getAddress().getHostAddress();
            }
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 5;
    }
}