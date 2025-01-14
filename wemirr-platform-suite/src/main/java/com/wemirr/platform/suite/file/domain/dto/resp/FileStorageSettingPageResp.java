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

package com.wemirr.platform.suite.file.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class FileStorageSettingPageResp {
    
    @Schema(description = "ID")
    private Long id;
    
    @Schema(description = "存储类型")
    private String type;
    
    @Schema(description = "是否开启配置")
    private Boolean status;
    
    @Schema(description = "存储平台accessKey")
    private String accessKey;
    
    @Schema(description = "存储平台secretKey")
    private String secretKey;
    
    @Schema(description = "访问域名,需要以/结尾")
    private String domain;
    
    @Schema(description = "桶名称")
    private String bucketName;
    
    @Schema(description = "基础路径")
    private String basePath;
    
    @Schema(description = "连接地址")
    private String endPoint;
    
    @Schema(description = "创建时间")
    private Instant createdTime;
}
