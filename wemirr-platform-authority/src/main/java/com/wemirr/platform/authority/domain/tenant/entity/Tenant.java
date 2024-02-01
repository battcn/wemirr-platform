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
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NEVER;

/**
 * <p>
 * 租户信息
 * </p>
 *
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_tenant")
public class Tenant extends SuperEntity<Long> {
    
    @Schema(description = "编码")
    private String code;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "类型")
    private Integer type;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "别名")
    private String alias;
    @Schema(description = "名称")
    private Boolean locked;
    
    @Schema(description = "LOGO")
    private String logo;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "联系人")
    private String contactPerson;
    @Schema(description = "联系方式")
    private String contactPhone;
    @Schema(description = "行业")
    private String industry;
    
    @Schema(description = "省")
    @TableField(updateStrategy = NEVER)
    private Long provinceId;
    @Schema(description = "省")
    @TableField(updateStrategy = NEVER)
    private String provinceName;
    @Schema(description = "市")
    @TableField(updateStrategy = NEVER)
    private Long cityId;
    @Schema(description = "市")
    @TableField(updateStrategy = NEVER)
    private String cityName;
    @Schema(description = "区")
    @TableField(updateStrategy = NEVER)
    private Long districtId;
    @Schema(description = "区")
    @TableField(updateStrategy = NEVER)
    private String districtName;
    
    private String address;
    @Schema(description = "统一信用代码")
    private String creditCode;
    @Schema(description = "法人")
    private String legalPersonName;
    @Schema(description = "WEB站点")
    private String webSite;
    @Schema(description = "描述")
    private String description;
    
}
