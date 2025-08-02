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

package com.wemirr.platform.iam.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 租户字典
 *
 * @author Levin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@TableName("t_tenant_dict")
@Schema(name = "TenantDict", description = "租户字典")
public class TenantDict extends SuperEntity<Long> {
    
    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "名称")
    private String name;

    @Schema(description = "父级节点ID")
    private Long parentId;

    @Schema(description = "父级节点编码")
    private String parentCode;

    @Schema(description = "完整字典路径")
    private String fullCodePath;

    @Schema(description = "排序")
    private Integer sequence;

    @TableField(value = TENANT_ID_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "租户ID")
    private Long tenantId;
    
    @Schema(description = "是否只读")
    private Boolean readonly;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "状态")
    private Boolean status;
    
}
