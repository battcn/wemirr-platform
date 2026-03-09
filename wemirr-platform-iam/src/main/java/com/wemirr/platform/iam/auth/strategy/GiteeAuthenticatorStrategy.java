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

import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.platform.iam.auth.domain.entity.UserThirdAccount;
import com.wemirr.platform.iam.auth.support.AuthenticationPrincipal;
import com.wemirr.platform.iam.auth.support.AuthenticatorStrategy;
import com.wemirr.platform.iam.auth.support.domain.UserTenantAuthentication;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import com.wemirr.platform.iam.system.repository.ThirdAccountMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.repository.TenantMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * GITEE 登录处理.
 *
 * @author Levin
 **/
@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class GiteeAuthenticatorStrategy implements AuthenticatorStrategy {

    @Resource
    private TenantMapper tenantMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ThirdAccountMapper thirdAccountMapper;

    @Override
    public void prepare(final AuthenticationPrincipal principal) {
    }

    @Override
    public UserTenantAuthentication authenticate(final AuthenticationPrincipal principal) {
        log.warn("暂未实现授权绑定逻辑,比如授权后默认第一种授权类型生成 t_user 记录");
        String username = principal.getUsername();
        String tenantCode = principal.getTenantCode();
        Tenant tenant = Optional.ofNullable(TenantHelper.executeWithMaster(() -> tenantMapper.selectOne(Tenant::getCode, tenantCode)))
                .orElseThrow(() -> CheckedException.notFound("{0}租户不存在", tenantCode));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户已被禁用,请联系管理员");
        }
        ThirdAuthType type = ThirdAuthType.of(principal.getLoginType());
        UserThirdAccount thirdAccount = Optional.ofNullable(thirdAccountMapper.selectOne(UserThirdAccount::getAccountId, username, UserThirdAccount::getType, type))
                .orElseThrow(() -> CheckedException.notFound("用户未授权"));
        log.debug("third-account => {}", JacksonUtils.toJson(thirdAccount));
        User user = Optional.ofNullable(TenantHelper.executeWithTenantDb(tenantCode, () -> userMapper.selectUserByTenantId("admin", tenant.getId())))
                .orElseThrow(() -> CheckedException.notFound("账户不存在"));
        return UserTenantAuthentication.builder().user(user).tenant(tenant).build();
    }

    @Override
    public String loginType() {
        return ThirdAuthType.GITEE.getType();
    }
}
