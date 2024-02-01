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
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * oauth2 三方登录获取到的用户信息转换策略接口
 *
 * @author vains
 */
public interface Oauth2UserConverter {
    
    /**
     * 将oauth2登录的认证信息转为 {@link OAuth2ThirdAccount}
     *
     * @param oAuth2User oauth2登录获取的用户信息
     * @return 项目中的用户信息
     */
    OAuth2ThirdAccount convert(OAuth2User oAuth2User);
    
}
