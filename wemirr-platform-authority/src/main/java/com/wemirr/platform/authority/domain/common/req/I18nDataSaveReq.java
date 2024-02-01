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
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(description = "I18N国际化入参")
public class I18nDataSaveReq {
    
    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "各国语言")
    private List<LocaleLanguage> languages;
    
    @Data
    public static class LocaleLanguage {
        
        @Schema(description = "语言")
        private String locale;
        
        @Schema(description = "消息")
        private String message;
        
    }
    
}
