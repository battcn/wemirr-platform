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
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.platform.iam.system.domain.dto.req.DataPermissionAssignReq;
import com.wemirr.platform.iam.system.domain.dto.resp.DataPermissionResp;
import com.wemirr.platform.iam.system.domain.entity.DataPermissionRef;
import com.wemirr.platform.iam.system.service.DataPermissionRefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 数据权限管理
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/data-permissions")
@RequiredArgsConstructor
@Tag(name = "数据权限管理", description = "通用数据权限管理接口")
public class DataPermissionRefController {

    private final DataPermissionRefService dataPermissionRefService;

    @GetMapping("/{ownerType}/{ownerId}")
    @Operation(summary = "查询数据权限")
    public List<DataPermissionResp> list(
            @Parameter(description = "所有者类型") @PathVariable DataResourceType ownerType,
            @Parameter(description = "所有者ID") @PathVariable Long ownerId,
            @Parameter(description = "数据类型") @RequestParam(required = false) DataResourceType dataType) {

        var refList = dataPermissionRefService.listByOwner(ownerType, ownerId, dataType);

        // 按 dataType 分组
        Map<DataResourceType, List<DataPermissionRef>> dataTypeMap = refList.stream()
                .filter(ref -> ref.getDataType() != null)
                .collect(Collectors.groupingBy(DataPermissionRef::getDataType));

        return dataTypeMap.entrySet().stream().map(entry -> {
            var refs = entry.getValue();
            var scopeType = refs.getFirst().getScopeType();
            var dataIds = refs.stream()
                    .map(DataPermissionRef::getDataId)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
            return DataPermissionResp.builder()
                    .dataType(entry.getKey())
                    .scopeType(scopeType)
                    .dataIds(dataIds)
                    .build();
        }).toList();
    }

    @PutMapping("/{ownerType}/{ownerId}")
    @Operation(summary = "分配数据权限")
    @AccessLog(module = "数据权限", description = "分配数据权限")
//    @SaCheckPermission(value = {"sys:data-permission:assign"})
    public void assign(
            @Parameter(description = "所有者类型") @PathVariable DataResourceType ownerType,
            @Parameter(description = "所有者ID") @PathVariable Long ownerId,
            @Validated @RequestBody List<DataPermissionAssignReq> reqList) {

        dataPermissionRefService.assignBatch(ownerType, ownerId, reqList);
    }

    @DeleteMapping("/{ownerType}/{ownerId}")
    @AccessLog(module = "数据权限", description = "删除数据权限")
    @Operation(summary = "删除数据权限")
    @SaCheckPermission(value = {"sys:data-permission:delete"})
    public void remove(
            @Parameter(description = "所有者类型") @PathVariable DataResourceType ownerType,
            @Parameter(description = "所有者ID") @PathVariable Long ownerId,
            @Parameter(description = "数据类型") @RequestParam(required = false) DataResourceType dataType) {

        dataPermissionRefService.removeByOwner(ownerType, ownerId, dataType);
    }
}
