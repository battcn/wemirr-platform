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

/**
 * 数据权限常量定义
 *
 * @author Levin
 */
public final class DataScopeConstants {

    private DataScopeConstants() {
    }

    /**
     * IN 列表阈值，超过此值考虑使用 EXISTS 子查询或分批处理
     */
    public static final int IN_THRESHOLD = 1000;

    /**
     * 数据权限关联表相关常量
     */
    public static final class RefTable {
        private RefTable() {
        }

        /**
         * 表名
         */
        public static final String TABLE_NAME = "sys_data_permission_ref";

        /**
         * 表别名
         */
        public static final String ALIAS = "ref";

        /**
         * 字段：所有者ID
         */
        public static final String COLUMN_OWNER_ID = "owner_id";

        /**
         * 字段：所有者类型
         */
        public static final String COLUMN_OWNER_TYPE = "owner_type";

        /**
         * 字段：数据类型
         */
        public static final String COLUMN_DATA_TYPE = "data_type";

        /**
         * 字段：数据ID
         */
        public static final String COLUMN_DATA_ID = "data_id";

        /**
         * 带别名的字段：所有者ID
         */
        public static final String ALIASED_OWNER_ID = ALIAS + "." + COLUMN_OWNER_ID;

        /**
         * 带别名的字段：所有者类型
         */
        public static final String ALIASED_OWNER_TYPE = ALIAS + "." + COLUMN_OWNER_TYPE;

        /**
         * 带别名的字段：数据类型
         */
        public static final String ALIASED_DATA_TYPE = ALIAS + "." + COLUMN_DATA_TYPE;

        /**
         * 带别名的字段：数据ID
         */
        public static final String ALIASED_DATA_ID = ALIAS + "." + COLUMN_DATA_ID;
    }

    /**
     * 数据权限模式
     */
    public enum Mode {
        /**
         * 同库模式：使用 EXISTS 子查询（性能好，无溢出风险）
         * 适用于：单体应用、同库微服务
         */
        SAME_DATABASE,

        /**
         * 分库模式：使用 IN 列表（需要分批处理避免溢出）
         * 适用于：分库分表、跨库微服务
         */
        SEPARATE_DATABASE
    }
}
