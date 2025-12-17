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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.commons.security.DataPermission;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.commons.security.DataScopeType;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ParenthesedExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 数据权限SQL条件构建器
 *
 * @author Levin
 */
@Slf4j
public final class DataScopeSqlBuilder {

    private DataScopeSqlBuilder() {
    }

    /**
     * 构建数据权限SQL条件表达式
     *
     * @param context 认证上下文
     * @param table   SQL表
     * @param rule    数据权限规则
     * @return SQL条件表达式，返回null表示不需要过滤
     */
    public static Expression build(AuthenticationContext context, Table table, DataScopeRule rule) {
        if (rule == null || rule.isIgnored()) {
            return null;
        }

        DataPermission permission = context.dataPermission();
        if (permission == null || permission.getScopeType() == DataScopeType.ALL) {
            return null;
        }

        List<DataScopeRule.Column> columns = rule.getColumns();
        if (CollUtil.isEmpty(columns)) {
            return null;
        }

        List<Expression> conditions = buildConditions(context, table, columns, permission);
        if (CollUtil.isEmpty(conditions)) {
            return null;
        }

        // 使用AND连接所有条件
        return conditions.stream().reduce(AndExpression::new).orElse(null);
    }

    /**
     * 构建条件列表
     */
    private static List<Expression> buildConditions(AuthenticationContext context, Table table,
                                                    List<DataScopeRule.Column> columns,
                                                    DataPermission permission) {
        List<Expression> conditions = new ArrayList<>();
        Map<DataResourceType, List<Object>> permissionMap = permission.getDataPermissionMap();

        for (DataScopeRule.Column column : columns) {
            // 确定最终的权限范围类型
            DataScopeType scopeType = determineScopeType(column.getScopeType(), permission.getScopeType());
            if (scopeType == DataScopeType.ALL) {
                continue;
            }

            // 检查表别名匹配
            if (!matchTableAlias(table, column.getAlias())) {
                continue;
            }

            // 构建SQL列
            Column sqlColumn = buildSqlColumn(table, column.getName());

            // 根据权限类型构建条件
            Expression condition = buildConditionByType(context, sqlColumn, column, scopeType, permissionMap);
            if (condition != null) {
                conditions.add(condition);
            }
        }

        return conditions;
    }

    /**
     * 确定最终的权限范围类型
     */
    private static DataScopeType determineScopeType(DataScopeType columnType, DataScopeType permissionType) {
        // 如果字段配置了IGNORE，则跟随用户权限配置
        return columnType == DataScopeType.IGNORE ? permissionType : columnType;
    }

    /**
     * 检查表别名是否匹配
     */
    private static boolean matchTableAlias(Table table, String columnAlias) {
        if (StrUtil.isBlank(columnAlias)) {
            return true;
        }
        if (table.getAlias() == null) {
            return true;
        }
        return StrUtil.equals(table.getAlias().getName(), columnAlias);
    }

    /**
     * 构建SQL列对象
     */
    private static Column buildSqlColumn(Table table, String columnName) {
        String tableName = table.getName();
        if (table.getAlias() != null) {
            tableName = table.getAlias().getName();
        }
        return new Column(tableName + StringPool.DOT + columnName);
    }

    /**
     * 根据权限类型构建条件
     */
    private static Expression buildConditionByType(AuthenticationContext context, Column sqlColumn,
                                                   DataScopeRule.Column column, DataScopeType scopeType,
                                                   Map<DataResourceType, List<Object>> permissionMap) {
        // 个人权限使用等于条件，性能更好
        if (scopeType == DataScopeType.SELF) {
            return new EqualsTo(sqlColumn, new LongValue(context.userId()));
        }

        // 其他类型使用IN条件
        if (CollUtil.isEmpty(permissionMap)) {
            log.debug("Data permission map is empty, skip building condition");
            return null;
        }

        List<?> values = permissionMap.get(column.getResourceType());
        if (CollUtil.isEmpty(values)) {
            return null;
        }

        return new InExpression(sqlColumn, buildValuesList(values, column.getJavaType()));
    }

    /**
     * 构建IN条件的值列表
     */
    private static Expression buildValuesList(List<?> values, Class<?> javaType) {
        List<Expression> expressions = values.stream()
                .filter(Objects::nonNull)
                .map(value -> convertToExpression(value, javaType))
                .toList();

        return new ParenthesedExpressionList<>(expressions);
    }

    /**
     * 将值转换为SQL表达式
     */
    private static Expression convertToExpression(Object value, Class<?> javaType) {
        if (javaType == Integer.class || javaType == Long.class || Number.class.isAssignableFrom(javaType)) {
            return new LongValue(value.toString());
        }
        return new StringValue(value.toString());
    }
}
