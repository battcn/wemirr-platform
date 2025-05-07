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

package com.wemirr.framework.security.configuration;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Levin
 */
@EnableConfigurationProperties(SecurityExtProperties.class)
public class OAuth2AutoConfiguration {

    @Resource
    private SecurityExtProperties extProperties;

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter().addInclude("/")
                .setExcludeList(extProperties.getDefaultIgnoreUrls())
                .setIncludeList(extProperties.getIgnore().getResourceUrls())
                .setAuth(a -> SaRouter.match("/**").check(r -> StpUtil.checkLogin()));
    }
}
