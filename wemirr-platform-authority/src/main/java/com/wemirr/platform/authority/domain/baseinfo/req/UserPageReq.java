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

package com.wemirr.platform.authority.domain.baseinfo.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.authority.domain.baseinfo.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageReq extends PageRequest {
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "昵称")
    private String nickName;
    
    @Schema(description = "性别")
    private Sex sex;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "组织ID")
    private Long orgId;
    
    @Schema(description = "手机号")
    private String mobile;
    
    @Schema(description = "状态")
    private Boolean status;
    
    @Schema(description = "状态")
    private String education;
    
}
