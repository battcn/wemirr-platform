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

package com.wemirr.framework.security.domain;

import lombok.Data;

import java.time.Instant;

/**
 * <p>
 * 三方登录账户信息表
 * </p>
 *
 * @author Levin
 * @since 2023-07-04
 */
@SuppressWarnings("ALL")
@Data
public class OAuth2ThirdAccount {
    
    /**
     * 三方登录唯一id
     */
    private String uniqueId;
    
    /**
     * 三方登录用户名
     */
    private String thirdUsername;
    
    /**
     * 三方登录获取的认证信息
     */
    private String credentials;
    
    /**
     * 三方登录获取的认证信息的过期时间
     */
    private Instant credentialsExpiresAt;
    
    /**
     * 三方登录类型
     */
    private String type;
    
    /**
     * 博客地址
     */
    private String blog;
    
    /**
     * 地址
     */
    private String location;
    
    /**
     * 用户名、昵称
     */
    private String name;
    
    /**
     * 头像地址
     */
    private String avatarUrl;
}
