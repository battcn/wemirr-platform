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
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.Dict;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.tenant.domain.dto.req.PlanDefPermissionReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.PlanDefinitionSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.PlanDefinitionDetailResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.PlanDefinitionPageResp;
import com.wemirr.platform.iam.tenant.domain.entity.PlanDefinition;
import com.wemirr.platform.iam.tenant.service.ProductDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/plan-definitions")
@Tag(name = "套餐定义", description = "套餐定义")
public class PlanDefinitionController {

    private final ProductDefinitionService productDefinitionService;

    @GetMapping("/list")
    @Operation(summary = "套餐列表", description = "套餐列表")
    public List<Dict<?>> list(String name, Boolean status) {
        return productDefinitionService.list(Wraps.<PlanDefinition>lbQ().likeRight(PlanDefinition::getName, name)
                        .eq(PlanDefinition::getStatus, status))
                .stream()
                .map(x -> Dict.builder().label(x.getName()).value(x.getId()).build())
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "套餐详情", description = "套餐详情")
    public PlanDefinitionDetailResp detail(@PathVariable Long id) {
        return productDefinitionService.detail(id);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public IPage<PlanDefinitionPageResp> pageList(PageRequest req, String code, String name, Boolean status) {
        return productDefinitionService.page(req.buildPage(), Wraps.<PlanDefinition>lbQ().eq(PlanDefinition::getCode, code)
                .likeRight(PlanDefinition::getName, name)
                .eq(PlanDefinition::getStatus, status)).convert(x -> BeanUtil.toBean(x, PlanDefinitionPageResp.class));
    }

    @PostMapping
    @Operation(summary = "添加套餐", description = "添加套餐")
    public void create(@RequestBody PlanDefinitionSaveReq req) {
        productDefinitionService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑套餐", description = "编辑套餐")
    public void modify(@PathVariable Long id, @RequestBody PlanDefinitionSaveReq req) {
        productDefinitionService.modify(id, req);
    }

    @GetMapping("/{id}/permissions")
    @Operation(summary = "编辑套餐", description = "编辑套餐")
    public RolePermissionResp permissions(@PathVariable Long id) {
        return productDefinitionService.findPermissions(id);
    }

    @PutMapping("/{id}/permissions")
    @AccessLog(module = "套餐定义", description = "套餐授权", response = false)
    @Operation(summary = "套餐授权", description = "套餐授权")
    public void permissions(@PathVariable Long id, @Validated @RequestBody PlanDefPermissionReq req) {
        productDefinitionService.permissions(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除套餐", description = "删除套餐")
    public void delete(@PathVariable Long id) {
        productDefinitionService.delete(id);
    }
}
