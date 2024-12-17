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

package com.wemirr.platform.iam.system.domain.dto.resp;

import com.wemirr.platform.iam.system.domain.enums.LoginConfigType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginConfigResp {
    
    @Schema(description = "登录类型")
    private LoginConfigType type;
    
    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "编码")
    private String tenantCode;
    @Schema(description = "名称")
    private String tenantName;
    @Schema(description = "LOGO")
    private String tenantLogo;
    @Schema(description = "WEB站点")
    private String webSite;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "创建时间")
    private Instant createdTime;
    
}
