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

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.net.HttpHeaders;
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
import java.util.List;

/**
 * XForwardedRemoteAddressResolver
 * 黑白名单过滤器
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
        return (exchange, chain) -> {
            InetSocketAddress remoteAddress = XForwardedRemoteAddressResolver.maxTrustedIndex(1).resolve(exchange);
            final InetAddress inetAddress = remoteAddress.getAddress();
            String ip = inetAddress.getHostAddress();
            log.debug("[访问者IP地址] - [{}]", ip);
            if (config.isIgnoreIntranet() && inetAddress.isSiteLocalAddress()) {
                log.info("[忽略内网IP] - {}", inetAddress.isSiteLocalAddress());
                return chain.filter(exchange);
            }
            if (config.type == BlackWhiteListType.BLACK_LIST) {
                boolean access = config.getIpList().contains(ip);
                if (access) {
                    log.warn("[访问受限，该地址在黑名单列表] - [{}]", ip);
                    return accessRestricted(exchange);
                }
            } else if (config.type == BlackWhiteListType.WHITE_LIST) {
                boolean access = config.getIpList().contains(ip);
                if (access) {
                    return chain.filter(exchange);
                } else {
                    log.warn("[访问受限，该地址不在白名单列表] - [{}]", ip);
                    return accessRestricted(exchange);
                }
            }
            return chain.filter(exchange);
        };
    }
    
    private Mono<Void> accessRestricted(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        JSONObject result = new JSONObject();
        result.put("messageId", HttpStatus.FORBIDDEN.value());
        result.put("message", "访问受限，请联系管理员");
        result.put("successful", false);
        result.put("timestamp", System.currentTimeMillis());
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(result))));
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
