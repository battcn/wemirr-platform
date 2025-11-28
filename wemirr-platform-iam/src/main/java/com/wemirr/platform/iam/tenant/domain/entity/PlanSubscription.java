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

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName("plat_plan_subscription")
@Schema(name = "PlanSubscription", description = "套餐订阅信息")
public class PlanSubscription extends SuperEntity<Long> {
    
    @Schema(description = "套餐ID")
    private Long planId;
    
    @Schema(description = "租户ID")
    private Long tenantId;
    
    @Schema(description = "用户数量")
    private Integer userNum;
    
    @Schema(description = "月数")
    private Integer monthNum;
    
    @Schema(description = "用户单价")
    private BigDecimal licensePrice;
    
    @Schema(description = "总金额")
    private BigDecimal totalAmount;
    
    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;
    
    @Schema(description = "结算单价")
    private BigDecimal statementPrice;
    
    @Schema(description = "结算金额")
    private BigDecimal statementAmount;
    
    @Schema(description = "开始时间")
    private Instant startTime;
    
    @Schema(description = "结束时间")
    private Instant endTime;
    
    @Schema(description = "支付状态(0=待支付;10=部分支付;20=已支付)")
    private String paymentStatus;
    
    @Schema(description = "描述信息")
    private String description;
    
}