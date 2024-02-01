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
package com.wemirr.framework.db.dynamic.core.redis;

import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceHandler;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Objects;

import static com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish.DEFAULT_EVENT_TOPIC;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class RedisDynamicDatasourceListener implements AbstractMessageEventListener<TenantDynamicDatasource> {
    
    private final TenantDynamicDataSourceHandler tenantDynamicDataSourceHandler;
    
    @Override
    public void handleMessage(TenantDynamicDatasource message) {
        if (Objects.isNull(message)) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        log.info("接收租户事件消息: - {}", message);
        tenantDynamicDataSourceHandler.handler(EventAction.of(message.getAction()), message);
    }
    
    @Override
    public Topic topic() {
        return new ChannelTopic(DEFAULT_EVENT_TOPIC);
    }
    
    @Override
    public Type type() {
        return TenantDynamicDatasource.class;
    }
}
