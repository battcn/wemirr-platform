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

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.jspecify.annotations.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Configuration
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory<PasswordDecoderFilter.Config> {
    
    private static final String REFRESH_TOKEN = "refresh_token";
    
    public PasswordDecoderFilter() {
        super(Config.class);
    }
    
    @Override
    public String name() {
        return "PasswordDecoderFilter";
    }
    
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // 1. 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), config.getLoginUrl())) {
                return chain.filter(exchange);
            }
            
            // 2. 刷新token类型，直接向下执行
            String grantType = request.getQueryParams().getFirst("grant_type");
            if (StrUtil.equals(REFRESH_TOKEN, grantType)) {
                return chain.filter(exchange);
            }
            // 获取原始请求参数
            Map<String, String> inParamsMap = new LinkedHashMap<>(exchange.getRequest().getQueryParams().toSingleValueMap());
            // String password = SecureUtil.aes(config.getEncodeKey().getBytes(StandardCharsets.UTF_8)).decryptStr(inParamsMap.get(config.getPassword()));
            String password = decryptStr(config, inParamsMap.get(config.getPassword()));
            // 替换或添加参数
            inParamsMap.put(config.getPassword(), password);
            
            // 构建新的 URI，包含修改后的参数
            URI updatedUri = buildUriWithParams(exchange.getRequest().getURI(), inParamsMap);
            
            // 使用新的 URI 构建请求
            ServerHttpRequest updatedRequest = exchange.getRequest().mutate().uri(updatedUri).build();
            
            // 构建新的 ServerWebExchange
            ServerWebExchange modifiedExchange = exchange.mutate().request(updatedRequest).build();
            
            // 继续过滤器链
            return chain.filter(modifiedExchange);
        };
    }
    
    private String decryptStr(Config config, String context) {
        // 将字符串转换为字节数组
        byte[] key = config.getEncodeKey().getBytes(StandardCharsets.UTF_8);
        byte[] iv = config.getEncodeKey().getBytes(StandardCharsets.UTF_8);
        // 创建 AES 实例并指定 CTR 模式（不需要填充）
        AES aes = new AES("CTR", Padding.NoPadding.name(), key, iv);
        return aes.decryptStr(context);
    }
    
    private URI buildUriWithParams(URI uri, Map<String, String> params) {
        try {
            String query = params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            
            return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), query, uri.getFragment());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error building URI with parameters", e);
        }
    }
    
    /**
     * 原文解密
     */
    private Function<String, Mono<String>> decryptAes(Config config) {
        return originalContext -> {
            // 获取请求密码并解密
            JSONObject inParamsMap = JSON.parseObject(originalContext);
            if (inParamsMap.containsKey(config.getPassword())) {
                String password = SecureUtil.aes(config.getEncodeKey().getBytes(StandardCharsets.UTF_8)).decryptStr(inParamsMap.getString(config.getPassword()));
                // 返回修改后报文字符
                inParamsMap.put(config.getPassword(), password);
            } else {
                log.error("解密失败,原始报文密码错误或者未加密:{}", originalContext);
            }
            return Mono.just(HttpUtil.toParams(inParamsMap, Charset.defaultCharset(), true));
        };
    }
    
    /**
     * 报文转换
     */
    private ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers,
                                                CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            
            @Override
            @NonNull
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }
            
            @Override
            @NonNull
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }
    
    @Data
    public static class Config {
        
        /**
         * 网关解密登录前端密码 秘钥
         */
        private String encodeKey;
        
        private String loginUrl = "/oauth2/token";
        private String password = "password";
    }
    
}
