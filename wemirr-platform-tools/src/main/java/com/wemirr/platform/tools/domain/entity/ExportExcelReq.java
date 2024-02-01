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
package com.wemirr.platform.tools.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class ExportExcelReq {
    
    private List<DynamicReleaseColumnReq> columns;
    
    @Data
    public static class DynamicReleaseColumnReq {
        
        private String title;
        private String key;
        private String type;
        private Boolean showOverflowTooltip;
        private DynamicReleaseColumnFormReq form;
        private DynamicReleaseColumnDictReq dict;
        private Boolean show;
        private Boolean disabled;
        private Integer width;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnFormReq {
        
        private Boolean disabled;
        private DynamicReleaseCurdOptionResp.DynamicReleaseColumnFormRuleResp rules;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnDictReq {
        
        private String value;
        private String label;
        private String url;
        private List<DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictData> data;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnDictData {
        
        private String value;
        private String label;
    }
    
}
