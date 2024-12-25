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
import com.wemirr.platform.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.ProductDefinitionPageResp;
import com.wemirr.platform.iam.tenant.domain.entity.ProductDefinition;
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
@RequestMapping("/product-definitions")
@Tag(name = "产品定义", description = "产品定义")
public class ProductDefinitionController {

    private final ProductDefinitionService productDefinitionService;

    @GetMapping("/list")
    @Operation(summary = "产品列表", description = "产品列表")
    public List<Dict<?>> list(String name, Boolean status) {
        return productDefinitionService.list(Wraps.<ProductDefinition>lbQ().likeRight(ProductDefinition::getName, name)
                        .eq(ProductDefinition::getStatus, status))
                .stream()
                .map(x -> Dict.builder().label(x.getName()).value(x.getId()).build())
                .collect(Collectors.toList());

    }

    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public IPage<ProductDefinitionPageResp> pageList(PageRequest req, String code, String name, Boolean status) {
        return productDefinitionService.page(req.buildPage(), Wraps.<ProductDefinition>lbQ().eq(ProductDefinition::getCode, code)
                .likeRight(ProductDefinition::getName, name)
                .eq(ProductDefinition::getStatus, status)).convert(x -> BeanUtil.toBean(x, ProductDefinitionPageResp.class));
    }

    @PostMapping
    @Operation(summary = "添加产品", description = "添加产品")
    public void create(@RequestBody ProductDefinitionSaveReq req) {
        productDefinitionService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑产品", description = "编辑产品")
    public void modify(@PathVariable Long id, @RequestBody ProductDefinitionSaveReq req) {
        productDefinitionService.modify(id, req);
    }

    @GetMapping("/{id}/permissions")
    @Operation(summary = "编辑产品", description = "编辑产品")
    public RolePermissionResp permissions(@PathVariable Long id) {
        return productDefinitionService.findPermissions(id);
    }

    @PutMapping("/{id}/permissions")
    @AccessLog(module = "产品定义", description = "产品授权", response = false)
    @Operation(summary = "产品授权", description = "产品授权")
    public void permissions(@PathVariable Long id, @Validated @RequestBody ProductDefPermissionReq req) {
        productDefinitionService.permissions(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除产品", description = "删除产品")
    public void delete(@PathVariable Long id) {
        productDefinitionService.delete(id);
    }
}
