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
package com.wemirr.platform.authority.service.impl;

import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wemirr.framework.boot.RegionUtils;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.authority.domain.common.entity.LoginLog;
import com.wemirr.platform.authority.repository.common.LoginLogMapper;
import com.wemirr.platform.authority.service.LoginLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    
    private final HttpServletRequest request;
    
    private static final String USER_AGENT = "User-Agent";
    
    @Override
    public void addLog(UserInfoDetails details) {
        final String clientId = request.getParameter("client_id");
        final String username = request.getParameter("username");
        String ip = JakartaServletUtil.getClientIP(request);
        String region = RegionUtils.getRegion(ip);
        String ua = request.getHeader(USER_AGENT);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final Browser browser = userAgent.getBrowser();
        LoginLog loginLog = LoginLog.builder()
                .tenantId(details.getTenantId())
                .userId(details.getUserId()).principal(username).name(details.getNickName())
                .location(region).ip(ip)
                .platform(userAgent.getPlatform().getName())
                .engine(userAgent.getEngine().getName())
                .browser(browser.getName())
                .os(userAgent.getOs().getName())
                .clientId(clientId)
                .createdBy(details.getUserId()).createdName(details.getNickName())
                .build();
        super.save(loginLog);
    }
}
