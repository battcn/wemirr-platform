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

package com.wemirr.framework.security.configuration.server.support;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Levin
 * @since 2019-04-03
 **/
@Data
@Builder
@AllArgsConstructor
public class AuthenticationPrincipal {

    @Schema(description = "登录类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String loginType;

    @Schema(description = "登录账号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "登录密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "租户编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tenantCode;

    @Schema(description = "客户端ID", example = "pc-web")
    private String clientId;

    @Schema(description = "客户端密钥", example = "pc-web")
    private String clientSecret;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Schema(description = "HTTP 请求", requiredMode = Schema.RequiredMode.REQUIRED)
    private HttpServletRequest request;

    public String getParameter(String name) {
        return request.getParameter(name);
    }
}