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

package com.wemirr.framework.redis.plus.listener;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis消息监听处理器
 *
 * @author Levin
 */
public interface AbstractMessageEventListener<T> extends MessageEventListener {
    
    /**
     * 公共消息处理
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    default void onMessage(Message message, byte[] pattern) {
        byte[] channelBytes = message.getChannel();
        final StringRedisTemplate template = SpringUtil.getBean(StringRedisTemplate.class);
        final RedisSerializer<String> stringSerializer = template.getStringSerializer();
        String channelTopic = stringSerializer.deserialize(channelBytes);
        String topic = topic().getTopic();
        if (topic.equals(channelTopic)) {
            byte[] bodyBytes = message.getBody();
            String body = stringSerializer.deserialize(bodyBytes);
            handleMessage(JSON.parseObject(body, type()));
        }
    }
    
    /**
     * 处理消息
     *
     * @param decodeMessage 反系列化之后的消息
     */
    void handleMessage(T decodeMessage);
    
}