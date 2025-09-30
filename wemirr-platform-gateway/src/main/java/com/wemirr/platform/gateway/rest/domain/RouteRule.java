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

package com.wemirr.platform.gateway.rest.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class RouteRule {
    
    @NotBlank(message = "ID不能为空")
    private String id;
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "URI不能为空")
    private String uri;
    private Boolean status;
    private List<Predicate> predicates;
    private List<Filter> filters;
    private String description;
    private Instant createTime;
    private Integer order;
    private Boolean dynamic;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filter {
        
        @NotBlank(message = "名称不能为空")
        private String name;
        private List<FilterArg> args;
        
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class FilterArg {
            
            private String key;
            private String value;
        }
    }
    
    @Data
    public static class Predicate {
        
        @NotBlank(message = "名称不能为空")
        private String name;
        private List<String> args;
    }
}
