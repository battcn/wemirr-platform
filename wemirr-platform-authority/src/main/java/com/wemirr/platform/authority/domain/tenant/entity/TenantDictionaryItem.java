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
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 实体类
 * 字典项
 * </p>
 *
 * @author Levin
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@TableName("t_tenant_dictionary_item")
@Schema(name = "TenantDictionaryItem", description = "字典项")
public class TenantDictionaryItem extends SuperEntity<Long> {
    
    /**
     * 类型ID
     */
    @Schema(description = "类型ID")
    @TableField("dictionary_id")
    private Long dictionaryId;
    
    /**
     * 类型ID
     */
    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;
    
    /**
     * 编码
     */
    @Schema(description = "编码")
    private String dictionaryCode;
    
    /**
     * 编码
     */
    @Schema(description = "编码")
    @TableField(value = "`value`")
    private String value;
    
    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "label")
    private String label;
    
    /**
     * 状态
     */
    @Schema(description = "状态")
    @TableField("`status`")
    private Boolean status;
    
    /**
     * 描述
     */
    @Schema(description = "描述")
    @TableField(value = "description")
    private String description;
    
    /**
     * 排序
     */
    @Schema(description = "排序")
    @TableField("`sequence`")
    private Integer sequence;
    
}
