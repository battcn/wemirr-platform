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

package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源 查询DTO
 *
 * @author Levin
 * @since 2019/06/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "资源查询")
public class ResourceQueryReq {
    
    @Schema(description = "资源类型")
    private Integer type;
    
    @Schema(description = "父资源ID,用于查询按钮")
    private Long parentId;
    
    @Schema(description = "指定用户ID，前端不传则自动获取")
    private Long userId;
    
}
