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

package com.wemirr.platform.authority.domain.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@SuperBuilder
@TableName("t_dynamic_datasource")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DynamicDatasource extends SuperEntity<Long> {
    
    @Schema(description = "连接名")
    private String name;
    
    @Schema(description = "数据库类型(只支持Mysql)")
    private String dbType;
    
    @Schema(description = "驱动类名")
    private String driverClassName;
    
    @TableField("`username`")
    @Schema(description = "用户名")
    private String username;
    
    @TableField("`password`")
    @Schema(description = "密码")
    private String password;
    
    @TableField("`host`")
    @Schema(description = "host")
    private String host;
    
    @TableField("`locked`")
    @Schema(description = "是否禁用")
    private Boolean locked;
    
    @Schema(description = "描述")
    private String description;
    
}
