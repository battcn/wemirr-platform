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

package com.wemirr.framework.websocket;

import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.websocket.utils.WebSocketUtil;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * NOTE: Nginx反向代理要支持WebSocket，需要配置几个header，否则连接的时候就报404
 * proxy_http_version 1.1;
 * proxy_set_header Upgrade $http_upgrade;
 * proxy_set_header Connection "upgrade";
 * proxy_read_timeout 3600s; //这个时间不长的话就容易断开连接
 * <p>
 * 写自己的Endpoint类，继承自此类，添加@ServerEndpoint、@Component注解，
 * 然后在方法中添加@OnOpen、@OnMessage、@OnClose、@OnError即可，这些方法中可以调用父类方法
 *
 * @author Levin
 */
@Slf4j
public abstract class BaseWebSocketEndpoint {
    
    /**
     * 路径标识：目前使用token来代表
     */
    public static final String IDENTIFIER = "identifier";
    
    public void connect(String identifier, Session session) {
        try {
            if (null == identifier || identifier.isEmpty()) {
                return;
            }
            WebSocketManager websocketManager = getWebSocketManager();
            WebSocket webSocket = new WebSocket();
            webSocket.setIdentifier(identifier);
            webSocket.setSession(session);
            webSocket.setLastHeart(new Date());
            // 像刷新这种，id一样，session不一样，后面的覆盖前面的
            websocketManager.put(identifier, webSocket);
            log.info("sessionId {} 身份标识 {} 连接成功", session.getId(), identifier);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public void disconnect(String identifier) {
        getWebSocketManager().remove(identifier);
        log.info("session close");
    }
    
    public void senderMessage(String identifier, String message) {
        WebSocketManager webSocketManager = getWebSocketManager();
        webSocketManager.sendMessage(identifier, message);
    }
    
    public void receiveMessage(String identifier, String message, Session session) {
        WebSocketManager webSocketManager = getWebSocketManager();
        // 心跳监测
        if (webSocketManager.isPing(identifier, message)) {
            String pong = webSocketManager.pong(identifier, message);
            WebSocketUtil.sendMessage(session, pong);
            WebSocket webSocket = webSocketManager.get(identifier);
            // 更新心跳时间
            if (null != webSocket) {
                webSocket.setLastHeart(new Date());
            }
            return;
        }
        // 收到其他消息的时候
        webSocketManager.onMessage(identifier, message);
    }
    
    protected WebSocketManager getWebSocketManager() {
        return SpringUtil.getBean(WebSocketManager.WEBSOCKET_MANAGER_NAME, WebSocketManager.class);
    }
}
