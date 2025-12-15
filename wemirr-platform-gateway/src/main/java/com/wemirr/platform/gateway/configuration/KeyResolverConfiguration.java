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

package com.wemirr.platform.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * 限流 Key 解析器配置
 * <p>
 * 提供多种限流策略：IP 限流、Token 限流、接口限流
 * 配合 Redis RateLimiter 使用
 *
 * @author Levin
 */
@Configuration
public class KeyResolverConfiguration {

    private static final String AUTHORIZATION = "Authorization";
    private static final String UNKNOWN_IP = "unknown";
    
    /**
     * IP 限流（默认策略）
     * <p>
     * 优先从 X-Forwarded-For 获取真实 IP，支持代理场景
     *
     * @return IP 限流解析器
     */
    @Primary
    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            String clientIp = getClientIp(exchange);
            return Mono.just(clientIp != null ? clientIp : UNKNOWN_IP);
        };
    }

    /**
     * 获取客户端真实 IP（支持代理）
     */
    private String getClientIp(org.springframework.web.server.ServerWebExchange exchange) {
        var request = exchange.getRequest();
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (ip == null || ip.isBlank() || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("X-Real-IP");
        }
        if (ip == null || ip.isBlank() || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            var remoteAddress = request.getRemoteAddress();
            ip = remoteAddress != null ? remoteAddress.getAddress().getHostAddress() : null;
        }
        // 多级代理取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
    
    /**
     * Token 限流
     * <p>
     * 基于 Authorization 请求头进行限流，适用于用户级别限流
     *
     * @return Token 限流解析器
     */
    @Bean(name = "tokenKeyResolver")
    public KeyResolver tokenKeyResolver() {
        return exchange -> {
            String token = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
            // 无 Token 时降级到 IP 限流
            return Mono.just(token != null && !token.isBlank() ? token : getClientIp(exchange));
        };
    }
    
    /**
     * 接口限流
     * <p>
     * 基于请求路径进行限流，适用于热点接口保护
     *
     * @return 接口限流解析器
     */
    @Bean(name = "apiKeyResolver")
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * 组合限流（IP + 接口）
     * <p>
     * 同一 IP 访问同一接口进行限流，更精细的控制
     *
     * @return 组合限流解析器
     */
    @Bean(name = "compositeKeyResolver")
    public KeyResolver compositeKeyResolver() {
        return exchange -> {
            String clientIp = getClientIp(exchange);
            String path = exchange.getRequest().getPath().value();
            return Mono.just((clientIp != null ? clientIp : UNKNOWN_IP) + ":" + path);
        };
    }
    
}
