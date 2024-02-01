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

package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.authority.domain.baseinfo.entity.RegisteredClientRef;
import com.wemirr.platform.authority.domain.baseinfo.req.RegisteredClientRefReq;
import com.wemirr.platform.authority.repository.baseinfo.RegisteredClientRefMapper;
import com.wemirr.platform.authority.service.RegisteredClientRefService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class RegisteredClientRefServiceImpl extends SuperServiceImpl<RegisteredClientRefMapper, RegisteredClientRef> implements RegisteredClientRefService {
    
    private final RegisteredClientRepository registeredClientRepository;
    
    @Override
    public void registeredClient(RegisteredClientRefReq req) {
        final RegisteredClientRefReq.TokenSettingsReq token = req.getTokenSettings();
        final RegisteredClient registeredClient = RegisteredClient.withId(req.getClientId()).clientName(req.getClientName())
                .clientId(req.getClientId()).clientIdIssuedAt(req.getClientIdIssuedAt())
                .clientSecret(req.getClientSecret()).clientSecretExpiresAt(req.getClientSecretExpiresAt())
                .authorizationGrantTypes(grantTypes -> {
                    if (CollUtil.isNotEmpty(req.getAuthorizationGrantTypes())) {
                        final List<AuthorizationGrantType> list = req.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::new).toList();
                        grantTypes.addAll(list);
                    }
                })
                .redirectUri(req.getRedirectUris())
                .postLogoutRedirectUri(req.getRedirectUris())
                .scopes(x -> x.addAll(req.getScopes()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(token.getAccessTokenTimeToLive()))
                        .refreshTokenTimeToLive(Duration.ofMinutes(token.getRefreshTokenTimeToLive()))
                        // .authorizationCodeTimeToLive(Duration.ofMinutes(ObjUtil.defaultIfNull(token.getAuthorizationCodeTimeToLive(), 0L)))
                        // .deviceCodeTimeToLive(Duration.ofMinutes(ObjUtil.defaultIfNull(token.getDeviceCodeTimeToLive(), 0L)))
                        .build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        registeredClientRepository.save(registeredClient);
        
    }
    
    @Override
    public void deleteById(String id) {
        this.baseMapper.removeById(id);
    }
}
