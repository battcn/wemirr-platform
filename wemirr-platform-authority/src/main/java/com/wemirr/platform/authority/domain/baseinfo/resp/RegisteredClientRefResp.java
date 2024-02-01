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
package com.wemirr.platform.authority.domain.baseinfo.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "RegisteredClientRefResp")
public class RegisteredClientRefResp {
    
    @Schema(description = "客户端ID")
    private String id;
    @Schema(description = "客户端名称")
    private String clientName;
    @Schema(description = "客户端ID")
    private String clientId;
    @Schema(description = "客户端ID生效时间")
    private Instant clientIdIssuedAt;
    @Schema(description = "客户端秘钥")
    private String clientSecret;
    @Schema(description = "秘钥失效时间")
    private Instant clientSecretExpiresAt;
    @Schema(description = "授权类型")
    private List<String> authorizationGrantTypes;
    @Schema(description = "重定向地址")
    private String redirectUris;
    @Schema(description = "退出后重定向地址")
    private String postLogoutRedirectUris;
    @Schema(description = "授权范围")
    private List<String> scopes;
    @Schema(description = "accessToken 有效时长(2小时) ", example = "120")
    private Long accessTokenTimeToLive;
    @Schema(description = "refreshToken 有效时长(7天)", example = "10080")
    private Long refreshTokenTimeToLive;
    @Schema(description = "授权码 有效时长（5分钟）", example = "5")
    private Long authorizationCodeTimeToLive;
    @Schema(description = "设备码 有效时长（分钟）", example = "5")
    private Long deviceCodeTimeToLive;
    
}
