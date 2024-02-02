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

package com.wemirr.framework.boot.response;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wemirr.framework.commons.exception.CheckedException;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * JSON 全局反序列化器
 *
 * @author Levin
 */
@JsonComponent
public class GlobalJsonDeserializer {
    
    /**
     * 字符串反序列化器
     * 过滤特殊字符，解决 XSS 攻击
     */
    public static class StringDeserializer extends JsonDeserializer<String> {
        
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            final String value = jsonParser.getValueAsString();
            if (SqlInjectionUtils.check(value)) {
                throw CheckedException.badRequest("存在SQL注入风险,已拦截");
            }
            // 替换 HTML 标签
            return HtmlUtil.escape(value);
        }
    }
}
