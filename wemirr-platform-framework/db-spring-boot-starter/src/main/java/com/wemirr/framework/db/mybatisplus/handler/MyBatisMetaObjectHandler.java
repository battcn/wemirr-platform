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

package com.wemirr.framework.db.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.Instant;
import java.util.Optional;

/**
 * MyBatis Plus 元数据处理类
 * 用于自动 注入 id, createdTime, lastModifiedTime, createdBy, lastModifiedBy 等字段
 *
 * @author Levin
 * @since 2019/04/29
 */
@Slf4j
@RequiredArgsConstructor
public class MyBatisMetaObjectHandler implements MetaObjectHandler {
    
    private final AuthenticationContext context;
    
    /**
     * 注意：不支持 复合主键 自动注入！！
     * <p>
     * 所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： id为空时， 通过IdGenerate生成唯一ID， 不为空则使用传递进来的id
     * createdBy, lastModifiedBy: 自动赋予 当前线程上的登录人id
     * createdTime, lastModifiedTime: 自动赋予 服务器的当前时间
     * <p>
     * 未继承任何父类的实体，且主键标注了 @TableId(value = "xxx", type = IdType.INPUT) 自动注入 主键
     * 主键的字段名称任意
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 如果要自己设置服务器时间就自己赋值,否则建议使用数据库的默认时间 DEFAULT CURRENT_TIMESTAMP
        final Object createTime = Optional.ofNullable(metaObject.getValue(Entity.CREATE_TIME)).orElseGet(Instant::now);
        this.setFieldValByName(Entity.CREATE_TIME, createTime, metaObject);
        if (context.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        this.setFieldValByName(Entity.CREATE_USER, context.userId(), metaObject);
        this.setFieldValByName(Entity.CREATE_USER_NAME, context.realName(), metaObject);
    }
    
    /**
     * 所有的继承了Entity、SuperEntity的实体，在update时，
     * lastModifiedBy: 自动赋予 当前线程上的登录人id
     * lastModifiedTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 如果要自己设置服务器时间就自己赋值,否则建议使用数据库的默认时间 DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
        final Object updateTime = Optional.ofNullable(metaObject.getValue(SuperEntity.UPDATE_TIME)).orElseGet(Instant::now);
        this.setFieldValByName(SuperEntity.UPDATE_TIME, updateTime, metaObject);
        if (context.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        this.setFieldValByName(SuperEntity.UPDATE_USER, context.userId(), metaObject);
        this.setFieldValByName(SuperEntity.UPDATE_USER_NAME, context.realName(), metaObject);
    }
}
