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
package com.wemirr.framework.db.mybatisplus.datascope.annotation;

import java.lang.annotation.*;

/**
 * 数据权限注解，注解在 Mapper类 或者 对应方法上 用于提供该 mapper 对应表，所需控制的实体信息
 *
 * @author Levin
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    
    /**
     * 当前类或方法是否忽略数据权限
     *
     * @return boolean 默认返回 false
     */
    boolean ignore() default false;
    
    /**
     * 数据权限字段，支持多字段组合
     * 指定  @ScopeColumn(alias = "u", name = "department_id")
     */
    DataColumn[] columns() default {};
    
}
