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

package com.wemirr.platform.iam.base.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * @author Levin
 */
@Data
@Schema(description = "平台站点详情")
public class SiteSettingDetailResp {
    
    @Schema(description = "租户编码")
    private String tenantCode;
    
    @Schema(description = "标题")
    private String title;
    
    @Schema(description = "子标题")
    private String subTitle;
    
    @Schema(description = "LOGO")
    private String logo;
    
    @Schema(description = "扩展属性")
    private Map<String, Object> ext;
    
}
