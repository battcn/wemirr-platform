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

package com.wemirr.platform.iam.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.security.configuration.server.support.AuthenticationPrincipal;
import com.wemirr.framework.security.configuration.server.support.AuthenticatorStrategy;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.iam.base.domain.dto.req.ChangePasswordReq;
import com.wemirr.platform.iam.base.domain.dto.req.ChangeUserInfoReq;
import com.wemirr.platform.iam.system.domain.dto.req.LoginReq;
import com.wemirr.platform.iam.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Tag(name = "Token管理", description = "Token管理")
public class TokenController {

    private final AuthenticationContext context;
    private final UserService userService;
    private final List<AuthenticatorStrategy> authenticatorStrategies;

    @SaIgnore
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录")
    public SaTokenInfo login(HttpServletRequest request, @Validated @RequestBody LoginReq req) {
        for (AuthenticatorStrategy strategy : authenticatorStrategies) {
            if (!strategy.support(req.getLoginType())) {
                continue;
            }
            AuthenticationPrincipal principal = AuthenticationPrincipal.builder()
                    .loginType(req.getLoginType()).tenantCode(req.getTenantCode())
                    .clientId(req.getClientId()).clientSecret(req.getClientSecret())
                    .username(req.getUsername()).password(req.getPassword())
                    .request(request)
                    .build();
            // 前置处理器
            strategy.prepare(principal);
            // 登录
            strategy.authenticate(principal);
            // 后置处理器
            strategy.complete(principal);
            return StpUtil.getTokenInfo();
        }
        throw CheckedException.notFound("未检测到有效的策略");
    }


    @GetMapping("/userinfo")
    @Operation(summary = "用户信息", description = "获取用户信息")
    public UserInfoDetails userinfo() {
        long userId = StpUtil.getLoginIdAsLong();
        return userService.userinfo(userId);
    }

    @PutMapping("/change_password")
    @Operation(summary = "修改密码")
    public void changePassword(@Validated @RequestBody ChangePasswordReq dto) {
        if (!StringUtils.equals(dto.getNewPassword(), dto.getConfirmPassword())) {
            throw CheckedException.badRequest("新密码与确认密码不一致");
        }
        this.userService.changePassword(context.userId(), dto.getCurrentPassword(), dto.getNewPassword());
    }

    @PutMapping("/change_info")
    @Operation(summary = "信息修改")
    public void changeInfo(@Validated @RequestBody ChangeUserInfoReq req) {
        this.userService.changeInfo(req);
    }

    @DeleteMapping("/logout")
    @Operation(summary = "退出登录")
    public void logout(String device) {
        StpUtil.login(StpUtil.getLoginId(), device);
    }

}
