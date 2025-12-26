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

package com.wemirr.platform.iam.auth.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.iam.auth.configuration.ThirdAuthProperties;
import com.wemirr.platform.iam.auth.domain.dto.resp.ThirdAuthResp;
import com.wemirr.platform.iam.auth.domain.entity.UserThirdAccount;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import com.wemirr.platform.iam.system.repository.ThirdAccountMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ThirdAuthTemplate {
    
    private final ThirdAuthProperties thirdAuthProperties;
    private final ThirdAccountMapper thirdAccountMapper;
    
    private static final Map<ThirdAuthType, ThirdAuthService> AUTH_SERVICE_MAP = Maps.newConcurrentMap();
    
//    @PostConstruct
    public void init() {
        Map<ThirdAuthType, AuthConfig> configMap = thirdAuthProperties.getConfigMap();
        if (CollUtil.isEmpty(configMap)) {
            log.warn("""
                    \n============================= Third Auth =============================
                    \nThird-Auth 未进行任何平台配置,该功能不可用
                    \n============================= Third Auth =============================
                    """);
        }
        var beans = SpringUtil.getBeansOfType(ThirdAuthService.class);
        for (Map.Entry<String, ThirdAuthService> entry : beans.entrySet()) {
            ThirdAuthService entryValue = entry.getValue();
            AUTH_SERVICE_MAP.put(entryValue.platform(), entryValue);
        }
    }
    
    private ThirdAuthService thirdAuthService(ThirdAuthType type) {
        if (type == null) {
            throw CheckedException.notFound("未知的授权方式");
        }
        return AUTH_SERVICE_MAP.get(type);
    }
    
    public ThirdAuthResp authorize(ThirdAuthType type) {
        ThirdAuthService service = thirdAuthService(type);
        return service.authorize();
    }
    
    @DSTransactional(rollbackFor = Exception.class)
    public AuthUser callback(ThirdAuthType type, AuthCallback authCallback) {
        ThirdAuthService service = thirdAuthService(type);
        AuthResponse<AuthUser> login = service.authRequest().login(authCallback);
        if (!login.ok()) {
            throw CheckedException.badRequest("授权失败 {0}", login.getMsg());
        }
        AuthUser user = login.getData();
        AuthToken authToken = user.getToken();
        String accessToken = authToken.getAccessToken();
        var thirdAccount = thirdAccountMapper.selectOne(UserThirdAccount::getAccountId, user.getUuid(),
                UserThirdAccount::getType, type);
        if (thirdAccount != null) {
            thirdAccountMapper.updateById(UserThirdAccount.builder().id(thirdAccount.getId())
                    .email(user.getEmail()).avatar(user.getAvatar()).nickname(user.getNickname())
                    .accessToken(accessToken).refreshToken(authToken.getRefreshToken()).build());
        } else {
            thirdAccountMapper.insert(UserThirdAccount.builder().accountId(user.getUuid()).email(user.getEmail()).avatar(user.getAvatar())
                    .username(user.getUsername()).nickname(user.getNickname()).type(type)
                    .accessToken(accessToken).refreshToken(authToken.getRefreshToken()).build());
        }
        service.callback(user);
        return user;
    }
    
}
