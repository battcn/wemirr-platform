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

package com.wemirr.platform.iam.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_registered_client")
public class RegisteredClient extends SuperEntity<String> {
    
    @Schema(description = "客户端ID")
    private String clientId;
    @Schema(description = "客户端秘钥")
    private String clientSecret;
    @Schema(description = "客户端 ID 发放时间")
    private Instant clientIdIssuedAt;
    @Schema(description = "客户端 秘钥失效时间")
    private Instant clientSecretExpiresAt;
    @Schema(description = "客户端名称")
    private String clientName;
    @Schema(description = "授权类型")
    private String grantTypes;
    @Schema(description = "重定向地址")
    private String redirectUris;
    @Schema(description = "退出登录重定向地址")
    private String postLogoutRedirectUris;
    @Schema(description = "授权范围")
    private String scopes;
    @Schema(description = "客户端设置")
    private String clientSettings;
    @Schema(description = "令牌设置")
    private String tokenSettings;
    
    @Schema(description = "状态")
    private Boolean status;
    
}
