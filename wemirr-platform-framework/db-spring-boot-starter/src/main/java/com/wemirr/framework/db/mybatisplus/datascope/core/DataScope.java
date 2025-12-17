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

import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.commons.security.DataScopeType;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 数据权限核心API
 * <p>提供优雅的流式调用方式</p>
 *
 * <pre>{@code
 * // 1. 使用默认数据权限执行
 * List<User> users = DataScope.execute(() -> userMapper.selectList(null));
 *
 * // 2. 忽略数据权限执行
 * List<User> allUsers = DataScope.ignore(() -> userMapper.selectList(null));
 *
 * // 3. 使用自定义规则执行
 * List<Order> orders = DataScope.with(rule -> rule.column("org_id"))
 *     .execute(() -> orderMapper.selectList(null));
 *
 * // 4. 复杂规则配置
 * DataScope.with(rule -> rule
 *         .column(Column.builder()
 *             .alias("t")
 *             .name("department_id")
 *             .resourceType(DataResourceType.ORG)
 *             .build()))
 *     .execute(() -> mapper.selectList(null));
 * }</pre>
 *
 * @author Levin
 */
@Slf4j
public final class DataScope {

    private final DataScopeRule rule;

    private DataScope(DataScopeRule rule) {
        this.rule = rule;
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 使用默认数据权限规则执行
     * <p>默认使用 created_by 字段进行数据过滤</p>
     *
     * @param supplier 业务逻辑
     * @param <T>      返回类型
     * @return 执行结果
     */
    public static <T> T run(Supplier<T> supplier) {
        return new DataScope(DataScopeRule.defaults()).execute(supplier);
    }

    /**
     * 使用默认数据权限规则执行（无返回值）
     *
     * @param runnable 业务逻辑
     */
    public static void run(Runnable runnable) {
        new DataScope(DataScopeRule.defaults()).execute(runnable);
    }

    /**
     * 忽略数据权限执行
     *
     * @param supplier 业务逻辑
     * @param <T>      返回类型
     * @return 执行结果
     */
    public static <T> T skip(Supplier<T> supplier) {
        return new DataScope(DataScopeRule.ignored()).execute(supplier);
    }

    /**
     * 忽略数据权限执行（无返回值）
     *
     * @param runnable 业务逻辑
     */
    public static void skip(Runnable runnable) {
        new DataScope(DataScopeRule.ignored()).execute(runnable);
    }

    /**
     * 使用指定规则创建DataScope实例
     *
     * @param rule 数据权限规则
     * @return DataScope实例
     */
    public static DataScope with(DataScopeRule rule) {
        return new DataScope(rule);
    }

    /**
     * 使用规则构建器创建DataScope实例
     *
     * @param configurator 规则配置器
     * @return DataScope实例
     */
    public static DataScope with(RuleConfigurator configurator) {
        DataScopeRule.Builder builder = DataScopeRule.builder();
        configurator.configure(builder);
        return new DataScope(builder.build());
    }

    // ==================== 快捷方法 ====================

    /**
     * 快速创建单字段规则
     *
     * @param columnName 字段名
     * @return DataScope实例
     */
    public static DataScope column(String columnName) {
        return with(rule -> rule.column(columnName));
    }

    /**
     * 快速创建带别名的单字段规则
     *
     * @param alias      表别名
     * @param columnName 字段名
     * @return DataScope实例
     */
    public static DataScope column(String alias, String columnName) {
        return with(rule -> rule.column(alias, columnName));
    }

    /**
     * 快速创建组织机构字段规则
     *
     * @param columnName 字段名（默认org_id）
     * @return DataScope实例
     */
    public static DataScope org(String columnName) {
        return with(rule -> rule.column(
                DataScopeRule.Column.builder()
                        .name(columnName)
                        .resourceType(DataResourceType.ORG)
                        .build()
        ));
    }

    /**
     * 快速创建组织机构字段规则（使用默认字段名org_id）
     *
     * @return DataScope实例
     */
    public static DataScope org() {
        return org("org_id");
    }

    /**
     * 创建自身数据规则（只看自己的数据）
     *
     * @param columnName 用户ID字段名
     * @return DataScope实例
     */
    public static DataScope self(String columnName) {
        return with(rule -> rule.column(
                DataScopeRule.Column.builder()
                        .name(columnName)
                        .scopeType(DataScopeType.SELF)
                        .build()
        ));
    }

    /**
     * 创建自身数据规则（使用默认字段名created_by）
     *
     * @return DataScope实例
     */
    public static DataScope self() {
        return self("created_by");
    }

    // ==================== 执行方法 ====================

    /**
     * 执行业务逻辑
     *
     * @param supplier 业务逻辑
     * @param <T>      返回类型
     * @return 执行结果
     */
    public <T> T execute(Supplier<T> supplier) {
        DataScopeContext.push(rule);
        try {
            return supplier.get();
        } finally {
            DataScopeContext.pop();
        }
    }

    /**
     * 执行业务逻辑（无返回值）
     *
     * @param runnable 业务逻辑
     */
    public void execute(Runnable runnable) {
        DataScopeContext.push(rule);
        try {
            runnable.run();
        } finally {
            DataScopeContext.pop();
        }
    }

    // ==================== 函数式接口 ====================

    /**
     * 规则配置器
     */
    @FunctionalInterface
    public interface RuleConfigurator {
        /**
         * 配置规则
         *
         * @param builder 规则构建器
         */
        void configure(DataScopeRule.Builder builder);
    }
}
