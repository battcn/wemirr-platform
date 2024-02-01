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
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-28
 */
@Data
@Schema(name = "OrgSaveReq", description = "组织")
public class OrgSaveReq {
    
    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过 {max}")
    private String label;
    /**
     * 简称
     */
    @Schema(description = "简称")
    @Length(max = 255, message = "简称长度不能超过 {max}")
    private String abbreviation;
    
    @Schema(description = "部门电话")
    private String tel;
    /**
     * 父ID
     */
    @Schema(description = "父ID")
    private Long parentId;
    
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sequence;
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
