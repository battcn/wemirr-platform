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

package com.wemirr.platform.iam.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.req.ResourcePageReq;
import com.wemirr.platform.iam.system.domain.dto.req.ResourceQueryReq;
import com.wemirr.platform.iam.system.domain.dto.req.ResourceSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.ResourcePageResp;
import com.wemirr.platform.iam.system.domain.dto.resp.VisibleResourceResp;
import com.wemirr.platform.iam.system.domain.entity.Resource;
import com.wemirr.platform.iam.system.domain.enums.ResourceType;
import com.wemirr.platform.iam.system.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.wemirr.platform.iam.system.domain.converts.MenuConverts.VUE_ROUTER_2_TREE_NODE_CONVERTS;
import static java.util.stream.Collectors.toList;

/**
 * 菜单资源
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
@Tag(name = "菜单资源", description = "菜单资源")
public class ResourceController {

    private final ResourceService resourceService;
    private final AuthenticationContext context;

    @GetMapping("/router")
    @Operation(summary = "菜单路由", description = "只能看到自身权限")
    public List<Tree<Long>> router(String clientId, Boolean status) {
        var routers = resourceService.findVisibleResource(ResourceQueryReq.builder().clientId(clientId)
                .status(status).userId(context.userId()).build());
        var list = routers.stream().filter(this::isValidRouterType).map(VUE_ROUTER_2_TREE_NODE_CONVERTS::convert).collect(toList());
        return TreeUtil.build(list, 0L);
    }

    @GetMapping("/trees")
    @Operation(summary = "菜单路由", description = "只能看到自身权限")
    public List<Tree<Long>> tree(String clientId) {
        var routers = resourceService.findVisibleResource(ResourceQueryReq.builder().clientId(clientId).userId(context.userId()).build());
        var list = routers.stream().map(VUE_ROUTER_2_TREE_NODE_CONVERTS::convert).collect(toList());
        return TreeUtil.build(list, 0L);
    }

    private boolean isValidRouterType(VisibleResourceResp router) {
        ResourceType type = router.getType();
        return type == ResourceType.MENU
                || type == ResourceType.DIRECTORY
                || type == ResourceType.IFRAME
                || type == ResourceType.LINK;
    }

    @Parameters({
            @Parameter(description = "父ID", name = "parentId", in = ParameterIn.QUERY),
            @Parameter(description = "资源类型", name = "type", in = ParameterIn.QUERY),
            @Parameter(description = "名称", name = "name", in = ParameterIn.QUERY),
            @Parameter(description = "类型", name = "type", in = ParameterIn.QUERY),
    })
    @Operation(summary = "资源列表 - [Levin] - [DONE]")
    @GetMapping("/page")
    @SaCheckPermission(value = {"sys:menu:page"})
    public IPage<ResourcePageResp> pageList(ResourcePageReq req) {
        return resourceService.page(req.buildPage(), Wraps.<Resource>lbQ()
                .eq(Resource::getParentId, req.getParentId())
                .eq(Resource::getType, req.getType())).convert(x -> BeanUtil.toBean(x, ResourcePageResp.class));
    }

    @GetMapping("/permissions")
    @Operation(summary = "资源码", description = "只能看到自身资源码")
    public List<String> permissions() {
        final List<VisibleResourceResp> routers = Optional.ofNullable(resourceService.findVisibleResource(ResourceQueryReq.builder()
                .userId(context.userId()).build())).orElseGet(Lists::newArrayList);
        return routers.stream().map(VisibleResourceResp::getPermission).filter(StrUtil::isNotBlank).distinct().collect(toList());
    }

    @PostMapping("/create")
    @AccessLog(module = "菜单资源", description = "添加资源")
    @Operation(summary = "添加资源")
    @SaCheckPermission(value = {"sys:menu:add"})
    public void create(@Validated @RequestBody ResourceSaveReq req) {
        resourceService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "菜单资源", description = "修改资源")
    @Operation(summary = "修改资源")
    @SaCheckPermission(value = {"sys:menu:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody ResourceSaveReq req) {
        resourceService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "菜单资源", description = "删除资源")
    @Operation(summary = "删除资源")
    @SaCheckPermission(value = {"sys:menu:remove"})
    public void del(@PathVariable Long id) {
        this.resourceService.delete(id);
    }

}
