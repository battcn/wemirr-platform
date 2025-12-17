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

package com.wemirr.framework.db.dynamic.core;

import com.wemirr.framework.db.properties.MultiTenantType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 动态数据源事件，用于多租户数据源的动态加载和切换
 *
 * @author Levin
 */
@Data
@Schema(description = "动态数据源事件")
public class DynamicDataSourceEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "租户编码")
    private String tenantCode;

    @Schema(description = "租户名称")
    private String tenantName;

    @Schema(description = "数据库类型")
    private String dbType;

    @Schema(description = "驱动类")
    private String driverClassName;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "JDBC连接串")
    private String jdbcUrl;

    @Schema(description = "隔离策略: DATABASE, SCHEMA, COLUMN")
    private MultiTenantType strategy;

    @Schema(description = "运行时Schema名称")
    private String schemaName;

    /**
     * 指令类型
     *
     * @see EventAction
     */
    @Schema(description = "指令类型")
    private Integer action;
}
