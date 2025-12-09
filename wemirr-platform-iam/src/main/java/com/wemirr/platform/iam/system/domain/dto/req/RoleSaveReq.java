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

package com.wemirr.platform.iam.system.domain.dto.req;

import com.wemirr.framework.commons.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 保存角色对象
 *
 * @author Levin
 */
@Data
@Schema(name = "RoleSaveReq")
public class RoleSaveReq {
    
    @Schema(description = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能超过30")
    private String name;
    
    @Schema(description = "角色编码")
    @Length(max = 20, message = "角色编码长度不能超过20")
    private String code;
    
    @Schema(description = "描述")
    @Length(max = 100, message = "描述长度不能超过100")
    private String description;
    
    @NotNull(message = "角色状态不能为空")
    @Schema(description = "状态")
    private Boolean status;
    
    @Schema(description = "内置角色")
    private Boolean readonly;
    
    @Schema(description = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private DataScopeType scopeType;
    
    @Schema(description = "关联的组织ID")
    private List<Long> orgIdList;
}
