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

package com.wemirr.platform.iam.system.listener;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.commons.RegionUtils;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.iam.base.domain.entity.LoginLog;
import com.wemirr.platform.iam.base.repository.LoginLogMapper;
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
    private final SaTokenDao saTokenDao;
    private final LoginLogMapper loginLogMapper;
    private final UserService userService;
    private final HttpServletRequest request;
    
    /**
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue 本次登录产生的 token 值
     * @param loginModel 登录参数
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        String ip = JakartaServletUtil.getClientIP(request);
        String region = RegionUtils.getRegion(ip);
        String ua = request.getHeader(USER_AGENT);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final Browser browser = userAgent.getBrowser();
        final Long userId = Long.parseLong(loginId.toString());
        String principal = SaHolder.getStorage().getString("principal");
        String principalType = SaHolder.getStorage().getString("principalType");
        UserInfoDetails info = this.userService.userinfo(userId);
        LoginLog loginLog = LoginLog.builder().principal(principal)
                .clientId(loginModel.getDevice())
                .tenantId(info.getTenantId()).tenantCode(info.getTenantCode())
                .location(region).ip(ip)
                .platform(userAgent.getPlatform().getName())
                .engine(userAgent.getEngine().getName())
                .browser(browser.getName())
                .os(userAgent.getOs().getName())
                .loginType(principalType)
                .createdBy(userId).createdTime(Instant.now()).createdName(info.getNickName())
                .build();
        info.setLoginLog(JSONObject.from(loginLog));
        this.saTokenDao.setObject(buildCacheKey(tokenValue), info, loginModel.getTimeout());
        // 记录登录日志
        TenantHelper.executeWithTenantDb(info.getTenantCode(), () -> this.loginLogMapper.insert(loginLog));
        // 刷新登录时间和IP
        this.userService.updateById(User.builder().id(userId).lastLoginIp(ip).lastLoginTime(Instant.now()).build());
    }
    
    private String buildCacheKey(String tokenValue) {
        return String.format(extProperties.getServer().getInfoKeyPrefix(), tokenValue);
    }
    
    /**
     * @param tokenValue token 值
     * @param loginId    账号id
     * @param timeout    续期时间
     */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        this.saTokenDao.updateTimeout(buildCacheKey(tokenValue), timeout);
    }
    
    /**
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        this.saTokenDao.delete(buildCacheKey(tokenValue));
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
        this.saTokenDao.delete(buildCacheKey(tokenValue));
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