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

package com.wemirr.framework.websocket.utils;

import jakarta.websocket.Session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

/**
 * @author Levin
 */
public class WebSocketUtil {
    
    /**
     * 发送消息
     */
    public static boolean sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 异步发送消息
     */
    public static boolean sendMessageAsync(Session session, String message) {
        Future<Void> voidFuture = session.getAsyncRemote().sendText(message);
        return voidFuture.isDone();
    }
    
    /**
     * 发送字节消息
     */
    public static boolean sendBytes(Session session, byte[] bytes) {
        try {
            session.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 异步发送字节
     */
    public static boolean sendBytesAsync(Session session, byte[] bytes) {
        Future<Void> voidFuture = session.getAsyncRemote().sendBinary(ByteBuffer.wrap(bytes));
        return voidFuture.isDone();
    }
    
    /**
     * 发送对象消息
     */
    public static boolean sendObject(Session session, Object o) {
        try {
            session.getBasicRemote().sendObject(o);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 异步发送对象
     */
    public static boolean sendObjectAsync(Session session, Object o) {
        Future<Void> voidFuture = session.getAsyncRemote().sendObject(o);
        return voidFuture.isDone();
    }
}
