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

package com.wemirr.platform.iam.system.domain.dto.req;

import com.wemirr.platform.iam.system.domain.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author Levin
 */
@Data
@Schema(name = "ResourceSaveReq", description = "菜单/按钮创建DTO")
public class ResourceSaveReq {

    @Schema(description = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 20, message = "名称长度不能超过20")
    private String label;

    @Schema(description = "路径")
    @Length(max = 255, message = "路径长度不能超过 {max}")
    private String path;

    @Schema(description = "组件")
    @Length(max = 255, message = "组件长度不能超过 {max}")
    private String component;

    @Schema(description = "公共菜单")
    private Boolean global;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "菜单图标")
    @Length(max = 255, message = "菜单图标长度不能超过 {max}")
    private String icon;

    @Schema(description = "父级菜单ID")
    @NotNull(message = "父节点不能为空")
    private Long parentId;

    @Schema(description = "资源编码")
    @Length(max = 100, message = "资源编码长度不能超过{max}")
    private String permission;

    @Schema(description = "类型")
    private ResourceType type;

    @Schema(description = "是否隐藏")
    private Boolean display;

    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String description;

}
