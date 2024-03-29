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

package com.wemirr.framework.security.configuration.server.store;

import com.wemirr.framework.security.constant.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.ObjectUtils;

/**
 * 基于redis存储认证信息
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class RedisSecurityContextRepository implements SecurityContextRepository {
    
    private final RedisTokenStore<SecurityContext> redisTokenStore;
    
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    
    @Override
    @Deprecated
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return readSecurityContextFromRedis(requestResponseHolder.getRequest());
    }
    
    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String nonce = getNonce(request);
        if (ObjectUtils.isEmpty(nonce)) {
            return;
        }
        
        // 如果当前的context是空的，则移除
        SecurityContext emptyContext = this.securityContextHolderStrategy.createEmptyContext();
        if (emptyContext.equals(context)) {
            redisTokenStore.delete((SecurityConstants.RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce));
        } else {
            // 保存认证信息
            redisTokenStore.set((SecurityConstants.RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce), context, SecurityConstants.RedisConstants.DEFAULT_TIMEOUT_SECONDS);
        }
    }
    
    @Override
    public boolean containsContext(HttpServletRequest request) {
        String nonce = getNonce(request);
        if (ObjectUtils.isEmpty(nonce)) {
            return false;
        }
        // 检验当前请求是否有认证信息
        return redisTokenStore.get((SecurityConstants.RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce)) != null;
    }
    
    /**
     * 从redis中获取认证信息
     *
     * @param request 当前请求
     * @return 认证信息
     */
    private SecurityContext readSecurityContextFromRedis(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        
        String nonce = getNonce(request);
        if (ObjectUtils.isEmpty(nonce)) {
            return null;
        }
        // 根据缓存id获取认证信息
        return redisTokenStore.get((SecurityConstants.RedisConstants.SECURITY_CONTEXT_PREFIX_KEY + nonce));
    }
    
    /**
     * 先从请求头中找，找不到去请求参数中找，找不到获取当前session的id
     * 2023-07-11新增逻辑：获取当前session的sessionId
     *
     * @param request 当前请求
     * @return 随机字符串(sessionId)，这个字符串本来是前端生成，现在改为后端获取的sessionId
     */
    private String getNonce(HttpServletRequest request) {
        String nonce = request.getHeader(SecurityConstants.NONCE_HEADER_NAME);
        if (ObjectUtils.isEmpty(nonce)) {
            nonce = request.getParameter(SecurityConstants.NONCE_HEADER_NAME);
            HttpSession session = request.getSession(Boolean.FALSE);
            if (ObjectUtils.isEmpty(nonce) && session != null) {
                nonce = session.getId();
            }
        }
        return nonce;
    }
    
}
