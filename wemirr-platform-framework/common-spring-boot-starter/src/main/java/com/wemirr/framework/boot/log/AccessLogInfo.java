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
package com.wemirr.framework.boot.log;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 访问日志
 *
 * @author Levin
 * @since 2023-10-13
 */
@Data
public class AccessLogInfo {
    
    @Schema(description = "租户ID")
    protected Long tenantId;
    
    @Schema(description = "操作IP")
    protected String ip;
    
    @Schema(description = "地区信息")
    protected String location;
    
    @Schema(description = "日志链路追踪id日志标志")
    protected String trace;
    
    @Schema(description = "操作描述")
    protected String description;
    
    @Schema(description = "请求方法")
    protected String action;
    
    @Schema(description = "请求地址")
    protected String uri;
    
    @Schema(description = "HTTP Method")
    protected String httpMethod;
    
    @Schema(description = "请求参数")
    protected String request;
    
    @Schema(description = "返回值")
    protected String response;
    
    @Schema(description = "异常描述")
    protected String message;
    
    @Schema(description = "日志状态")
    private Boolean status;
    
    @Schema(description = "开始时间")
    protected Instant startTime;
    
    @Schema(description = "完成时间")
    protected Instant finishTime;
    
    @Schema(description = "消耗时间")
    protected Long consumingTime;
    
    @Schema(description = "浏览器信息")
    protected String browser;
    
    @Schema(description = "浏览器引擎")
    protected String engine;
    
    @Schema(description = "操作系统")
    protected String os;
    
    @Schema(description = "平台")
    protected String platform;
    
    @Schema(description = "创建者ID")
    protected Long createdBy;
    
    @Schema(description = "创建者名字")
    protected String createdName;
    
    @TableField(exist = false)
    @Schema(description = "数据源键值")
    private String dsKey;
    
    public Boolean getStatus() {
        return StrUtil.isBlank(message);
    }
}
