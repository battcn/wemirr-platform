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

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Optional;

/**
 * MyBatis Plus 元数据自动填充处理器（JDK21 Record）
 * <p>自动填充实体类中的审计字段，包括创建人、创建时间、修改人、修改时间等</p>
 *
 * <h3>自动填充字段</h3>
 * <ul>
 *   <li><b>INSERT时</b>：tenantId、createBy、createName、createTime、deleted</li>
 *   <li><b>UPDATE时</b>：lastModifyBy、lastModifyName、lastModifyTime</li>
 * </ul>
 *
 * <h3>注意事项</h3>
 * <ul>
 *   <li>不支持复合主键自动注入</li>
 *   <li>匿名接口（未登录）时跳过自动填充</li>
 *   <li>字段类型自动适配（支持Long/String类型的ID字段）</li>
 * </ul>
 *
 * @param context 认证上下文，用于获取当前登录用户信息
 * @author Levin
 * @since 1.0.0
 * @see Entity
 * @see SuperEntity
 */
@Slf4j
public record MyBatisMetaObjectHandler(AuthenticationContext context) implements MetaObjectHandler {

    /**
     * 注意：不支持 复合主键 自动注入！！
     * <p>
     * 所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： id为空时， 通过IdGenerate生成唯一ID， 不为空则使用传递进来的id
     * createBy, lastModifiedBy: 自动赋予 当前线程上的登录人id
     * createTime, lastModifiedTime: 自动赋予 服务器的当前时间
     * <p>
     * 未继承任何父类的实体，且主键标注了 @TableId(value = "xxx", type = IdType.INPUT) 自动注入 主键
     * 主键的字段名称任意
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (context.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作...");
            return;
        }
        Object object = metaObject.getOriginalObject();
        if (metaObject.hasGetter(SuperEntity.DELETED)) {
            final Object deleted = Optional.ofNullable(metaObject.getValue(SuperEntity.DELETED)).orElse(Boolean.FALSE);
            this.setFieldValByName(SuperEntity.DELETED, deleted, metaObject);
        }
        if (metaObject.hasGetter(Entity.TENANT_ID)) {
            final Object tenantId = Optional.ofNullable(metaObject.getValue(Entity.TENANT_ID)).orElse(context.tenantId());
            if (isStrField(object, Entity.TENANT_ID)) {
                this.setFieldValByName(Entity.TENANT_ID, String.valueOf(tenantId), metaObject);
            } else {
                this.setFieldValByName(Entity.TENANT_ID, tenantId, metaObject);
            }
        }
        if (metaObject.hasGetter(Entity.CREATE_TIME)) {
            final Object createTime = Optional.ofNullable(metaObject.getValue(Entity.CREATE_TIME)).orElseGet(Instant::now);
            this.setFieldValByName(Entity.CREATE_TIME, createTime, metaObject);
        }
        if (metaObject.hasGetter(Entity.CREATE_USER)) {
            final Object userId = Optional.ofNullable(metaObject.getValue(Entity.CREATE_USER)).orElse(context.userId());
            // 如果是字符串或者不存在字段,统一设置成字符串 防止报错
            if (isStrField(object, Entity.CREATE_USER) || !ReflectUtil.hasField(object.getClass(), Entity.CREATE_USER)) {
                this.setFieldValByName(Entity.CREATE_USER, String.valueOf(userId), metaObject);
            } else {
                this.setFieldValByName(Entity.CREATE_USER, userId, metaObject);
            }
            this.setFieldValByName(Entity.CREATE_USER_NAME, context.nickName(), metaObject);
        }
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
        if (context.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        Object object = metaObject.getOriginalObject();
        // 如果要自己设置服务器时间就自己赋值,否则建议使用数据库的默认时间 DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
        if (metaObject.hasGetter(SuperEntity.UPDATE_TIME)) {
            final Object updateTime = Optional.ofNullable(metaObject.getValue(SuperEntity.UPDATE_TIME)).orElseGet(Instant::now);
            this.setFieldValByName(SuperEntity.UPDATE_TIME, updateTime, metaObject);
        }
        if (metaObject.hasGetter(SuperEntity.UPDATE_USER)) {
            this.setFieldValByName(SuperEntity.UPDATE_USER, context.userId(), metaObject);

            final Object userId = Optional.ofNullable(metaObject.getValue(SuperEntity.UPDATE_USER)).orElse(context.userId());
            if (isStrField(object, SuperEntity.UPDATE_USER)) {
                this.setFieldValByName(SuperEntity.UPDATE_USER, String.valueOf(userId), metaObject);
            } else {
                this.setFieldValByName(SuperEntity.UPDATE_USER, userId, metaObject);
            }
            this.setFieldValByName(SuperEntity.UPDATE_USER_NAME, context.nickName(), metaObject);
        }
    }

    /**
     * 判断字段是否是Long类型
     */
    public static boolean isStrField(Object obj, String fieldName) {
        Field field = ReflectUtil.getField(obj.getClass(), fieldName);
        if (field == null) {
            return false;
        }
        Class<?> fieldType = field.getType();
        return fieldType.equals(String.class);
    }

}
