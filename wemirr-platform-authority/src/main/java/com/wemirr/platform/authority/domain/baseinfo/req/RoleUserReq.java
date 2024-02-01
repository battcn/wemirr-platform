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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-27
 */
@Data
@Schema(name = "RoleUserReq", description = "角色的资源")
public class RoleUserReq {
    
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotEmpty(message = "分配用户不能为空")
    @Size(min = 1, message = "至少勾选 {min} 条数据")
    private List<Long> userIdList;
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;
    
}
