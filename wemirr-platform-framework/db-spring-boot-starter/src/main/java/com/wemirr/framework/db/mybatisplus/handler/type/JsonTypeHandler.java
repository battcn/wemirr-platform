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

package com.wemirr.framework.db.mybatisplus.handler.type;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * 参考 {@link com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler} 实现
 * 字符串反序列化为 Set<Long> 时，如果元素的数值太小，会被处理成 Integer 类型，导致可能存在隐性的 BUG。
 *
 * @author Levin
 */
@MappedTypes(value = {JSONObject.class})
public class JsonTypeHandler extends AbstractJsonTypeHandler<JSONObject> {
    
    private static final TypeReference<Set<Long>> TYPE_REFERENCE = new TypeReference<>() {
    };
    
    @Override
    protected JSONObject parse(String json) {
        return JSON.parseObject(json, (Type) TYPE_REFERENCE);
    }
    
    @Override
    protected String toJson(JSONObject obj) {
        return obj.toJSONString();
    }
    
}