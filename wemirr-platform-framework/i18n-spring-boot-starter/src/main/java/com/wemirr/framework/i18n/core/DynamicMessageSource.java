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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.TypeUtil;
import com.wemirr.framework.i18n.I18nMessageProvider;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.framework.i18n.domain.I18nRedisKeyConstants;
import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class DynamicMessageSource extends StaticMessageSource implements InitializingBean, AbstractMessageEventListener<I18nMessage> {

    private final I18nMessageProvider i18nMessageProvider;

    @Override
    public void afterPropertiesSet() {
        List<I18nMessage> list = i18nMessageProvider.list();
        if (CollUtil.isEmpty(list)) {
            return;
        }
        for (I18nMessage message : list) {
            addMessage(message.getCode(), LocaleUtils.toLocale(message.getLocale()), message.getMessage());
        }
    }

    @Override
    public void handleMessage(I18nMessage message) {
        if (message == null) {
            return;
        }
        addMessage(message.getCode(), LocaleUtils.toLocale(message.getLocale()), message.getMessage());
    }

    @Override
    public Topic topic() {
        return new ChannelTopic(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED);
    }

    @Override
    public Type type() {
        return TypeUtil.getTypeArgument(I18nMessage.class);
    }
}