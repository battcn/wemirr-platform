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
package com.wemirr.platform.authority.domain.common.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取验证码返回
 *
 * @author Levin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResp {
    
    /**
     * 验证码id
     */
    @Schema(description = "验证码ID")
    private String captchaId;
    
    /**
     * 验证码的值
     */
    @JsonIgnore
    @Schema(description = "验证码")
    private String code;
    
    /**
     * 图片验证码的base64值
     */
    @Schema(description = "验证码BASE64")
    private String imageData;
    
}
