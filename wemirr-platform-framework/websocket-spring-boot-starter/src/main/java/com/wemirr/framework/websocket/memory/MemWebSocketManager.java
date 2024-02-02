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

package com.wemirr.framework.websocket.memory;

import com.wemirr.framework.websocket.WebSocket;
import com.wemirr.framework.websocket.WebSocketCloseEvent;
import com.wemirr.framework.websocket.WebSocketConnectEvent;
import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.utils.WebSocketUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Levin
 */
public class MemWebSocketManager implements WebSocketManager, ApplicationContextAware {
    
    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * 因为全局只有一个 WebSocketManager ，所以才敢定义为非static
     */
    private final Map<String, WebSocket> connections = new ConcurrentHashMap<>(100);
    
    @Override
    public WebSocket get(String identifier) {
        return connections.get(identifier);
    }
    
    @Override
    public void put(String identifier, WebSocket webSocket) {
        connections.put(identifier, webSocket);
        // 发送连接事件
        getApplicationContext().publishEvent(new WebSocketConnectEvent(webSocket));
    }
    
    @Override
    public void remove(String identifier) {
        WebSocket removedWebSocket = connections.remove(identifier);
        // 发送关闭事件
        if (null != removedWebSocket) {
            getApplicationContext().publishEvent(new WebSocketCloseEvent(removedWebSocket));
        }
    }
    
    @Override
    public Map<String, WebSocket> localWebSocketMap() {
        return connections;
    }
    
    @Override
    public void sendMessage(String identifier, String message) {
        WebSocket webSocket = get(identifier);
        if (null == webSocket) {
            throw new RuntimeException("identifier 不存在");
        }
        WebSocketUtil.sendMessage(webSocket.getSession(), message);
    }
    
    @Override
    public void broadcast(String message) {
        localWebSocketMap().values().forEach(
                webSocket -> WebSocketUtil.sendMessage(
                        webSocket.getSession(), message));
    }
    
    @Override
    public void onMessage(String identifier, String message) {
        
    }
}
