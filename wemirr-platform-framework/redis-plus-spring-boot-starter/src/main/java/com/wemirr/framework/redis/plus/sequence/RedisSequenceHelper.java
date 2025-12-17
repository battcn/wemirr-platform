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

package com.wemirr.framework.redis.plus.sequence;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Levin
 */
@Slf4j
public record RedisSequenceHelper(StringRedisTemplate redisTemplate) {

    public String generate(Sequence sequence) {
        DateTimeFormatter formatter = sequence.formatter();
        String localDate = "";
        if (formatter != null) {
            localDate = LocalDateTime.now().format(formatter);
        }
        Long increment = redisTemplate.opsForValue().increment(StrUtil.join(sequence.key(), localDate));
        return sequence.prefix()
                + localDate
                + StrUtil.blankToDefault(sequence.delimiter(), "")
                + StrUtil.padPre(increment + "", sequence.size(), '0');
    }

    /**
     * 按规则生成流水号
     *
     * @param sequence     规则
     * @param isolationKey 隔离（比如 租户ID）
     * @return 流水号 = 前缀 + 日期时间 + 自增流水号
     */
    public String generate(Sequence sequence, Object isolationKey) {
        DateTimeFormatter formatter = sequence.formatter();
        String localDate = "";
        if (formatter != null) {
            localDate = LocalDateTime.now().format(formatter);
        }
        Long increment = redisTemplate.opsForHash().increment(sequence.key() + localDate, String.valueOf(isolationKey), 1);
        return sequence.prefix()
                + localDate
                + StrUtil.blankToDefault(sequence.delimiter(), "")
                + StrUtil.padPre(increment + "", sequence.size(), '0');
    }

}
