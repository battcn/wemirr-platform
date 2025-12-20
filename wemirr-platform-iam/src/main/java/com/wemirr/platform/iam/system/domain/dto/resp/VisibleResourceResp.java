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

package com.wemirr.platform.iam.system.domain.dto.resp;

import com.wemirr.platform.iam.system.domain.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * 构建前端路由
 *
 * @author Levin
 */
@Data
public class VisibleResourceResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "归属应用")
    private String clientId;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "名称")
    private String title;

    @Schema(description = "父级菜单ID")
    private Long parentId;

    @Schema(description = "类型")
    private ResourceType type;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "页面缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效")
    private Boolean keepAlive;

    @Schema(description = "公共资源（无需分配所有人可访问）")
    private Boolean shared;

    @Schema(description = "是否可见")
    private Boolean visible;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "路由元信息(JSON)")
    private Map<String, Object> meta;
}
