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

package com.wemirr.framework.i18n;

import com.wemirr.framework.i18n.domain.I18nMessage;

import java.util.List;
import java.util.Locale;

/**
 * @author Levin
 */
public interface I18nMessageProvider {


    /**
     * 获取消息
     *
     * @param code   code
     * @param locale 语言
     * @return 国际化消息
     */
    String getI18nMessage(String code, Locale locale);

    /**
     * 加载全部的 i18n 数据
     *
     * @return 国际化消息
     */
    List<I18nMessage> list();


    /**
     * 加载消息到存储列表
     *
     * @param messages 消息
     */
    void loadI18nMessage(List<I18nMessage> messages);

}
