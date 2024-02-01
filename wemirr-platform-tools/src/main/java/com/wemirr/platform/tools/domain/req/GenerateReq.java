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
package com.wemirr.platform.tools.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateReq {
    
    /**
     * 开发人员
     */
    @NotBlank(message = "作者不能为空")
    private String author;
    /**
     * 开启 swagger2 模式
     */
    @Builder.Default
    private boolean swagger2 = false;
    /**
     * 表名
     */
    @NotBlank(message = "表名不能为空")
    private String tableName;
    @NotBlank(message = "包名不能为空")
    private String parentPackage;
    @NotBlank(message = "模块名不能为空")
    private String moduleName;
    private String tablePrefix;
    private String apiUrlPrefix;
    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteField;
    
    private String rootDir;
}
