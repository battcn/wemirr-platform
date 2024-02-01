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
package com.wemirr.platform.tools.domain.resp;

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
public class DynamicReleaseGridColumnResp {
    
    @NotBlank(message = "字段不能为空")
    private String key;
    @NotBlank(message = "标题不能为空")
    private String title;
    private Boolean disabled;
    @NotBlank(message = "类型不能为空")
    private String type;
    private DynamicReleaseGridColumnForm form;
    private DynamicReleaseGridColumnSearch search;
    private DynamicReleaseGridColumnDict dict;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnForm {
        
        private Boolean disabled;
        private DynamicReleaseGridColumnFormRule rules;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnSearch {
        
        private Boolean disabled;
        private String expression;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnDict {
        
        private String code;
        private String url;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnFormRule {
        
        private Boolean required;
        private String type;
    }
}
