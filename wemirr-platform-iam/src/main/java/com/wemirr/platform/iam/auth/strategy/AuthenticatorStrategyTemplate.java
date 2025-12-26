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

package com.wemirr.platform.iam.auth.strategy;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.auth.domain.entity.RegisteredClient;
import com.wemirr.platform.iam.auth.support.AuthenticationPrincipal;
import com.wemirr.platform.iam.auth.support.AuthenticatorStrategy;
import com.wemirr.platform.iam.auth.support.domain.UserTenantAuthentication;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.repository.RegisteredClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticatorStrategyTemplate {

    private final List<AuthenticatorStrategy> authenticatorStrategies;
    private final RegisteredClientMapper registeredClientMapper;

    public void prepare(final AuthenticationPrincipal principal) {
        log.info("[登录类型] - [{}], 登录参数 - [{}]", principal.getLoginType(), JacksonUtils.toJson(principal));
        Assert.notBlank(principal.getTenantCode(), () -> CheckedException.badRequest("租户编码不能为空"));
        Assert.notBlank(principal.getUsername(), () -> CheckedException.badRequest("用户名不能为空"));
        Assert.notBlank(principal.getPassword(), () -> CheckedException.badRequest("密码不能为空"));
        Assert.notBlank(principal.getClientId(), () -> CheckedException.badRequest("客户端ID不能为空"));
        Assert.notBlank(principal.getClientSecret(), () -> CheckedException.badRequest("客户端秘钥不能为空"));

        RegisteredClient registeredClient = Optional.ofNullable(this.registeredClientMapper.selectOne(Wraps.<RegisteredClient>lbQ()
                        .eq(RegisteredClient::getClientId, principal.getClientId())
                        .eq(RegisteredClient::getClientSecret, principal.getClientSecret())))
                .orElseThrow(() -> CheckedException.notFound("未查询到有效的客户端信息"));
        Assert.isTrue(registeredClient.getStatus(), () -> CheckedException.badRequest("当前客户端提被禁用"));
        Instant issuedAt = registeredClient.getClientIdIssuedAt();
        Instant expiresAt = registeredClient.getClientSecretExpiresAt();
        Assert.isTrue(issuedAt == null || issuedAt.isBefore(Instant.now()), () -> CheckedException.badRequest("客户端秘钥未生效"));
        Assert.isTrue(expiresAt == null || expiresAt.isBefore(Instant.now()), () -> CheckedException.badRequest("客户端秘钥已失效"));
        SaHolder.getStorage()
                .set(AuthenticationPrincipal.PRINCIPAL, principal.getUsername())
                .set(AuthenticationPrincipal.PRINCIPAL_TYPE, principal.getLoginType());
    }

    public void authenticate(AuthenticationPrincipal principal) {
        AuthenticatorStrategy strategy = authenticatorStrategies.stream()
                .filter(x -> x.support(principal.getLoginType())).findFirst()
                .orElseThrow(() -> CheckedException.notFound("未检测到有效的策略"));
        // 前置处理器-可以做一些私有化的校验
        strategy.prepare(principal);
        // 登录
        UserTenantAuthentication authentication = strategy.authenticate(principal);
        User user = authentication.getUser();
        if (user != null) {
            SaHolder.getStorage().set(AuthenticationPrincipal.AUTHENTICATION, authentication);
            StpUtil.login(user.getId(), principal.getClientId());
        }
        // 后置处理器
        strategy.complete(principal);
    }

}
