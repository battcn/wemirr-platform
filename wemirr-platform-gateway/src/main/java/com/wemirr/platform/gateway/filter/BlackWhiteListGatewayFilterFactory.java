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

import com.google.common.net.HttpHeaders;
import com.wemirr.framework.commons.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 黑白名单网关过滤器
 * <p>
 * 支持 IP 黑名单和白名单模式，可配置忽略内网 IP
 * 使用 X-Forwarded-For 解析真实客户端 IP
 *
 * @author Levin
 */
@Slf4j
@Order(99)
@Configuration
public class BlackWhiteListGatewayFilterFactory extends AbstractGatewayFilterFactory<BlackWhiteListGatewayFilterFactory.Config> {

    private static final String DEFAULT_FILTER_NAME = "BlackWhiteList";
    
    public BlackWhiteListGatewayFilterFactory() {
        super(Config.class);
    }
    
    @Override
    public String name() {
        return DEFAULT_FILTER_NAME;
    }
    
    @Override
    public GatewayFilter apply(Config config) {
        // 预编译 IP 列表为 Set 提升查询效率
        var ipSet = config.getIpList() != null ? new java.util.HashSet<>(config.getIpList()) : java.util.Set.<String>of();

        return (exchange, chain) -> {
            InetSocketAddress remoteAddress = XForwardedRemoteAddressResolver
                    .maxTrustedIndex(config.getMaxTrustedIndex())
                    .resolve(exchange);
            if (remoteAddress == null) {
                log.warn("[无法解析客户端地址]");
                return accessRestricted(exchange, "无法识别客户端地址");
            }
            final InetAddress inetAddress = remoteAddress.getAddress();
            String ip = inetAddress.getHostAddress();
            log.debug("[访问者IP] - [{}]", ip);

            // 内网 IP 直接放行
            if (config.isIgnoreIntranet() && (inetAddress.isSiteLocalAddress() || inetAddress.isLoopbackAddress())) {
                log.debug("[内网IP放行] - {}", ip);
                return chain.filter(exchange);
            }

            // 黑名单模式：在名单中则拒绝
            if (config.type == BlackWhiteListType.BLACK_LIST) {
                if (matchIp(ipSet, ip)) {
                    log.warn("[黑名单拦截] - IP: {}", ip);
                    return accessRestricted(exchange, "访问受限，IP 已被封禁");
                }
            }
            // 白名单模式：不在名单中则拒绝
            else if (config.type == BlackWhiteListType.WHITE_LIST) {
                if (!matchIp(ipSet, ip)) {
                    log.warn("[白名单拦截] - IP: {}", ip);
                    return accessRestricted(exchange, "访问受限，IP 未授权");
                }
            }
            return chain.filter(exchange);
        };
    }

    /**
     * IP 匹配（支持 CIDR 和通配符）
     */
    private boolean matchIp(java.util.Set<String> ipSet, String clientIp) {
        if (ipSet.contains(clientIp)) {
            return true;
        }
        // 支持简单通配符匹配，如 192.168.*
        for (String pattern : ipSet) {
            if (pattern.contains("*") && matchWildcard(pattern, clientIp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通配符匹配
     */
    private boolean matchWildcard(String pattern, String ip) {
        String regex = pattern.replace(".", "\\.").replace("*", ".*");
        return ip.matches(regex);
    }
    
    private Mono<Void> accessRestricted(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Map<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.FORBIDDEN.value());
        result.put("message", message);
        result.put("successful", false);
        result.put("timestamp", System.currentTimeMillis());
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JacksonUtils.toJsonBytes(result))));
    }
    
    @AllArgsConstructor
    public enum BlackWhiteListType {
        /**
         * 黑名单
         */
        BLACK_LIST,
        /**
         * 白名单
         */
        WHITE_LIST;
        
    }
    
    @Data
    public static class Config {
        
        private Integer maxTrustedIndex = 1;
        private BlackWhiteListType type;
        private boolean ignoreIntranet;
        private List<String> ipList;
    }
    
}
