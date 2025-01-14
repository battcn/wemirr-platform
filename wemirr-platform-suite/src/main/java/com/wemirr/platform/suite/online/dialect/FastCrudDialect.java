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

package com.wemirr.platform.suite.online.dialect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import lombok.Data;

import java.util.List;

/**
 * 暂未优化,没考虑好如何优雅设计
 *
 * @author Levin
 */
@Data
public class FastCrudDialect {
    
    public static JSONObject toFastCrud(String text) {
        // 将JSON字符串解析为对象
        JSONArray schemas = (JSONArray) JSONPath.eval(text, "$..[?(@.field)]");
        return toFastCrud(schemas.toJavaList(EpicDesignerField.class));
    }
    
    public static JSONObject toFastCrudField(EpicDesignerField field) {
        if (field == null) {
            throw new RuntimeException("Field 不能为空");
        }
        JSONObject item = new JSONObject() {
            
            {
                put("search", new JSONObject() {
                    
                    {
                        put("show", true);
                    }
                });
                put("column", new JSONObject() {
                    
                    {
                        put("show", true);
                        put("width", 200);
                    }
                });
                put("type", getType(field));
                put("title", field.getLabel());
            }
        };
        EpicDesignerField.ComponentProps props = field.getComponentProps();
        if (props == null || CollUtil.isEmpty(props.getOptions())) {
            return item;
        }
        List<EpicDesignerField.Option> options = props.getOptions();
        item.put("dict", String.format("dict({data:%s})", JSON.toJSONString(options)));
        return item;
    }
    
    private static String getType(EpicDesignerField field) {
        if (StrUtil.equalsIgnoreCase(field.getType(), "input")) {
            return "text";
        }
        if (StrUtil.equalsIgnoreCase(field.getType(), "radio")) {
            return "dict-radio";
        }
        if (StrUtil.equalsIgnoreCase(field.getType(), "checkbox")) {
            return "dict-checkbox";
        }
        return field.getType();
    }
    
    public static JSONObject toFastCrud(List<EpicDesignerField> fieldList) {
        if (CollUtil.isEmpty(fieldList)) {
            return null;
        }
        JSONObject out = new JSONObject();
        for (EpicDesignerField field : fieldList) {
            out.put(field.getField(), toFastCrudField(field));
        }
        return out;
    }
}
