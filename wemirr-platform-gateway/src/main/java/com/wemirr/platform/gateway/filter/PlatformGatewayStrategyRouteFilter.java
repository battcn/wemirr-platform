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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.net.InetSocketAddress;
import java.net.URI;

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

    private static final String TRACE_ID_HEADER = "x-request-id";
    private static final long SLOW_REQUEST_THRESHOLD = 1000;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 生成 TraceId
        final String traceId = StrUtil.blankToDefault(exchange.getRequest().getHeaders().getFirst(TRACE_ID_HEADER), IdUtil.fastSimpleUUID());
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 构建新的 Request (传递 TraceId)
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header(TRACE_ID_HEADER, traceId).build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        return chain.filter(mutatedExchange)
                .then(Mono.fromRunnable(() -> {
                    MDC.put(TRACE_ID_HEADER, traceId);
                    long executeTime = System.currentTimeMillis() - startTime;
                    int statusCode = mutatedExchange.getResponse().getStatusCode() != null ? mutatedExchange.getResponse().getStatusCode().value() : 0;
                    // 1. 获取基本请求信息
                    String method = mutatedExchange.getRequest().getMethod().name();
                    String path = mutatedExchange.getRequest().getURI().getPath();

                    // 2. 获取客户端 IP
                    String clientIp = getClientIp(mutatedExchange.getRequest());

                    // 3. 获取目标服务名 (Gateway 路由匹配后会放入 Attribute 中)
                    String serviceName = "unknown";
                    Route route = mutatedExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                    if (route != null) {
                        // 如果是 lb://iam-service，getHost() 就是 iam-service
                        serviceName = route.getUri().getHost();
                    }

                    // 4. 获取实际转发的具体节点 IP (包含负载均衡后的真实IP和端口)
                    URI targetUri = mutatedExchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
                    String targetNode = "unknown";
                    if (targetUri != null) {
                        targetNode = targetUri.getAuthority();
                    }
                    // 日志输出
                    if (executeTime >= SLOW_REQUEST_THRESHOLD) {
                        log.warn("[慢请求] 服务={}, 节点={}, 客户端IP={}, 耗时={}ms, 状态码={}, 方法={}, 路径={}, traceId={}",
                                serviceName, targetNode, clientIp, executeTime, statusCode, method, path, traceId);
                    } else {
                        log.info("[请求] 服务={}, 节点={}, 客户端IP={}, 耗时={}ms, 状态码={}, 方法={}, 路径={}, traceId={}",
                                serviceName, targetNode, clientIp, executeTime, statusCode, method, path, traceId);
                    }
                }))
                // 【重要】contextWrite 必须放在最后，才能覆盖上面的 then 逻辑
                .contextWrite(Context.of(TRACE_ID_HEADER, traceId)).then();
    }

    /**
     * 获取客户端 IP 辅助方法
     */
    private String getClientIp(ServerHttpRequest request) {
        // 优先获取 X-Forwarded-For (防止经过 Nginx 后拿不到真实 IP)
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
        // 对于多级代理的情况，第一个 IP 才是真实客户端 IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}

