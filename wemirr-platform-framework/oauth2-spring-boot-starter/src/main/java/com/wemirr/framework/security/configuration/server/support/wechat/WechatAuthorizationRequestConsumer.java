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
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 自定义微信登录认证请求
 *
 * @author Levin
 */
public class WechatAuthorizationRequestConsumer implements Consumer<OAuth2AuthorizationRequest.Builder> {
    
    @Override
    public void accept(OAuth2AuthorizationRequest.Builder builder) {
        OAuth2AuthorizationRequest authorizationRequest = builder.build();
        Object registrationId = authorizationRequest.getAttribute(OAuth2ParameterNames.REGISTRATION_ID);
        if (Objects.equals(registrationId, SecurityConstants.THIRD_LOGIN_WECHAT)) {
            // 判断是否微信登录，如果是微信登录则将appid添加至请求参数中
            builder.additionalParameters((params) -> params.put(SecurityConstants.WECHAT_PARAMETER_FORCE_POPUP, true));
            builder.additionalParameters((params) -> params.put(SecurityConstants.WECHAT_PARAMETER_APPID, authorizationRequest.getClientId()));
        }
    }
    
}
