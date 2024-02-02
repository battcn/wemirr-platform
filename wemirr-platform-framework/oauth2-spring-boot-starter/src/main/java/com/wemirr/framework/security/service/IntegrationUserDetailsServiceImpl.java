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

package com.wemirr.framework.security.service;

import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthentication;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthenticator;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 基础用户信息表 服务实现类
 * </p>
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class IntegrationUserDetailsServiceImpl implements UserDetailsService {
    
    private final List<IntegrationAuthenticator> authenticators;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取当前request
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes()).map(req -> ((ServletRequestAttributes) req).getRequest())
                .orElseThrow(() -> new OAuth2InvalidException("Failed to get the current request."));
        final IntegrationAuthentication authentication = new IntegrationAuthentication(request);
        return Optional.ofNullable(this.authenticate(authentication)).orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
    }
    
    private UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators == null) {
            return null;
        }
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                return authenticator.authenticate(integrationAuthentication);
            }
        }
        return null;
    }
}
