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

package com.wemirr.platform.authority.domain.baseinfo.converts;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.authority.domain.baseinfo.entity.RegisteredClientRef;
import com.wemirr.platform.authority.domain.baseinfo.resp.RegisteredClientRefResp;
import lombok.SneakyThrows;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
public class RegisteredClientRefConverts {
    
    public static final RegisteredClientRefConverts.RegisteredClientRef2RespConverts REGISTERED_CLIENT_REF_2_RESP_CONVERTS = new RegisteredClientRefConverts.RegisteredClientRef2RespConverts();
    
    public static class RegisteredClientRef2RespConverts implements BasePageConverts<RegisteredClientRef, RegisteredClientRefResp> {
        
        @SneakyThrows
        @Override
        public RegisteredClientRefResp convert(RegisteredClientRef source) {
            if (source == null) {
                return null;
            }
            RegisteredClientRefResp target = new RegisteredClientRefResp();
            target.setId(source.getId());
            target.setClientName(source.getClientName());
            target.setClientId(source.getClientId());
            target.setClientIdIssuedAt(source.getClientIdIssuedAt());
            target.setClientName(source.getClientName());
            target.setClientSecretExpiresAt(source.getClientSecretExpiresAt());
            target.setAuthorizationGrantTypes(StrUtil.split(source.getAuthorizationGrantTypes(), ','));
            target.setRedirectUris(source.getRedirectUris());
            target.setPostLogoutRedirectUris(source.getPostLogoutRedirectUris());
            target.setScopes(StrUtil.split(source.getScopes(), ','));
            final String tokenSettings = source.getTokenSettings();
            if (StrUtil.isNotBlank(tokenSettings)) {
                ObjectMapper objectMapper = new ObjectMapper();
                ClassLoader classLoader = JdbcRegisteredClientRepository.class.getClassLoader();
                List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
                objectMapper.registerModules(securityModules);
                objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
                final Map<String, Object> readValue = objectMapper.readValue(tokenSettings, new TypeReference<>() {
                });
                final TokenSettings settings = TokenSettings.withSettings(readValue).build();
                target.setAccessTokenTimeToLive(settings.getAccessTokenTimeToLive().toMinutes());
                target.setRefreshTokenTimeToLive(settings.getRefreshTokenTimeToLive().toMinutes());
                target.setAuthorizationCodeTimeToLive(settings.getAuthorizationCodeTimeToLive().toMinutes());
                target.setDeviceCodeTimeToLive(settings.getDeviceCodeTimeToLive().toMinutes());
            }
            return target;
        }
    }
    
}
