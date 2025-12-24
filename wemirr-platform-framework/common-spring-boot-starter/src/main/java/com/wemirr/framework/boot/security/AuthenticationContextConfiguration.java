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

package com.wemirr.framework.boot.security;

import cn.dev33.satoken.stp.StpUtil;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.commons.security.DataPermission;
import com.wemirr.framework.commons.threadlocal.ThreadLocalHolder;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.domain.UserInfoDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 认证上下文配置
 * <p>支持主线程和异步线程获取用户信息</p>
 * <p>异步线程通过TTL自动传递ThreadLocalHolder中的上下文</p>
 *
 * @author Levin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthenticationContextConfiguration {

    /**
     * 用户信息缓存Key（存储在ThreadLocalHolder中，支持TTL传递）
     */
    private static final String USER_INFO = "USER_INFO_KEY";
    private static final String ANONYMOUS = "USER_ANONYMOUS_KEY";

    @Bean
    public AuthenticationContext authenticationContext(SecurityExtProperties properties) {
        return new AuthenticationContext() {

            /**
             * 获取用户上下文（支持异步线程）
             * <p>优先从ThreadLocalHolder获取（支持TTL传递），如果没有再从SaToken获取</p>
             */
            @Override
            public UserInfoDetails getContext() {
                // 1. 先尝试从ThreadLocalHolder获取（异步线程场景）
                Object cached = ThreadLocalHolder.get(USER_INFO);
                if (cached != null) {
                    return (UserInfoDetails) cached;
                }

                // 2. 主线程场景：从SaToken获取并缓存到ThreadLocalHolder
                try {
                    if (!StpUtil.isLogin()) {
                        return null;
                    }
                    var tokenInfo = StpUtil.getTokenSession().get(properties.getServer().getTokenInfoKey());
                    if (tokenInfo == null) {
                        return null;
                    }
                    UserInfoDetails userInfo = (UserInfoDetails) tokenInfo;
                    // 缓存到ThreadLocalHolder，后续异步线程可通过TTL获取
                    ThreadLocalHolder.set(USER_INFO, userInfo);
                    return userInfo;
                } catch (Exception e) {
                    // 异步线程中SaToken上下文可能不可用，这是正常情况
                    log.trace("无法从SaToken获取用户信息（可能是异步线程）: {}", e.getMessage());
                    return null;
                }
            }

            @Override
            public String clientId() {
                try {
                    return StpUtil.getLoginDeviceType();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public Long tenantId() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getTenantId).orElse(null);
            }

            @Override
            public String tenantName() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getTenantName).orElse(null);
            }

            @Override
            public String tenantCode() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getTenantCode).orElse(null);
            }

            @Override
            public Long userId() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getUserId).orElse(null);
            }

            @Override
            public String nickName() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getNickName).orElse(null);
            }

            @Override
            public String mobile() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getMobile).orElse(null);
            }

            @Override
            public List<String> funcPermissionList() {
                UserInfoDetails context = getContext();
                return context != null ? (List<String>) context.getFuncPermissions() : Collections.emptyList();
            }

            @Override
            public List<String> rolePermissionList() {
                UserInfoDetails context = getContext();
                return context != null ? (List<String>) context.getRoles() : Collections.emptyList();
            }

            @Override
            public DataPermission dataPermission() {
                UserInfoDetails context = getContext();
                return context != null ? context.getDataPermission() : null;
            }

            @Override
            public boolean anonymous() {
                // 先从缓存获取
                Object cached = ThreadLocalHolder.get(ANONYMOUS);
                if (cached != null) {
                    return (Boolean) cached;
                }

                // 计算匿名状态
                boolean isAnonymous;
                try {
                    isAnonymous = !StpUtil.isLogin() && getContext() == null;
                } catch (Exception ex) {
                    // SaToken上下文不可用时，根据ThreadLocalHolder中是否有用户信息判断
                    isAnonymous = getContext() == null;
                }

                // 只缓存非匿名状态（登录用户）
                if (!isAnonymous) {
                    ThreadLocalHolder.set(ANONYMOUS, false);
                }
                return isAnonymous;
            }
        };
    }
}
