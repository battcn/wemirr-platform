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

package com.wemirr.framework.boot.log.feign;

import com.wemirr.framework.boot.log.AccessLogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.wemirr.framework.boot.log.feign.AccessLogFeign.FEIGN_CLIENT_NAME;

/**
 * 访问日志的 Feign
 *
 * @author Levin
 */
@FeignClient(name = FEIGN_CLIENT_NAME, dismiss404 = true)
public interface AccessLogFeign {
    
    String FEIGN_CLIENT_NAME = "wemirr-platform-authority";
    
    /**
     * 日志监听
     *
     * @param info 日志信息
     */
    @PostMapping("/opt_logs/listener")
    void listener(@RequestBody AccessLogInfo info);
    
}
