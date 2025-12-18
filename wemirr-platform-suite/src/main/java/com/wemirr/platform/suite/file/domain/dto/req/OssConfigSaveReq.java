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

package com.wemirr.platform.suite.file.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@Schema(name = "OssConfigSaveReq", description = "存储配置上传")
public class OssConfigSaveReq {
    
    @Schema(description = "存储类型")
    @NotBlank(message = "存储类型不能为空")
    private String type;
    /**
     * 是否启用存储 [true:启用;false:禁用]
     * 同个租户下只能开启一个配置
     */
    @Schema(description = "是否开启配置", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Boolean status;
    /**
     * AccessKey
     */
    @NotBlank(message = "AccessKey 不能为空")
    @Schema(description = "存储平台accessKey", requiredMode = Schema.RequiredMode.REQUIRED)
    private String accessKey;
    /**
     * SecretKey
     */
    @NotBlank(message = "SecretKey 不能为空")
    @Schema(description = "存储平台secretKey", requiredMode = Schema.RequiredMode.REQUIRED)
    private String secretKey;
    /**
     * 区域
     */
    @Schema(description = "存储平台region")
    private String region;
    /**
     * 访问域名
     */
    @NotBlank(message = "域名不能为空")
    @Schema(description = "访问域名,需要以/结尾", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://192.168.241.130:15000/test/")
    private String domain;
    /**
     * 桶名称
     */
    @NotBlank(message = "桶名称不能为空")
    @Schema(description = "桶名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "test")
    private String bucketName;
    /**
     * 基础路径
     */
    @NotBlank(message = "基础路径不能为空")
    @Schema(description = "基础路径", requiredMode = Schema.RequiredMode.REQUIRED, example = "test/")
    private String basePath;
    /**
     * 连接地址
     */
    @NotBlank(message = "连接地址不能为空")
    @Schema(description = "连接地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://192.168.241.131:9000")
    private String endPoint;

}
