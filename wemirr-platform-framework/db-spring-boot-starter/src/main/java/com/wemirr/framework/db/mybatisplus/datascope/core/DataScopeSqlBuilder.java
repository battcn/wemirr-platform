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
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ParenthesedExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.ParenthesedSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 数据权限SQL条件构建器
 * <p>
 * 支持两种模式：
 * - 同库模式（SAME_DATABASE）：使用 EXISTS 子查询，性能好，无溢出风险
 * - 分库模式（SEPARATE_DATABASE）：使用 IN 列表，适用于微服务/分库架构
 * </p>
 *
 * @author Levin
 */
@Slf4j
public final class DataScopeSqlBuilder {

    /**
     * 数据权限模式（通过配置注入，默认分库模式）
     */
    private static DataScopeConstants.Mode mode = DataScopeConstants.Mode.SEPARATE_DATABASE;

    /**
     * IN 列表阈值
     */
    private static int inThreshold = DataScopeConstants.IN_THRESHOLD;

    private DataScopeSqlBuilder() {
    }

    /**
     * 设置数据权限模式（由配置类调用）
     */
    public static void setMode(DataScopeConstants.Mode mode) {
        DataScopeSqlBuilder.mode = mode;
    }

    /**
     * 设置 IN 列表阈值（由配置类调用）
     */
    public static void setInThreshold(int threshold) {
        DataScopeSqlBuilder.inThreshold = threshold;
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

        // 其他类型使用IN条件或EXISTS子查询
        if (CollUtil.isEmpty(permissionMap)) {
            log.debug("Data permission map is empty, skip building condition");
            return null;
        }

        List<?> values = permissionMap.get(column.getResourceType());
        if (CollUtil.isEmpty(values)) {
            return null;
        }

        // 根据模式和阈值选择策略
        if (mode == DataScopeConstants.Mode.SAME_DATABASE && values.size() >= inThreshold) {
            // 同库模式且超过阈值：使用 EXISTS 子查询
            return buildExistsExpression(context, sqlColumn, column.getResourceType());
        }
        // 分库模式或数据量较小：使用 IN 列表
        return new InExpression(sqlColumn, buildValuesList(values, column.getJavaType()));
    }

    /**
     * 构建 EXISTS 子查询表达式
     * <p>
     * 生成的 SQL 类似：
     * EXISTS (SELECT 1 FROM sys_data_permission_ref ref 
     *         WHERE ref.data_id = t.created_by 
     *         AND ref.owner_id IN (roleIds) 
     *         AND ref.owner_type = 'ROLE' 
     *         AND ref.data_type = 'USER')
     * </p>
     */
    private static Expression buildExistsExpression(AuthenticationContext context, Column sqlColumn,
                                                    DataResourceType resourceType) {
        DataPermission permission = context.dataPermission();
        List<Long> roleIds = permission.getRoleIds();
        if (CollUtil.isEmpty(roleIds)) {
            log.warn("roleIds is empty, cannot build EXISTS query, fallback to empty result");
            // 1=0 返回空结果
            return new EqualsTo(new LongValue(1), new LongValue(0));
        }

        // 构建子查询: SELECT 1 FROM sys_data_permission_ref ref WHERE ...
        PlainSelect subSelect = new PlainSelect();
        subSelect.setSelectItems(List.of(SelectItem.from(new LongValue(1))));

        Table refTable = new Table(DataScopeConstants.RefTable.TABLE_NAME);
        refTable.setAlias(new net.sf.jsqlparser.expression.Alias(DataScopeConstants.RefTable.ALIAS));
        subSelect.setFromItem(refTable);

        // WHERE 条件
        List<Expression> conditions = new ArrayList<>();

        // ref.data_id = t.column
        conditions.add(new EqualsTo(new Column(DataScopeConstants.RefTable.ALIASED_DATA_ID), sqlColumn));

        // ref.owner_id IN (roleIds)
        if (roleIds.size() == 1) {
            conditions.add(new EqualsTo(new Column(DataScopeConstants.RefTable.ALIASED_OWNER_ID), new LongValue(roleIds.getFirst())));
        } else {
            List<Expression> roleIdExpressions = roleIds.stream()
                    .map(id -> (Expression) new LongValue(id))
                    .toList();
            conditions.add(new InExpression(new Column(DataScopeConstants.RefTable.ALIASED_OWNER_ID), new ParenthesedExpressionList<>(roleIdExpressions)));
        }

        // ref.owner_type = 'ROLE'
        conditions.add(new EqualsTo(new Column(DataScopeConstants.RefTable.ALIASED_OWNER_TYPE), new StringValue(DataResourceType.ROLE.getResource())));

        // ref.data_type = resourceType
        conditions.add(new EqualsTo(new Column(DataScopeConstants.RefTable.ALIASED_DATA_TYPE), new StringValue(resourceType.getResource())));

        // 合并所有条件
        Expression whereCondition = conditions.stream().reduce(AndExpression::new).orElse(null);
        subSelect.setWhere(whereCondition);

        // 使用 ParenthesedSelect 包装子查询
        ParenthesedSelect parenthesedSelect = new ParenthesedSelect();
        parenthesedSelect.setSelect(subSelect);

        ExistsExpression existsExpression = new ExistsExpression();
        existsExpression.setRightExpression(parenthesedSelect);
        return existsExpression;
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
