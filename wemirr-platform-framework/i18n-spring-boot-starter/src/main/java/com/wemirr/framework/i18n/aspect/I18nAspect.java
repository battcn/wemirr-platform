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

package com.wemirr.framework.i18n.aspect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.i18n.Language;
import com.wemirr.framework.i18n.annotation.I18nField;
import com.wemirr.framework.i18n.core.I18nMessageResource;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 国际化字段自动翻译切面
 * <p>
 * 拦截标记了 {@link com.wemirr.framework.i18n.annotation.I18nMethod} 注解的方法，
 * 自动将返回对象中标记了 {@link I18nField} 的字段进行国际化翻译
 *
 * @author Levin
 */
@Aspect
@RequiredArgsConstructor
public class I18nAspect {

    /**
     * 字段缓存，避免重复反射获取字段
     * key: Class, value: 标记了 @I18nField 的字段列表
     */
    private static final Map<Class<?>, List<FieldMeta>> FIELD_CACHE = new ConcurrentHashMap<>();

    private final I18nMessageResource messageSource;

    /***
     * 定义controller切入点拦截规则：拦截标记AccessLog注解和指定包下的方法
     * execution(public * com.wemirr.base.controller.*.*(..)) 解释：
     * 第一个* 任意返回类型
     * 第三个* 类下的所有方法
     * ()中间的.. 任意参数
     * annotation(com.wemirr.framework.commons.annotation.log.AccessLog) 解释：
     * 标记了@AccessLog 注解的方法
     */
    @Pointcut("execution(public * com.wemirr..*.*(..)) && @annotation(com.wemirr.framework.i18n.annotation.I18nMethod)")
    public void i18nAspect() {

    }

    /**
     * 返回通知
     *
     * @param obj       obj
     * @param joinPoint joinPoint
     */
    @AfterReturning(returning = "obj", pointcut = "i18nAspect()")
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        parse(obj);
    }

    private void parseList(Collection<?> list) {
        list.forEach(this::parse);
    }

    /**
     * 遍历字段，解析标记了 @I18nField 的字段并进行国际化翻译
     *
     * @param obj 待处理对象
     */
    private void parse(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Collection<?> list) {
            parseList(list);
            return;
        }
        // 从缓存获取字段元数据，避免重复反射
        List<FieldMeta> fieldMetas = FIELD_CACHE.computeIfAbsent(obj.getClass(), this::extractI18nFields);
        for (FieldMeta meta : fieldMetas) {
            Object fieldValue = ReflectUtil.getFieldValue(obj, meta.field);
            if (fieldValue == null) {
                continue;
            }
            if (fieldValue instanceof Language item) {
                ReflectUtil.setFieldValue(obj, meta.targetField, messageSource.getMessage(item.getLanguage(), fieldValue));
            } else if (StrUtil.isNotBlank(meta.targetField)) {
                ReflectUtil.setFieldValue(obj, meta.targetField, messageSource.getMessage(meta.code, fieldValue));
            } else {
                ReflectUtil.setFieldValue(obj, meta.field, messageSource.getMessage(fieldValue.toString()));
            }
        }
    }

    /**
     * 提取类中标记了 @I18nField 的字段元数据
     */
    private List<FieldMeta> extractI18nFields(Class<?> clazz) {
        Field[] fields = ReflectUtil.getFields(clazz);
        return java.util.Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(I18nField.class))
                .map(f -> {
                    I18nField anno = f.getAnnotation(I18nField.class);
                    return new FieldMeta(f, anno.code(), anno.target());
                })
                .toList();
    }

    /**
     * 字段元数据，缓存注解信息避免重复解析
     */
    private record FieldMeta(Field field, String code, String targetField) {}
}
