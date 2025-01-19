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

package com.wemirr.platform.iam.auth.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "LoginReq", description = "登录类")
public class LoginReq {
    
    @NotBlank(message = "租户编码不能为空")
    @Schema(description = "租户编码", example = "0000")
    private String tenantCode;
    
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名", example = "admin")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", example = "123456")
    private String password;
    
    @NotBlank(message = "登录类型不能为空")
    @Schema(description = "登录类型", example = "password")
    private String loginType;
    
    @Schema(description = "验证码", example = "123456")
    private String code;
    
    @NotBlank(message = "客户端ID不能为空")
    @Schema(description = "客户端ID", example = "pc-web")
    private String clientId;
    
    @NotBlank(message = "客户端密钥不能为空")
    @Schema(description = "客户端密钥", example = "pc-web")
    private String clientSecret;
}
