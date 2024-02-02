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

package com.wemirr.framework.commons.annotation.remote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static cn.hutool.core.text.CharSequenceUtil.EMPTY;

/**
 * <p>
 * 在某个对象的字段上标记该注解，该字段的值将被主动注入
 * </p>
 * 如：@Remote(bean = "userApiImpl")
 * private String userId;
 *
 * <p>
 * 强烈建议：不要对象之间互相依赖
 * 如： User 想要注入 File， File也想注入User
 * </p>
 *
 * @author Levin
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
public @interface Remote {

    /**
     * 提供自动注入值的 查询类
     * 注意： 用 @Remote(bean = "xxxServiceImpl")时，要保证当前服务有 xxxServiceImpl 类. 没这个类就要用 xxxApi  (FeignClient)
     *
     * @return 查询类的Spring Name
     */
    String beanName() default EMPTY;

    /**
     * @return 查询类的Spring Bean Class
     */
    Class<?> beanClass();

    /**
     * 规则
     *
     * @return 分隔符
     */
    String rule() default EMPTY;

    /**
     * 回显到那个字段
     *
     * @return 回显到那个字段
     */
    FieldRef[] fields() default {};

    /**
     * 自动注入值的类型， 用于强制转换（配置 ref 可以忽略）
     *
     * @return 待强转的类
     */
    Class<?> objectClass() default Object.class;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @interface FieldRef {

        String source() default EMPTY;

        String target();
    }

}
