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

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An {@link OAuth2TokenCustomizer} to map claims from a federated identity to
 * the {@code id_token} produced by this authorization server.
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public final class CustomTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    
    private final SecurityExtProperties properties;
    private static final Set<String> ID_TOKEN_CLAIMS = Set.of(IdTokenClaimNames.ISS, IdTokenClaimNames.SUB, IdTokenClaimNames.AUD, IdTokenClaimNames.EXP,
            IdTokenClaimNames.IAT, IdTokenClaimNames.AUTH_TIME,
            IdTokenClaimNames.NONCE, IdTokenClaimNames.ACR, IdTokenClaimNames.AMR, IdTokenClaimNames.AZP, IdTokenClaimNames.AT_HASH, IdTokenClaimNames.C_HASH);
    
    @Override
    public void customize(JwtEncodingContext context) {
        if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
            Map<String, Object> thirdPartyClaims = extractClaims(context.getPrincipal());
            context.getClaims().claims(existingClaims -> {
                existingClaims.keySet().forEach(thirdPartyClaims::remove);
                ID_TOKEN_CLAIMS.forEach(thirdPartyClaims::remove);
                existingClaims.putAll(thirdPartyClaims);
            });
        }
        final JwtClaimsSet.Builder claims = context.getClaims();
        // 检查登录用户信息是不是OAuth2User，在token中添加loginType属性
        if (context.getPrincipal().getPrincipal()instanceof OAuth2User user) {
            Object loginType = user.getAttribute("loginType");
            if (loginType instanceof String) {
                // 同时检验是否为String和是否不为空
                claims.claim("loginType", loginType);
                Object uniqueId;
                if (SecurityConstants.THIRD_LOGIN_WECHAT.equals(loginType)) {
                    uniqueId = user.getAttribute("openid");
                } else {
                    uniqueId = user.getAttribute("id");
                }
                if (uniqueId != null) {
                    claims.claim("uniqueId", String.valueOf(uniqueId));
                }
            }
        }
        
        // 检查登录用户信息是不是UserDetails，排除掉没有用户参与的流程
        if (context.getPrincipal().getPrincipal()instanceof UserDetails user) {
            // 获取申请的scopes
            Set<String> scopes = context.getAuthorizedScopes();
            // 获取用户的权限
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            // 提取权限并转为字符串
            Set<String> authoritySet = Optional.ofNullable(authorities).orElse(Collections.emptyList()).stream()
                    // 获取权限字符串
                    .map(GrantedAuthority::getAuthority)
                    // 去重
                    .collect(Collectors.toSet());
            // 合并scope与用户信息
            authoritySet.addAll(scopes);
            // 将权限信息放入jwt的claims中（也可以生成一个以指定字符分割的字符串放入）
            claims.claim(SecurityConstants.CLAIM_AUTHORITIES, authoritySet);
            claims.claim(SecurityConstants.CLAIM_USERINFO, BeanUtil.beanToMap(user, properties.getServer().getJwtClaims()));
        }
    }
    
    private Map<String, Object> extractClaims(Authentication principal) {
        Map<String, Object> claims;
        if (principal.getPrincipal()instanceof OidcUser oidcUser) {
            OidcIdToken idToken = oidcUser.getIdToken();
            claims = idToken.getClaims();
        } else if (principal.getPrincipal()instanceof OAuth2User oauth2User) {
            claims = oauth2User.getAttributes();
        } else {
            claims = Collections.emptyMap();
        }
        
        return new HashMap<>(claims);
    }
    
}
