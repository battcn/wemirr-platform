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
package com.wemirr.platform.tools.service.impl;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.tools.domain.DynamicReleaseDragSetting;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.repository.DynamicReleaseDragMapper;
import com.wemirr.platform.tools.service.DynamicReleaseGenerateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicReleaseGenerateServiceImpl implements DynamicReleaseGenerateService {
    
    private final DynamicReleaseDragMapper dynamicReleaseDragMapper;
    private final StringRedisTemplate stringRedisTemplate;
    
    @Override
    public void add(DynamicReleaseDragSetting req) {
        final DynamicReleaseDrag drag = this.dynamicReleaseDragMapper.selectById(req.getDragId());
        if (drag == null) {
            throw CheckedException.notFound("请先创建表单记录");
        }
        final String setting = JSON.toJSONString(req);
        DynamicReleaseDrag dynamicReleaseDrag = new DynamicReleaseDrag();
        dynamicReleaseDrag.setId(req.getDragId());
        dynamicReleaseDrag.setSetting(setting);
        this.dynamicReleaseDragMapper.updateById(dynamicReleaseDrag);
        final String tableName = drag.getTableName();
        stringRedisTemplate.delete(tableName);
        stringRedisTemplate.opsForValue().set(tableName, JSON.toJSONString(req));
    }
}
