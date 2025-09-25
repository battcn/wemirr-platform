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
import java.util.List;

/**
 * @author levin
 */
@Data
public class MessageTemplatePageResp {
    
    @Schema(description = "模板ID")
    private Long id;
    
    @Schema(description = "模板编码")
    private String code;
    
    @Schema(description = "模板名称")
    private String name;
    
    @Schema(description = "类型")
    private List<String> type;
    
    @Schema(description = "消息标题（如邮件标题）")
    private String subject;
    
    @Schema(description = "消息内容模板，支持占位符")
    private String content;
    
    @Schema(description = "创建人")
    private String createName;
    
    @Schema(description = "创建时间")
    private Instant createTime;
}
