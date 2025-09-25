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

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class MessageNotifyPageResp {
    
    @Schema(description = "ID")
    private Long id;
    
    @Schema(description = "标题")
    private String title;
    
    @Schema(description = "消息类型")
    private String type;
    
    @Schema(description = "消息模板ID")
    private Long templateId;
    
    @Schema(description = "消息变量")
    private String variables;
    
    @Schema(description = "内容")
    private String content;
    
    @Schema(description = "接收用户ID")
    private Long userId;
    
    @Schema(description = "订阅人")
    private String nickname;
    
    @Schema(description = "创建人")
    private String createName;
    
    @Schema(description = "创建时间")
    private Instant createTime;
    
}
