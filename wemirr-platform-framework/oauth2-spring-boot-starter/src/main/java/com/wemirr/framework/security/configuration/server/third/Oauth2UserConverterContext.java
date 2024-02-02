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

package com.wemirr.framework.security.configuration.server.third;

import com.wemirr.framework.security.domain.OAuth2ThirdAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Map;

/**
 * 三方oauth2登录获取的用户信息转换处理
 *
 * @author vains
 */
@Component
@RequiredArgsConstructor
public class Oauth2UserConverterContext {
    
    /**
     * 注入所有实例，map的key是实例在ioc中的名字
     * 这里通过构造器注入所有Oauth2UserConverterStrategy的实例，实例名之前在编写时已经通过
     * {@link Component }注解指定过bean的名字，可以根据给定bean名称从map中获取对应的实例(如果存在)
     */
    private final Map<String, Oauth2UserConverter> userConverterStrategyMap;
    
    /**
     * 获取转换器实例
     *
     * @param loginType 三方登录方式
     * @return 转换器实例 {@link Oauth2UserConverter}
     */
    public Oauth2UserConverter getInstance(String loginType) {
        if (ObjectUtils.isEmpty(loginType)) {
            throw new UnsupportedOperationException("登录方式不能为空.");
        }
        Oauth2UserConverter userConverterStrategy = userConverterStrategyMap.get(loginType);
        if (userConverterStrategy == null) {
            throw new UnsupportedOperationException("不支持[" + loginType + "]登录方式获取用户信息转换器");
        }
        return userConverterStrategy;
    }
    
    /**
     * 根据登录方式获取转换器实例，使用转换器获取用户信息
     *
     * @param userRequest 获取三方用户信息入参
     * @param oAuth2User  三方登录获取到的认证信息
     * @return {@link OAuth2ThirdAccount}
     */
    public OAuth2ThirdAccount convert(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // 获取三方登录配置的registrationId，这里将他当做登录方式
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 转换用户信息
        OAuth2ThirdAccount oauth2ThirdAccount = this.getInstance(registrationId).convert(oAuth2User);
        // 获取AccessToken
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        // 设置token
        oauth2ThirdAccount.setCredentials(accessToken.getTokenValue());
        // 设置账号的方式
        oauth2ThirdAccount.setType(registrationId);
        Instant expiresAt = accessToken.getExpiresAt();
        if (expiresAt != null) {
            // token过期时间
            oauth2ThirdAccount.setCredentialsExpiresAt(expiresAt);
        }
        return oauth2ThirdAccount;
    }
    
}
