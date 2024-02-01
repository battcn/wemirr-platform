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

package com.wemirr.platform.authority.domain.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class DictionarySaveReq {
    
    @Schema(description = "字典编码(字典编码唯一)")
    @NotBlank(message = "编码不能为空")
    @Length(max = 64, message = "类型长度不能超过64")
    private String code;
    
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过64")
    private String name;
    
    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String description;
    
    @Schema(description = "状态")
    private Boolean status;
    
}
