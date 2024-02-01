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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 实体类
 * 字典类型
 * </p>
 *
 * @author Levin
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName("t_tenant_dictionary")
@Schema(name = "TenantDictionary", description = "字典类型")
public class TenantDictionary extends SuperEntity<Long> {
    
    /**
     * 编码
     */
    @Schema(description = "编码")
    @TableField(value = "`code`")
    private String code;
    
    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "name")
    private String name;
    
    /**
     * 名称
     */
    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private Long tenantId;
    
    /**
     * 描述
     */
    @Schema(description = "描述")
    @TableField(value = "description")
    private String description;
    
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;
    
    @TableField(value = "`readonly`")
    @Schema(description = "是否只读")
    private Boolean readonly;
    
}
