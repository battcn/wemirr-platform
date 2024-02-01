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

import com.wemirr.platform.authority.domain.baseinfo.enums.ReceiverType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class SiteMessageResp {
    
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "级别")
    private String level;
    @Schema(description = "接收类型")
    private ReceiverType type;
    @Schema(description = "接收人")
    private List<Long> receiver;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "创建人")
    private String createdName;
    @Schema(description = "创建时间")
    private Instant createdTime;
    
}
