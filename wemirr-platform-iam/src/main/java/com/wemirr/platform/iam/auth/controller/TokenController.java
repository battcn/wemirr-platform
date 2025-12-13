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

package com.wemirr.platform.iam.auth.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.iam.auth.domain.dto.req.LoginReq;
import com.wemirr.platform.iam.auth.domain.dto.resp.LoginResp;
import com.wemirr.platform.iam.auth.strategy.AuthenticatorStrategyTemplate;
import com.wemirr.platform.iam.auth.support.AuthenticationPrincipal;
import com.wemirr.platform.iam.base.domain.dto.req.ChangePasswordReq;
import com.wemirr.platform.iam.base.domain.dto.req.ChangeUserInfoReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserOnlinePageReq;
import com.wemirr.platform.iam.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

    private final SaTokenConfig tokenConfig;
    private final AuthenticationContext context;
    private final UserService userService;
    private final AuthenticatorStrategyTemplate strategyTemplate;

    @SaIgnore
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录")
    public LoginResp login(HttpServletRequest request, @Validated @RequestBody LoginReq req) {
        AuthenticationPrincipal principal = AuthenticationPrincipal.builder().code(req.getCode())
                .loginType(req.getLoginType()).tenantCode(req.getTenantCode())
                .clientId(req.getClientId()).clientSecret(req.getClientSecret())
                .username(req.getUsername()).password(req.getPassword())
                .request(request).build();
        strategyTemplate.prepare(principal);
        strategyTemplate.authenticate(principal);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return LoginResp.builder()
                .accessToken(tokenInfo.getTokenValue())
                .expiresIn(tokenInfo.getTokenTimeout())
                .clientId(principal.getClientId())
                .tokenType(tokenConfig.getTokenPrefix()).build();
    }

    @GetMapping("/userinfo")
    @Operation(summary = "用户信息", description = "获取用户信息")
    public UserInfoDetails userinfo() {
        return (UserInfoDetails) context.getContext();
    }

    @GetMapping("/func_permissions")
    @Operation(summary = "用户信息", description = "获取用户信息")
    public List<String> funcPermissionList() {
        return context.funcPermissionList();
    }

    @PutMapping("/change_password")
    @Operation(summary = "修改密码")
    public void changePassword(@Validated @RequestBody ChangePasswordReq req) {
        if (!StringUtils.equals(req.getNewPassword(), req.getConfirmPassword())) {
            throw CheckedException.badRequest("新密码与确认密码不一致");
        }
        this.userService.changePassword(StpUtil.getLoginIdAsLong(), req.getCurrentPassword(), req.getNewPassword());
    }

    @PutMapping("/change_info")
    @Operation(summary = "信息修改")
    public void changeInfo(@Validated @RequestBody ChangeUserInfoReq req) {
        this.userService.changeInfo(req);
    }

    @SaIgnore
    @DeleteMapping("/logout")
    @Operation(summary = "退出登录")
    public void logout() {
        StpUtil.logout();
    }

    @Operation(summary = "在线用户", description = "分页查询在线用户列表")
    @SaCheckPermission("monitor:online:token-list")
    @GetMapping("/online")
    public IPage<Object> userOnlinePage(UserOnlinePageReq req) {
        return userService.userOnlineList(req);
    }

    @Operation(summary = "强退用户", description = "强退在线用户")
    @Parameter(name = "token", description = "令牌", example = "123", in = ParameterIn.PATH)
    @SaCheckPermission("token:online:logout")
    @DeleteMapping("/{token}")
    public void forceLogout(@PathVariable String token) {
        if (StrUtil.equals(token, StpUtil.getTokenValue())) {
            throw CheckedException.badRequest("不能强退自己");
        }
        StpUtil.kickoutByTokenValue(token);
    }

}
