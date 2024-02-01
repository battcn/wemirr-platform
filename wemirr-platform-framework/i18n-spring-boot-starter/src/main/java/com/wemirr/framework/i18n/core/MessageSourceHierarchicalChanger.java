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
package com.wemirr.framework.i18n.core;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * 解决静态资源文件 + 动态资源不兼容问题
 *
 * @author Levin
 */
public class MessageSourceHierarchicalChanger {
    
    @Resource(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    private MessageSource messageSource;
    
    @Resource
    private DynamicMessageSource dynamicMessageSource;
    
    @PostConstruct
    public void changeMessageSourceParent() {
        // 优先走 messageSource，从资源文件中查找
        if (messageSource instanceof HierarchicalMessageSource hierarchicalMessageSource) {
            MessageSource parentMessageSource = hierarchicalMessageSource.getParentMessageSource();
            dynamicMessageSource.setParentMessageSource(parentMessageSource);
            hierarchicalMessageSource.setParentMessageSource(dynamicMessageSource);
        } else {
            dynamicMessageSource.setParentMessageSource(messageSource);
        }
    }
    
}
