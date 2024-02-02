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

package com.wemirr.framework.security.configuration.server.support.wechat;

import com.wemirr.framework.security.constant.SecurityConstants;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.util.MultiValueMap;

/**
 * 微信登录请求token入参处理类
 *
 * @author Levin
 */
public class WechatCodeGrantRequestEntityConverter extends OAuth2AuthorizationCodeGrantRequestEntityConverter {
    
    @Override
    protected MultiValueMap<String, String> createParameters(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        // 如果是微信登录，获取token时携带appid参数与secret参数
        MultiValueMap<String, String> parameters = super.createParameters(authorizationCodeGrantRequest);
        String registrationId = authorizationCodeGrantRequest.getClientRegistration().getRegistrationId();
        if (SecurityConstants.THIRD_LOGIN_WECHAT.equals(registrationId)) {
            // 如果当前是微信登录，携带appid和secret
            parameters.add(SecurityConstants.WECHAT_PARAMETER_APPID, authorizationCodeGrantRequest.getClientRegistration().getClientId());
            parameters.add(SecurityConstants.WECHAT_PARAMETER_SECRET, authorizationCodeGrantRequest.getClientRegistration().getClientSecret());
        }
        return parameters;
    }
    
}
