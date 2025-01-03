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

package com.wemirr.platform.iam.auth.listener;

import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * TODO 方案暂时废弃,还是不优雅
 * 在线用户队列监听
 *
 * @author Levin
 */

@Slf4j
@Deprecated
@RequiredArgsConstructor
public class WpTokenRedisQueueListener implements AbstractMessageEventListener<String> {
    
    private final RedisProperties properties;
    
    @Override
    public void handleMessage(String message) {
        if (Objects.isNull(message)) {
            log.warn("event wp-token-redis-queue is null....");
            return;
        }
        log.info("receiver wp-token redis message: - {}", message);
    }
    
    @Override
    public Topic topic() {
        int database = properties.getDatabase();
        return new PatternTopic("__keyevent@" + database + "__:expired");
    }
    
    @Override
    public Type type() {
        return String.class;
    }
}
