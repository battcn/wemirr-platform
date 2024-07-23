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

package com.wemirr.framework.i18n.core;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.i18n.I18nMessageProvider;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.framework.i18n.domain.I18nRedisKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class I18nMessageRedisProvider implements I18nMessageProvider {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getI18nMessage(String code, Locale locale) {
        String buildKey = I18nMessage.builder().code(code).locale(locale.toString()).build().buildKey();
        I18nMessage message = (I18nMessage) redisTemplate.opsForHash().get(I18nRedisKeyConstants.I18N_DATA_PREFIX, buildKey);
        return message == null ? null : message.getMessage();
    }

    @Override
    public List<I18nMessage> list() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        return entries.values().stream().filter(Objects::nonNull).map(x -> (I18nMessage) x).toList();
    }

    @Override
    public void loadI18nMessage(List<I18nMessage> messages) {
        if (messages == null) {
            return;
        }
        redisTemplate.delete(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        final Map<String, I18nMessage> map = messages.stream().collect(Collectors.toMap(I18nMessage::buildKey, Function.identity()));
        redisTemplate.opsForHash().putAll(I18nRedisKeyConstants.I18N_DATA_PREFIX, map);
    }

    public void publish(List<I18nMessage> list) {
        if (list == null) {
            return;
        }
        for (I18nMessage message : list) {
            publish(message);
        }
    }

    public void publish(I18nMessage message) {
        redisTemplate.convertAndSend(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED, JSON.toJSONString(message));
    }
}
