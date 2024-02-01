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

package com.wemirr.platform.authority.domain.tenant.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class TenantSaveReq {
    
    @NotBlank(message = "租户编码不能为空")
    @Length(min = 2, max = 6, message = "租户编码长度 {min} - {max} 之间")
    @Schema(description = "租户编码")
    private String code;
    
    @NotBlank(message = "租户名称不能为空")
    @Length(min = 2, max = 30, message = "租户名称长度 {min} - {max} 之间")
    @Schema(description = "租户名称")
    private String name;
    
    @NotNull(message = "租户类型不能为空")
    @Schema(description = "租户类型")
    private Integer type;
    
    @NotNull(message = "认证状态不能为空")
    @Schema(description = "认证状态")
    private Integer status;
    
    @Length(min = 2, max = 8, message = "租户简称长度 {min} - {max} 之间")
    @Schema(description = "租户简称")
    private String alias;
    
    @Schema(description = "使用状态")
    @NotNull(message = "使用状态不能为空")
    private Boolean locked;
    
    @Length(min = 2, max = 256, message = "租户LOGO长度 {min} - {max} 之间")
    @Schema(description = "LOGO")
    private String logo;
    
    @Length(min = 2, max = 30, message = "租户邮箱长度 {min} - {max} 之间")
    private String email;
    
    @Length(min = 2, max = 30, message = "联系人长度 {min} - {max} 之间")
    @Schema(description = "联系人")
    private String contactPerson;
    
    @Length(min = 2, max = 30, message = "联系方式长度 {min} - {max} 之间")
    @Schema(description = "联系方式")
    private String contactPhone;
    
    @Schema(description = "行业")
    private String industry;
    
    private Long provinceId;
    private Long cityId;
    private Long districtId;
    
    @Schema(description = "地址")
    @Length(min = 2, max = 100, message = "地址长度 {min} - {max} 之间")
    private String address;
    @Schema(description = "统一信用代码")
    @Length(min = 2, max = 100, message = "统一信用代码长度为 {min} - {max} 之间")
    private String creditCode;
    @Schema(description = "法人")
    @Length(min = 2, max = 30, message = "法人长度为 {min} - {max} 之间")
    private String legalPersonName;
    @Schema(description = "站点")
    @Length(min = 2, max = 100, message = "租户站点长度为 {min} - {max} 之间")
    private String webSite;
    @Schema(description = "描述")
    @Length(min = 2, max = 256, message = "租户描述度为 {min} - {max} 之间")
    private String description;
}
