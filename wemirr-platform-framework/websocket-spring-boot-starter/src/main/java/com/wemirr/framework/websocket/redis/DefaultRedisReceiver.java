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
package com.wemirr.framework.websocket.redis;

import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.redis.action.Action;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * redis消息订阅者
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultRedisReceiver implements RedisReceiver {
    
    private final ApplicationContext applicationContext;
    
    /**
     * 此方法会被反射调用
     */
    @Override
    public void receiveMessage(String message) {
        log.info(message);
        JSONObject object = JSONObject.parseObject(message);
        if (!object.containsKey(Action.ACTION)) {
            return;
        }
        String actionName = object.getString(Action.ACTION);
        Action action = getAction(actionName);
        action.doMessage(getWebSocketManager(), object);
    }
    
    protected Action getAction(String actionName) {
        return applicationContext.getBean(actionName, Action.class);
    }
    
    protected WebSocketManager getWebSocketManager() {
        return applicationContext.getBean(WebSocketManager.WEBSOCKET_MANAGER_NAME, WebSocketManager.class);
    }
}