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

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.websocket.WebSocket;
import com.wemirr.framework.websocket.memory.MemWebSocketManager;
import com.wemirr.framework.websocket.redis.action.Action;
import com.wemirr.framework.websocket.redis.action.BroadCastAction;
import com.wemirr.framework.websocket.redis.action.RemoveAction;
import com.wemirr.framework.websocket.redis.action.SendMessageAction;
import com.wemirr.framework.websocket.utils.WebSocketUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * WebSocket的session无法序列化,所以session还是保存在本地内存中，发送消息这种就走订阅发布模式
 * 1.redis或者mq进行发布订阅，广播->有某个节点能找到此人就发送消息，其他的忽略
 * 2.Nginx 进行IP hash 可以使用{@link MemWebSocketManager}
 * <p>
 * 3.需要扩展不同的功能,就写相应的Action,放入容器中,然后给订阅的频道发布一条包含该Action的JSON串
 *
 * @author Levin
 * @see RedisWebSocketManager#sendMessage
 */
public class RedisWebSocketManager extends MemWebSocketManager {
    
    public static final String CHANNEL = "websocket";
    private static final String COUNT_KEY = "websocket:connection:count";
    
    @Override
    public void put(String identifier, WebSocket webSocket) {
        super.put(identifier, webSocket);
        // 在线数量加1
        countChange(1);
    }
    
    @Override
    public void remove(String identifier) {
        final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        boolean containsKey = localWebSocketMap().containsKey(identifier);
        if (containsKey) {
            super.remove(identifier);
        } else {
            JSONObject map = new JSONObject();
            map.put(Action.ACTION, RemoveAction.class.getName());
            map.put(Action.IDENTIFIER, identifier);
            // 在websocket频道上发布发送消息的消息
            redisTemplate.convertAndSend(getChannel(), map.toJSONString());
        }
        // 在线数量减1
        countChange(-1);
    }
    
    @Override
    public int size() {
        return getCount();
    }
    
    @Override
    public void sendMessage(String identifier, String message) {
        WebSocket webSocket = get(identifier);
        // 本地能找到就直接发
        if (null != webSocket) {
            WebSocketUtil.sendMessage(webSocket.getSession(), message);
            return;
        }
        final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        JSONObject map = new JSONObject();
        map.put(Action.ACTION, SendMessageAction.class.getName());
        map.put(Action.IDENTIFIER, identifier);
        map.put(Action.MESSAGE, message);
        // 在websocket频道上发布发送消息的消息
        redisTemplate.convertAndSend(getChannel(), map.toJSONString());
    }
    
    @Override
    public void broadcast(String message) {
        JSONObject map = new JSONObject();
        map.put(Action.ACTION, BroadCastAction.class.getName());
        map.put(Action.MESSAGE, message);
        final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        // 在websocket频道上发布广播的消息
        redisTemplate.convertAndSend(getChannel(), map.toJSONString());
    }
    
    protected String getChannel() {
        return CHANNEL;
    }
    
    /**
     * 增减在线数量
     */
    private void countChange(int delta) {
        final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        
        // 获取在线当前数量
        int count = getCount(value);
        
        count = count + delta;
        count = Math.max(count, 0);
        
        // 设置新的数量
        value.set(COUNT_KEY, "" + count);
    }
    
    /**
     * 获取当前在线数量
     */
    private int getCount() {
        final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        return getCount(value);
    }
    
    private int getCount(ValueOperations<String, String> value) {
        String countStr = value.get(COUNT_KEY);
        int count = 0;
        if (null != countStr) {
            count = Integer.parseInt(countStr);
        }
        return count;
    }
}
