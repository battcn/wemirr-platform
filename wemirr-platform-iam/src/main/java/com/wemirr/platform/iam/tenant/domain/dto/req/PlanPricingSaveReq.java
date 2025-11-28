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

package com.wemirr.platform.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "ProductPricingSaveReq")
public class PlanPricingSaveReq {
    
    @Schema(description = "套餐ID")
    @NotNull(message = "套餐不能为空")
    private Long planId;
    
    @Schema(description = "定价名称")
    @NotNull(message = "定价名称不能为空")
    private String name;
    
    @Schema(description = "用户数量")
    @NotNull(message = "用户数量不能为空")
    private Integer userNum;
    
    @Schema(description = "月数")
    @NotNull(message = "采购购买不能为空")
    private Integer monthNum;
    
    @Schema(description = "每用户单价")
    @NotNull(message = "每用户单价不能为空")
    private Integer pricePerUser;
    
    @Schema(description = "超额单价")
    private String overPrice;
    
    @Schema(description = "超额单价")
    @NotNull(message = "超额单价不能为空")
    private Integer price;
    
    @Schema(description = "描述")
    private String description;
    
}
