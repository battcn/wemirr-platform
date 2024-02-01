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

/**
 * 转换通过Github登录的用户信息
 *
 * @author vains
 */
@RequiredArgsConstructor
@Component(SecurityConstants.THIRD_LOGIN_GITHUB)
public class GithubUserConverter implements Oauth2UserConverter {
    
    private final GiteeUserConverter userConverter;
    
    protected static final String LOGIN_TYPE = SecurityConstants.THIRD_LOGIN_GITHUB;
    
    @Override
    public OAuth2ThirdAccount convert(OAuth2User oAuth2User) {
        // github与gitee目前所取字段一致，直接调用gitee的解析
        OAuth2ThirdAccount thirdAccount = userConverter.convert(oAuth2User);
        // 提取location
        Object location = oAuth2User.getAttributes().get("location");
        thirdAccount.setLocation(String.valueOf(location));
        // 设置登录类型
        thirdAccount.setType(LOGIN_TYPE);
        return thirdAccount;
    }
}
