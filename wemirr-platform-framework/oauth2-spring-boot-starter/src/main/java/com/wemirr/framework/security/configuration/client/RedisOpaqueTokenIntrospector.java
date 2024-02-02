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

package com.wemirr.framework.security.configuration.client;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.security.domain.UserInfoDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.security.Principal;
import java.util.Objects;

/**
 * @author Levin
 */
@Slf4j
public class RedisOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2AuthorizationService authorizationService = SpringUtil.getBean(OAuth2AuthorizationService.class);
        OAuth2Authorization oAuth2Authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
        if (Objects.isNull(oAuth2Authorization)) {
            throw new InvalidBearerTokenException(token);
        }
        // 客户端模式默认返回
        if (AuthorizationGrantType.CLIENT_CREDENTIALS.equals(oAuth2Authorization.getAuthorizationGrantType())) {
            return new DefaultOAuth2AuthenticatedPrincipal(oAuth2Authorization.getPrincipalName(), oAuth2Authorization.getAttributes(), AuthorityUtils.NO_AUTHORITIES);
        }
        try {
            final Object object = Objects.requireNonNull(oAuth2Authorization).getAttributes().get(Principal.class.getName());
            if (object instanceof UsernamePasswordAuthenticationToken principal) {
                return (OAuth2AuthenticatedPrincipal) principal.getPrincipal();
            }
            JSONObject principal = (JSONObject) Objects.requireNonNull(oAuth2Authorization).getAttributes().get(Principal.class.getName());
            return principal.getObject("principal", UserInfoDetails.class);
        } catch (Exception ex) {
            log.error("introspect error {}", ex.getLocalizedMessage());
        }
        return null;
    }
}
