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

package com.wemirr.framework.security.configuration.server.third.converts;

import com.wemirr.framework.security.configuration.server.third.Oauth2UserConverter;
import com.wemirr.framework.security.constant.SecurityConstants;
import com.wemirr.framework.security.domain.OAuth2ThirdAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信用户信息转换器
 *
 * @author vains
 */
@RequiredArgsConstructor
@Component(SecurityConstants.THIRD_LOGIN_WECHAT)
public class WechatUserConverter implements Oauth2UserConverter {
    
    @Override
    public OAuth2ThirdAccount convert(OAuth2User oAuth2User) {
        // 获取三方用户信息
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 转换至Oauth2ThirdAccount
        OAuth2ThirdAccount thirdAccount = new OAuth2ThirdAccount();
        thirdAccount.setUniqueId(String.valueOf(attributes.get("openid")));
        thirdAccount.setThirdUsername(oAuth2User.getName());
        thirdAccount.setType(SecurityConstants.THIRD_LOGIN_WECHAT);
        thirdAccount.setLocation(attributes.get("province") + " " + attributes.get("city"));
        // 设置基础用户信息
        thirdAccount.setName(oAuth2User.getName());
        thirdAccount.setAvatarUrl(String.valueOf(attributes.get("headimgurl")));
        return thirdAccount;
    }
}
