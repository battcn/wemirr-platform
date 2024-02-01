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
package com.wemirr.platform.authority.domain.baseinfo.resp;

import com.wemirr.platform.authority.domain.baseinfo.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class ResourcePageResp {
    
    private Long id;
    
    @Schema(description = "权限编码")
    private String permission;
    
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String label;
    
    private Boolean readonly;
    
    /**
     * 菜单ID
     */
    private Long parentId;
    
    /**
     * '资源类型（1=按钮，0=菜单）'
     */
    private ResourceType type;
    
    private Integer sequence;
    
    private String style;
    
    private String icon;
    
    private String path;
    
    private String component;
    
    private String model;
    
    private String treePath;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    private Boolean global;
    private Boolean status;
    @Schema(description = "创建时间")
    private Instant createdTime;
    
}
