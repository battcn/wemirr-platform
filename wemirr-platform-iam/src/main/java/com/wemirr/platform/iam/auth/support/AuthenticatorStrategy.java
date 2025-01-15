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

package com.wemirr.platform.iam.auth.support;

import com.wemirr.platform.iam.auth.support.domain.UserTenantAuthentication;

/**
 * @author Levin
 **/
public interface AuthenticatorStrategy {

    String DEFAULT_AUTH_TYPE = "password";

    /**
     * 处理集成认证
     *
     * @param principal principal
     * @return 认证
     */
    UserTenantAuthentication authenticate(AuthenticationPrincipal principal);

    /**
     * 进行预处理
     *
     * @param principal principal
     */
    void prepare(AuthenticationPrincipal principal);

    /**
     * 判断是否支持集成认证类型
     *
     * @param loginType loginType
     * @return 是否统一处理
     */
    default boolean support(String loginType) {
        return loginType != null && loginType.equalsIgnoreCase(loginType());
    }

    /**
     * 登录方式
     *
     * @return 默认密码登录
     */
    default String loginType() {
        return DEFAULT_AUTH_TYPE;
    }

    /**
     * 认证结束后执行
     *
     * @param principal integrationAuthentication
     */
    default void complete(AuthenticationPrincipal principal) {

    }

}