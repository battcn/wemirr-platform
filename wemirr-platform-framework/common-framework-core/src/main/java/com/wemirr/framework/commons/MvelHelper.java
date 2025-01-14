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

package com.wemirr.framework.commons;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Levin
 */
@Slf4j
public class MvelHelper {
    
    private static final Pattern DEFAULT_VARIABLES_PATTERN = Pattern.compile("\\$\\{([^}]+)}");
    
    public static List<String> getVariables(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        List<String> variables = new ArrayList<>();
        while (matcher.find()) {
            variables.add(StrUtil.trim(matcher.group(1)));
        }
        return variables;
    }
    
    public static List<String> getVariables(String content) {
        Matcher matcher = DEFAULT_VARIABLES_PATTERN.matcher(content);
        List<String> variables = new ArrayList<>();
        while (matcher.find()) {
            variables.add(StrUtil.trim(matcher.group(1)));
        }
        return variables;
    }
    
    /**
     * 格式化忽略异常
     *
     * @param pattern   pattern
     * @param content   内容
     * @param variables 变量
     * @return 格式化结果
     */
    public static String formatIgnoreError(Pattern pattern, String content, Map<String, Object> variables) {
        return format(pattern, content, variables, true, true);
    }
    
    /**
     * 使用默认配置格式化
     *
     * @param content   内容
     * @param variables 变量
     * @return 格式化结果
     */
    public static String format(String content, Map<String, Object> variables) {
        return format(DEFAULT_VARIABLES_PATTERN, content, variables, true, true);
    }
    
    /**
     * 使用默认正则格式化
     *
     * @param content     内容
     * @param variables   变量
     * @param ignoreError 忽略异常
     * @return 格式化结果
     */
    public static String format(String content, Map<String, Object> variables, boolean ignoreError) {
        return format(DEFAULT_VARIABLES_PATTERN, content, variables, true, ignoreError);
    }
    
    /**
     * 使用默认正则格式化
     *
     * @param pattern   表达式
     * @param content   内容
     * @param variables 变量
     * @return 格式化结果
     */
    public static String format(Pattern pattern, String content, Map<String, Object> variables) {
        return format(pattern, content, variables, true, true);
    }
    
    /**
     * 使用指定正则格式化
     *
     * @param pattern     正则
     * @param content     内容
     * @param variables   变量
     * @param trans       是否将 variables 转换嵌套MAP
     * @param ignoreError 忽略异常
     * @return 格式化结果
     */
    public static String format(Pattern pattern, String content, Map<String, Object> variables, boolean trans, boolean ignoreError) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = pattern.matcher(content);
        if (trans && CollUtil.isNotEmpty(variables)) {
            variables.putAll(transNestedMap(variables));
        }
        while (matcher.find()) {
            // 使用 MVEL 解析表达式
            String group = matcher.group(1);
            if (ignoreError) {
                try {
                    String value = MVEL.evalToString(group, variables);
                    matcher.appendReplacement(result, StrUtil.blankToDefault(value, StrUtil.EMPTY));
                } catch (Exception ex) {
                    log.error("eval exception => {}", ex.getLocalizedMessage());
                }
            } else {
                String value = MVEL.evalToString(group, variables);
                matcher.appendReplacement(result, StrUtil.blankToDefault(value, StrUtil.EMPTY));
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> transNestedMap(Map<String, Object> variables) {
        Map<String, Object> nestedMap = MapUtil.newHashMap();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String[] keys = entry.getKey().split("\\.");
            Map<String, Object> current = nestedMap;
            for (int i = 0; i < keys.length; i++) {
                if (i == keys.length - 1) {
                    current.put(keys[i], entry.getValue());
                } else {
                    current = (Map<String, Object>) current.computeIfAbsent(keys[i], k -> MapUtil.newHashMap());
                }
            }
        }
        return nestedMap;
    }
    
}
