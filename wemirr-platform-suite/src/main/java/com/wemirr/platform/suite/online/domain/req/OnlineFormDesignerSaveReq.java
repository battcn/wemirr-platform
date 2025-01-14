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

package com.wemirr.platform.suite.online.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class OnlineFormDesignerSaveReq {
    
    @NotBlank(message = "定义KEY不能为空")
    @Schema(description = "定义KEY")
    private String definitionKey;
    
    @NotBlank(message = "模型标题不能为空")
    @Schema(description = "标题")
    private String title;
    
    @NotBlank(message = "描述不能为空")
    @Schema(description = "描述")
    private String description;
    
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态")
    private Boolean status;
    
}
