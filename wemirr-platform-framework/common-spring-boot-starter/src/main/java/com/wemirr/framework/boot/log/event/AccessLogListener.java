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
package com.wemirr.framework.boot.log.event;

import com.wemirr.framework.boot.log.AccessLogInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * 异步监听日志事件
 *
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class AccessLogListener {
    
    private final Consumer<AccessLogInfo> consumer;
    
    @Async
    @Order
    @EventListener(AccessLogEvent.class)
    public void accessLogListener(AccessLogEvent event) {
        AccessLogInfo info = (AccessLogInfo) event.getSource();
        if (info == null) {
            log.warn("日志为空，忽略操作日志...");
            return;
        }
        consumer.accept(info);
    }
    
}
