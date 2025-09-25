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

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class ResourcePageResp {
    
    @Schema(description = "ID")
    private Long id;
    
    @Schema(description = "权限编码")
    private String permission;
    
    @Schema(description = "名称")
    private String title;
    
    @Schema(description = "是否只读")
    private Boolean readonly;
    
    @Schema(description = "父级ID")
    private Long parentId;
    
    @Schema(description = "资源类型（2=按钮，1=菜单）'")
    private ResourceType type;
    
    @Schema(description = "排序")
    private Integer sequence;
    
    @Schema(description = "图标")
    private String icon;
    
    @Schema(description = "路径")
    private String path;
    
    @Schema(description = "组件")
    private String component;
    
    @Schema(description = "是否全局菜单")
    private Boolean global;
    
    @Schema(description = "状态")
    private Boolean status;
    
    @Schema(description = "创建时间")
    private Instant createTime;
    
    @Schema(description = "描述信息")
    private String description;
}
