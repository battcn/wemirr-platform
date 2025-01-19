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

package com.wemirr.platform.iam.auth.domain.dto.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResp {
    
    @Schema(description = "scope")
    @JsonProperty("scope")
    private List<String> scope;
    
    @Schema(description = "openId")
    @JsonProperty("openId")
    private String openId;
    
    @Schema(description = "访问令牌")
    private String accessToken;
    
    @Schema(description = "Token 类型")
    private String tokenType;
    
    @Schema(description = "刷新令牌")
    private String refreshToken;
    
    @Schema(description = "客户端ID")
    private String clientId;
    
    @Schema(description = "访问令牌失效时间")
    private Long expiresIn;
    
    @Schema(description = "刷新令牌失效时间")
    private Long refreshExpireIn;
    
}
