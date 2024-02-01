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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-28
 */
@Data
@Schema(name = "StationSaveReq", description = "岗位")
public class StationSaveReq {
    
    /**
     * 名称
     */
    @Schema(description = "名称")
    @Length(max = 255, message = "岗位名称长度不能超过{max}")
    @NotBlank(message = "岗位名称不能为空")
    private String name;
    
    @Schema(description = "岗位编码")
    private String code;
    
    @Schema(description = "排序")
    private Integer sequence;
    
    /**
     * 组织ID
     */
    @Schema(description = "组织ID")
    @NotNull(message = "组织不能为空")
    private Long orgId;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 255, message = "描述长度不能超过 {max}")
    private String description;
    
}
