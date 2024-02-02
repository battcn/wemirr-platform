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

package com.wemirr.framework.boot.remote;

import com.wemirr.framework.commons.annotation.remote.Remote;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author Levin
 */
@Getter
@ToString
@EqualsAndHashCode
public class LoadKey {
    
    /**
     * 执行查询任务的类
     */
    private final String beanName;
    /**
     * bean 类型
     */
    private final Class<?> beanClass;
    
    public LoadKey(Remote rf) {
        this.beanName = rf.beanName();
        this.beanClass = rf.beanClass();
    }
}
