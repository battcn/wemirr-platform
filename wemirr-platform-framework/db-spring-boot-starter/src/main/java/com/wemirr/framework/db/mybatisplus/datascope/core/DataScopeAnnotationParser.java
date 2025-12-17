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

package com.wemirr.framework.db.mybatisplus.datascope.core;

import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataColumn;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataScope;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据权限注解解析器
 * <p>高性能缓存设计，避免重复反射解析</p>
 *
 * @author Levin
 */
@Slf4j
public final class DataScopeAnnotationParser {

    /**
     * 解析结果缓存，key为mappedStatementId
     */
    private static final Map<String, DataScopeRule> RULE_CACHE = new ConcurrentHashMap<>(256);

    /**
     * 空规则标记，用于缓存未配置注解的方法，避免重复解析
     */
    private static final DataScopeRule EMPTY_MARKER = DataScopeRule.builder().build();

    private DataScopeAnnotationParser() {
    }

    /**
     * 根据MappedStatementId解析数据权限规则
     *
     * @param mappedStatementId MyBatis的MappedStatement ID
     * @return 数据权限规则，如果没有配置则返回null
     */
    public static DataScopeRule parse(String mappedStatementId) {
        // 先从缓存获取
        DataScopeRule cached = RULE_CACHE.get(mappedStatementId);
        if (cached != null) {
            // 返回null表示该方法没有配置注解
            return cached == EMPTY_MARKER ? null : cached;
        }

        // 解析注解
        DataScopeRule rule = doParse(mappedStatementId);

        // 缓存结果（使用EMPTY_MARKER标记未配置的情况）
        RULE_CACHE.put(mappedStatementId, rule != null ? rule : EMPTY_MARKER);

        return rule;
    }

    /**
     * 执行解析逻辑
     */
    private static DataScopeRule doParse(String mappedStatementId) {
        try {
            int lastDotIndex = mappedStatementId.lastIndexOf(".");
            if (lastDotIndex < 0) {
                return null;
            }

            String className = mappedStatementId.substring(0, lastDotIndex);
            String methodName = mappedStatementId.substring(lastDotIndex + 1);

            Class<?> clazz = Class.forName(className);

            // 优先查找方法级别注解
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(methodName)) {
                    DataScope methodScope = method.getAnnotation(DataScope.class);
                    if (methodScope != null) {
                        return buildRule(methodScope);
                    }
                }
            }

            // 其次查找类级别注解
            DataScope classScope = clazz.getAnnotation(DataScope.class);
            if (classScope != null) {
                return buildRule(classScope);
            }

            return null;
        } catch (ClassNotFoundException e) {
            log.warn("Failed to parse DataScope annotation for: {}", mappedStatementId, e);
            return null;
        }
    }

    /**
     * 从注解构建规则
     */
    private static DataScopeRule buildRule(DataScope scope) {
        if (scope.ignore()) {
            return DataScopeRule.ignored();
        }

        DataColumn[] columns = scope.columns();
        if (columns.length == 0) {
            // 没有配置字段，使用默认规则
            return DataScopeRule.defaults();
        }

        DataScopeRule.Builder builder = DataScopeRule.builder();
        Arrays.stream(columns).forEach(column ->
                builder.column(DataScopeRule.Column.builder()
                        .alias(column.alias())
                        .name(column.name())
                        .javaType(column.javaClass())
                        .resourceType(column.resource())
                        .scopeType(column.scopeType())
                        .build())
        );

        return builder.build();
    }

    /**
     * 清除缓存（用于测试或热更新场景）
     */
    public static void clearCache() {
        RULE_CACHE.clear();
    }

    /**
     * 获取缓存大小（用于监控）
     */
    public static int cacheSize() {
        return RULE_CACHE.size();
    }
}
