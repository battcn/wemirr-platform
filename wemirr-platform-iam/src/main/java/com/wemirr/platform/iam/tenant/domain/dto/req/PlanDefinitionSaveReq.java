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

package com.wemirr.platform.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

/**
 * @author Levin
 */
@Data
@Schema(name = "ProductDefinitionSaveReq")
public class PlanDefinitionSaveReq {

    @NotBlank(message = "套餐名称不能为空")
    @Length(min = 1, max = 100, message = "套餐名称长度 {min} - {max}")
    @Schema(description = "套餐名称")
    private String name;

    @Schema(description = "套餐Logo链接")
    private String logo;

    @Length(max = 10086, message = "套餐描述超过 {max} 描述,请简化描述")
    @Schema(description = "套餐描述")
    private String description;


    @Schema(description = "套餐关联资源ID")
    @NotEmpty(message = "套餐关联资源不存在")
    @Size(min = 1, message = "套餐关联资源最少 {min} 个")
    private Set<Long> itemIdList;

}
