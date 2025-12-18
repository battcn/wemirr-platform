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

package com.wemirr.platform.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据权限关联表
 * 支持多维度、多拥有者类型的数据权限配置
 *
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_data_permission_ref")
@EqualsAndHashCode(callSuper = true)
public class DataPermissionRef extends Entity<Long> {
    
    @Schema(description = "所有者ID（角色ID/用户ID等）")
    @TableField("owner_id")
    private Long ownerId;
    
    @Schema(description = "所有者类型（ROLE-角色, USER-用户等）")
    private DataResourceType ownerType;
    
    @Schema(description = "数据类型（ORG-机构, COMPANY-公司等）")
    private DataResourceType dataType;
    
    @Schema(description = "权限范围类型: 10-个人, 20-自定义, 30-本级, 40-本级及子级, 50-全部")
    private Integer scopeType;
    
    @Schema(description = "数据ID（自定义时的具体数据，非自定义时为null）")
    @TableField("data_id")
    private Long dataId;
}
