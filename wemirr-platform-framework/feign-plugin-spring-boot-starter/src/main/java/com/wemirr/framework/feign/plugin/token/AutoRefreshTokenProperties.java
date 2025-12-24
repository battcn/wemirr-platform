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

package com.wemirr.framework.feign.plugin.token;

import lombok.Data;

/**
 * 自动刷新 Token 配置
 * <p>
 * 用于 Feign 内部服务调用时自动获取和刷新认证 Token
 *
 * @author Levin
 */
@Data
public class AutoRefreshTokenProperties {

    public static final String TOKEN_PREFIX = "extend.feign.plugin.token";
    public static final String X_AUTO_TOKEN = "X-Auto-Token=true";
    public static final String X_AUTO_TOKEN_KEYWORD = "X-Auto-Token";
    public static final String AUTHORIZATION = "Authorization";

}
