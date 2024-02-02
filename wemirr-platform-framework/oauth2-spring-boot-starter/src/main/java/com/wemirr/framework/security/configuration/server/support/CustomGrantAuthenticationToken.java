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

package com.wemirr.framework.security.configuration.server.support;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.*;

/**
 * 自定义短信验证登录Token类
 *
 * @author Levin
 */
public class CustomGrantAuthenticationToken extends AbstractAuthenticationToken {
    
    @Getter
    private final HttpServletRequest request;
    /**
     * 本次登录申请的scope
     */
    private final Set<String> scopes;
    
    /**
     * 客户端认证信息
     */
    private final Authentication clientPrincipal;
    
    /**
     * 当前请求的参数
     */
    private final Map<String, Object> additionalParameters;
    
    /**
     * 认证方式
     */
    private final AuthorizationGrantType authorizationGrantType;
    
    public CustomGrantAuthenticationToken(HttpServletRequest request, AuthorizationGrantType authorizationGrantType,
                                          Authentication clientPrincipal,
                                          @Nullable Set<String> scopes,
                                          @Nullable Map<String, Object> additionalParameters) {
        super(Collections.emptyList());
        this.request = request;
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
        this.clientPrincipal = clientPrincipal;
        this.additionalParameters = Collections.unmodifiableMap(
                additionalParameters != null ? new HashMap<>(additionalParameters) : Collections.emptyMap());
        this.authorizationGrantType = authorizationGrantType;
    }
    
    @Override
    public Object getCredentials() {
        return null;
    }
    
    @Override
    public Object getPrincipal() {
        return clientPrincipal;
    }
    
    /**
     * 返回请求的scope(s)
     *
     * @return 请求的scope(s)
     */
    public Set<String> getScopes() {
        return this.scopes;
    }
    
    /**
     * 返回请求中的authorization grant type
     *
     * @return authorization grant type
     */
    public AuthorizationGrantType getAuthorizationGrantType() {
        return this.authorizationGrantType;
    }
    
    /**
     * 返回请求中的附加参数
     *
     * @return 附加参数
     */
    public Map<String, Object> getAdditionalParameters() {
        return this.additionalParameters;
    }
    
}