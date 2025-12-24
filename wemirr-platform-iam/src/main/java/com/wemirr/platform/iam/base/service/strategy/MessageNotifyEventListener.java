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

package com.wemirr.platform.iam.base.service.strategy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.MapHelper;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import com.wemirr.platform.iam.base.domain.entity.MessageTemplate;
import com.wemirr.platform.iam.base.repository.MessageChannelMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageNotifyEventListener {
    
    private static final Map<String, MessageNotifyStrategy> NOTIFY_STRATEGY_MAP = Maps.newConcurrentMap();
    private final ThreadPoolExecutor poolExecutor;
    private final MessageChannelMapper messageChannelMapper;
    
//    @PostConstruct
    public void init() {
        Map<String, MessageNotifyStrategy> beansOfType = SpringUtil.getBeansOfType(MessageNotifyStrategy.class);
        for (Map.Entry<String, MessageNotifyStrategy> entry : beansOfType.entrySet()) {
            MessageNotifyStrategy notifyStrategy = entry.getValue();
            NOTIFY_STRATEGY_MAP.put(notifyStrategy.channelType(), notifyStrategy);
        }
    }
    
    /**
     * 消息处理
     */
    @Async
    @EventListener(classes = MessageNotifyEvent.class)
    public void handler(MessageNotifyEvent event) {
        MessageTemplate template = event.getTemplate();
        List<MessageNotify> list = event.getMessageNotifyList();
        List<MessageChannel> channels = this.messageChannelMapper.selectList(Wraps.<MessageChannel>lbQ()
                .in(MessageChannel::getType, StrUtil.split(template.getType(), ","))
                .eq(MessageChannel::getStatus, true));
        // 量大的推送任务应该异步进行
        Map<String, MessageChannel> channelMap = MapHelper.toHashMap(channels, MessageChannel::getType, x -> x);
        if (CollUtil.isEmpty(channelMap) || CollUtil.isEmpty(list)) {
            log.warn("channel is empty or notify list is empty ");
            return;
        }
        // 需要调整,用设计模式调整
        for (MessageNotify notify : list) {
            MessageChannel channel = channelMap.get(notify.getType());
            if (channel == null) {
                continue;
            }
            MessageNotifyStrategy strategy = NOTIFY_STRATEGY_MAP.get(channel.getType());
            if (strategy == null) {
                continue;
            }
            poolExecutor.execute(() -> strategy.handler(channel, notify));
        }
    }
}
