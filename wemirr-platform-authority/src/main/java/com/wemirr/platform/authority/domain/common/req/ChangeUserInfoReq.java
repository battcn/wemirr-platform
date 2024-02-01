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

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 * 修改用户基本信息
 *
 * @author Levin
 */
@Data
@Schema(name = "ChangeUserInfoReq", description = "修改用户基本信息")
public class ChangeUserInfoReq {
    
    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Length(max = 64, message = "邮箱长度不能超过{max}")
    private String email;
    
    @Schema(description = "昵称")
    @NotEmpty(message = "昵称不能为空")
    @Length(max = 64, message = "昵称长度不能超过{max}")
    private String nickName;
    
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Length(max = 11, message = "手机号长度不能超过{max}")
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式错误")
    private String mobile;
    
    @Schema(description = "生日")
    private LocalDate birthday;
    
    @Schema(description = "描述")
    private String description;
    
}
