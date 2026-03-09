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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.framework.commons.JacksonUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 暂未优化,没考虑好如何优雅设计
 *
 * @author Levin
 */
@Data
public class FastCrudDialect {

    public static Map<String, Object> toFastCrud(String text) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = JacksonUtils.readTree(text);

        // 手动遍历查找包含 field 属性的节点
        List<EpicDesignerField> fields = new ArrayList<>();
        findFieldNodes(root, fields, mapper);

        return toFastCrud(fields);
    }

    private static void findFieldNodes(JsonNode node, List<EpicDesignerField> fields, ObjectMapper mapper) {
        if (node.isObject() && node.has("field")) {
            EpicDesignerField field = mapper.convertValue(node, EpicDesignerField.class);
            fields.add(field);
        }

        // 递归遍历所有子节点
        if (node.isObject()) {
            node.properties().forEach(entry ->
                    findFieldNodes(entry.getValue(), fields, mapper)
            );
        } else if (node.isArray()) {
            node.forEach(child -> findFieldNodes(child, fields, mapper));
        }
    }

    public static Map<String, Object> toFastCrudField(EpicDesignerField field) {
        if (field == null) {
            throw new RuntimeException("Field 不能为空");
        }
        Map<String, Object> item = new HashMap<>() {

            {
                put("search", new HashMap<String, Object>() {

                    {
                        put("show", true);
                    }
                });
                put("column", new HashMap<String, Object>() {

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
        item.put("dict", String.format("dict({data:%s})", JacksonUtils.toJson(options)));
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

    public static Map<String, Object> toFastCrud(List<EpicDesignerField> fieldList) {
        if (CollUtil.isEmpty(fieldList)) {
            return null;
        }
        Map<String, Object> out = new HashMap<>();
        for (EpicDesignerField field : fieldList) {
            out.put(field.getField(), toFastCrudField(field));
        }
        return out;
    }
}
