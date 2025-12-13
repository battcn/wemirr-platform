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

package com.wemirr.platform.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class DbInstanceSaveReq {


    @NotBlank(message = "数据库连接名不能为空")
    @Schema(description = "节点名称")
    private String name;

    @Schema(description = "数据库类型")
    @NotBlank(message = "数据库类型不能为空")
    private String dbType;

    @NotBlank(message = "驱动类不能为空")
    @Schema(description = "驱动类型")
    private String driverClassName;

    @Schema(description = "JDBC连接串")
    private String jdbcUrl;


    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "是否启用(0=停用；1=启用)")
    private Boolean status;

    @Length(max = 300, message = "长度不能超过 {max} 个字符")
    private String description;
}
