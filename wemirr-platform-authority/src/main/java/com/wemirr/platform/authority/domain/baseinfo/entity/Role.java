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

package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author battcn
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends Entity<Long> {
    
    @Schema(description = "租户ID")
    private Long tenantId;
    
    @Schema(description = "角色名称")
    private String name;
    
    @Schema(description = "角色编码")
    private String code;
    
    @Schema(description = "超级角色")
    @TableField("`super`")
    private Boolean superRole;
    
    @Schema(description = "租户描述")
    private String description;
    
    @Schema(description = "是否只读")
    private Boolean readonly;
    
    @Schema(description = "是否被锁定")
    private Boolean locked;
    
    @Schema(description = "权限类型")
    private DataScopeType scopeType;
}
