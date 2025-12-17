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

import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.commons.security.DataScopeType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限规则
 * <p>采用不可变设计，线程安全</p>
 *
 * @author Levin
 */
@Getter
public final class DataScopeRule {

    /**
     * 是否忽略数据权限
     */
    private final boolean ignored;

    /**
     * 规则字段列表
     */
    private final List<Column> columns;

    private DataScopeRule(boolean ignored, List<Column> columns) {
        this.ignored = ignored;
        this.columns = columns != null ? List.copyOf(columns) : List.of();
    }

    /**
     * 创建忽略数据权限的规则
     */
    public static DataScopeRule ignored() {
        return new DataScopeRule(true, List.of());
    }

    /**
     * 创建默认规则（使用created_by字段）
     */
    public static DataScopeRule defaults() {
        return builder().column(Column.defaults()).build();
    }

    /**
     * 创建构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 规则构建器
     */
    public static final class Builder {
        private boolean ignored = false;
        private final List<Column> columns = new ArrayList<>();

        private Builder() {
        }

        /**
         * 设置忽略数据权限
         */
        public Builder ignore() {
            this.ignored = true;
            return this;
        }

        /**
         * 添加字段规则
         */
        public Builder column(Column column) {
            this.columns.add(column);
            return this;
        }

        /**
         * 添加字段规则（使用字段名）
         */
        public Builder column(String name) {
            this.columns.add(Column.of(name));
            return this;
        }

        /**
         * 添加字段规则（使用别名和字段名）
         */
        public Builder column(String alias, String name) {
            this.columns.add(Column.of(alias, name));
            return this;
        }

        /**
         * 构建规则
         */
        public DataScopeRule build() {
            return new DataScopeRule(ignored, columns);
        }
    }

    /**
     * 字段规则
     */
    @Getter
    public static final class Column {

        /**
         * 表别名
         */
        private final String alias;

        /**
         * 字段名称
         */
        private final String name;

        /**
         * Java类型
         */
        private final Class<?> javaType;

        /**
         * 权限资源类型
         */
        private final DataResourceType resourceType;

        /**
         * 权限范围类型
         */
        private final DataScopeType scopeType;

        private Column(String alias, String name, Class<?> javaType,
                       DataResourceType resourceType, DataScopeType scopeType) {
            this.alias = alias;
            this.name = name;
            this.javaType = javaType;
            this.resourceType = resourceType;
            this.scopeType = scopeType;
        }

        /**
         * 创建默认字段规则
         */
        public static Column defaults() {
            return new Column("", Entity.CREATE_USER_COLUMN, Long.class,
                    DataResourceType.USER, DataScopeType.IGNORE);
        }

        /**
         * 使用字段名创建
         */
        public static Column of(String name) {
            return new Column("", name, Long.class, DataResourceType.USER, DataScopeType.IGNORE);
        }

        /**
         * 使用别名和字段名创建
         */
        public static Column of(String alias, String name) {
            return new Column(alias, name, Long.class, DataResourceType.USER, DataScopeType.IGNORE);
        }

        /**
         * 创建构建器
         */
        public static ColumnBuilder builder() {
            return new ColumnBuilder();
        }

        /**
         * 字段构建器
         */
        public static final class ColumnBuilder {
            private String alias = "";
            private String name = Entity.CREATE_USER_COLUMN;
            private Class<?> javaType = Long.class;
            private DataResourceType resourceType = DataResourceType.USER;
            private DataScopeType scopeType = DataScopeType.IGNORE;

            private ColumnBuilder() {
            }

            public ColumnBuilder alias(String alias) {
                this.alias = alias;
                return this;
            }

            public ColumnBuilder name(String name) {
                this.name = name;
                return this;
            }

            public ColumnBuilder javaType(Class<?> javaType) {
                this.javaType = javaType;
                return this;
            }

            public ColumnBuilder resourceType(DataResourceType resourceType) {
                this.resourceType = resourceType;
                return this;
            }

            public ColumnBuilder scopeType(DataScopeType scopeType) {
                this.scopeType = scopeType;
                return this;
            }

            public Column build() {
                return new Column(alias, name, javaType, resourceType, scopeType);
            }
        }
    }
}
