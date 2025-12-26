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

package com.wemirr.platform.iam.auth.listener;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.NativeUserAgent;
import com.wemirr.framework.commons.RegionUtils;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.iam.auth.domain.entity.LoginLog;
import com.wemirr.platform.iam.auth.repository.LoginLogMapper;
import com.wemirr.platform.iam.auth.support.AuthenticationPrincipal;
import com.wemirr.platform.iam.auth.support.domain.UserTenantAuthentication;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 登录监听器
 *
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WpTokenListener implements SaTokenListener {

    private static final String USER_AGENT = "User-Agent";
    private final SecurityExtProperties extProperties;
    private final LoginLogMapper loginLogMapper;
    private final UserService userService;
    private final HttpServletRequest request;

    /**
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue 本次登录产生的 token 值
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginParameter loginParameter) {
        String ip = JakartaServletUtil.getClientIP(request);
        String region = RegionUtils.getRegion(ip);
        String ua = request.getHeader(USER_AGENT);
        var userAgent = NativeUserAgent.parse(ua);
        final long userId = (long) loginId;
        String principal = SaHolder.getStorage().getString(AuthenticationPrincipal.PRINCIPAL);
        String principalType = SaHolder.getStorage().getString(AuthenticationPrincipal.PRINCIPAL_TYPE);
        UserTenantAuthentication authentication = SaHolder.getStorage().getModel(AuthenticationPrincipal.AUTHENTICATION, UserTenantAuthentication.class);
        UserInfoDetails info = this.userService.userinfo(authentication);
        LoginLog loginLog = LoginLog.builder().principal(principal).clientId(loginParameter.getDeviceType())
                .tenantId(info.getTenantId()).tenantCode(info.getTenantCode()).location(region).ip(ip)
                .platform(userAgent.platform()).engine(userAgent.engine()).browser(userAgent.browser()).os(userAgent.os())
                .loginType(principalType).createBy(userId).createTime(Instant.now()).createName(info.getNickName()).build();
        info.setLoginLog(JacksonUtils.readValue(JacksonUtils.toJson(loginLog), new TypeReference<>() {
        }));
        StpUtil.getTokenSessionByToken(tokenValue).set(extProperties.getServer().getTokenInfoKey(), info);
        // 记录登录日志
        TenantHelper.executeWithTenantDb(info.getTenantCode(), () -> this.loginLogMapper.insert(loginLog));
        // 刷新登录时间和IP
        this.userService.updateById(User.builder().id(userId).lastLoginIp(ip).lastLoginTime(Instant.now()).build());
    }

    @Override
    public void doRenewTimeout(String loginType, Object loginId, String tokenValue, long timeout) {

    }

    /**
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
    }

    /**
     * 每次被踢下线时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        // 数据库可以记录一下操作日志
    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {

    }

    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {

    }

    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }


}