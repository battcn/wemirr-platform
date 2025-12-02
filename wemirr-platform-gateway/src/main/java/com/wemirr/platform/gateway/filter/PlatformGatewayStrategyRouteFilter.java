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
import com.wemirr.platform.gateway.configuration.rule.BlacklistHelper;
import com.wemirr.platform.gateway.configuration.rule.LimitHelper;
import com.wemirr.platform.gateway.utils.MonoHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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

    private static final String TRACE_ID_HEADER = "n-d-trace-id";
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
        // 生成 TraceId (如果没有则生成，有则沿用，保证链路连贯)
        final String traceId = StrUtil.blankToDefault(exchange.getRequest().getHeaders().getFirst(TRACE_ID_HEADER), IdUtil.fastSimpleUUID());
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 构建新的 Request (将 TraceId 放入 Header 传给下游微服务)
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header(TRACE_ID_HEADER, traceId).build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        return chain.filter(mutatedExchange)
                .then(Mono.fromRunnable(() -> {
                    long executeTime = System.currentTimeMillis() - startTime;
                    int statusCode = mutatedExchange.getResponse().getStatusCode() != null ? mutatedExchange.getResponse().getStatusCode().value() : 0;
                    // 获取 Request 信息
                    String method = mutatedExchange.getRequest().getMethod().name();
                    String path = mutatedExchange.getRequest().getURI().getPath();
                    // 这里 log 输出时，MDC 还是空的，需要配合下面的第二步才能生效
                    if (executeTime >= SLOW_REQUEST_THRESHOLD) {
                        log.warn("[慢请求] traceId => {},耗时={}ms, 状态码={},方法名={}, 路径={}", traceId, executeTime, statusCode, method, path);
                    } else {
                        log.info("[请求] traceId => {},耗时={}ms, 状态码={},方法名={}, 路径={}", traceId, executeTime, statusCode, method, path);
                    }
                }))
                .contextWrite(Context.of(TRACE_ID_HEADER, traceId)).then();
    }
}
