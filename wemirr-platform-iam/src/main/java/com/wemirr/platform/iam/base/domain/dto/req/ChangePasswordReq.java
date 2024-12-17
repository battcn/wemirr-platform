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

package com.wemirr.platform.iam.base.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 修改密码
 *
 * @author Levin
 */
@Data
@Schema(name = "ChangePasswordReq", description = "修改密码")
public class ChangePasswordReq {
    
    @Schema(description = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    @Length(max = 64, message = "旧密码长度不能超过 {max}")
    private String currentPassword;
    
    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    @Length(max = 64, message = "新密码长度不能超过 {max}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "密码不符合复杂性要求，必须包含至少8个字符，其中包括一个大写字母、一个小写字母、一个数字和一个特殊字符。")
    private String newPassword;
    
    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    @Length(max = 64, message = "确认密码长度不能超过 {max}")
    private String confirmPassword;
}
