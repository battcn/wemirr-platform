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

package com.wemirr.platform.iam.tenant.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.tenant.domain.dto.req.PlanSubscriptionSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.PlanSubscriptionPageResp;
import com.wemirr.platform.iam.tenant.domain.entity.PlanSubscription;
import com.wemirr.platform.iam.tenant.service.ProductSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@RequestMapping("/plan-subscriptions")
@RestController
@RequiredArgsConstructor
@Tag(name = "套餐订阅", description = "套餐订阅")
public class PlanSubscriptionController {
    
    private final ProductSubscriptionService productSubscriptionService;
    
    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public IPage<PlanSubscriptionPageResp> pageList(PageRequest req, Long tenantId, Long planId, Boolean status) {
        return productSubscriptionService.page(req.buildPage(), Wraps.<PlanSubscription>lbQ()
                .eq(PlanSubscription::getPlanId, planId)
                .eq(PlanSubscription::getTenantId, tenantId)
                .eq(PlanSubscription::getPaymentStatus, status))
                .convert(x -> BeanUtil.toBean(x, PlanSubscriptionPageResp.class));
    }
    
    @PostMapping
    @Operation(summary = "添加订阅", description = "添加订阅")
    public void create(@Validated @RequestBody PlanSubscriptionSaveReq req) {
        productSubscriptionService.create(req);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "编辑订阅", description = "编辑订阅")
    public void modify(@PathVariable Long id, @Validated @RequestBody PlanSubscriptionSaveReq req) {
        productSubscriptionService.modify(id, req);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除订阅", description = "删除订阅")
    public void delete(@PathVariable Long id) {
        productSubscriptionService.removeById(id);
    }
    
}
