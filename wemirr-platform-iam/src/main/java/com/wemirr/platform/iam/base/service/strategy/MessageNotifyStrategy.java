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

import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Levin
 * 消息推送策略
 */
public interface MessageNotifyStrategy {
    
    Logger log = LoggerFactory.getLogger(MessageNotifyStrategy.class);
    
    String channelType();
    
    /**
     * 消息处理
     *
     * @param channel 通知渠道
     * @param notify  通知内容
     */
    
    void handler(MessageChannel channel, MessageNotify notify);
    
}
