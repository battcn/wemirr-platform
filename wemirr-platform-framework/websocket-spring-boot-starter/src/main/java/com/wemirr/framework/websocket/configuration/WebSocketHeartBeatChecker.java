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

package com.wemirr.framework.websocket.configuration;

import com.wemirr.framework.websocket.TodoAtRemoved;
import com.wemirr.framework.websocket.WebSocket;
import com.wemirr.framework.websocket.WebSocketManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Levin at 2020/11/20
 */
@Slf4j
public class WebSocketHeartBeatChecker {
    
    /**
     * 定时检测 WebSocket 的心跳时间跟现在的间隔，超过设定的值说明失去了心跳，就去除他，并更新数据库
     * 基于每次 WebSocket 的心跳都更新其心跳时间
     *
     * @param webSocketManager 要检测的容器
     * @param timeSpan         检查到心跳更新时间大于这么毫秒就认为断开了（心跳时间）
     * @param errorTolerant    容忍没有心跳次数
     * @param todoAtRemoved    在删除的时候额外需要做的事情
     */
    public void check(WebSocketManager webSocketManager,
                      long timeSpan,
                      int errorTolerant,
                      TodoAtRemoved todoAtRemoved) {
        final long timeSpans = timeSpan * errorTolerant;
        Map<String, WebSocket> socketMap = webSocketManager.localWebSocketMap();
        Date now = new Date();
        List<WebSocket> toRemoves = new LinkedList<>();
        socketMap.forEach((identifier, webSocket) -> {
            long interval = now.getTime() - webSocket.getLastHeart().getTime();
            if (interval >= timeSpans) {
                // 说明失去心跳了
                log.info("{} 失去心跳了", identifier);
                toRemoves.add(webSocket);
            }
        });
        
        if (toRemoves.size() > 0) {
            for (WebSocket webSocket : toRemoves) {
                // 内存删了
                socketMap.remove(webSocket.getIdentifier());
            }
            // 额外比如还有数据库操作
            todoAtRemoved.todoWith(toRemoves);
        }
    }
}
