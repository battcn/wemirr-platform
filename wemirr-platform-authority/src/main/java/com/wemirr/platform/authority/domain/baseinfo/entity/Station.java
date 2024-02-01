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
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_station")
@Schema(name = "Station", description = "岗位")
public class Station extends SuperEntity<Long> {
    
    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "`name`", condition = LIKE)
    private String name;
    
    @Schema(description = "岗位编码")
    private String code;
    
    @Schema(description = "排序")
    @TableField(value = "`sequence`")
    private Integer sequence;
    
    @Schema(description = "组织ID")
    @TableField("org_id")
    private Long orgId;
    
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
    private String description;
    
}
