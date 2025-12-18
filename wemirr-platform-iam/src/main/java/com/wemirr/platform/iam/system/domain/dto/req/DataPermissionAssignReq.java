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

import com.wemirr.framework.commons.security.DataResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 数据权限分配请求
 *
 * @author Levin
 */
@Data
@Schema(name = "DataPermissionAssignReq", description = "数据权限分配请求")
public class DataPermissionAssignReq {
    
    @NotNull(message = "数据类型不能为空")
    @Schema(description = "数据类型")
    private DataResourceType dataType;
    
    @NotNull(message = "权限范围类型不能为空")
    @Schema(description = "权限范围类型: 10-个人, 20-自定义, 30-本级, 40-本级及子级, 50-全部")
    private Integer scopeType;
    
    @Schema(description = "数据ID列表（自定义时使用）")
    private List<Long> dataIds;
}
