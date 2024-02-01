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
package com.wemirr.framework.robot.message.exception;

import org.aspectj.lang.JoinPoint;

/**
 * 异常通知接口
 *
 * @author Levin
 */
public interface IRobotExceptionMessage {
    
    /**
     * 发送异常内容
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     * @return true 发送成功  false 发送失败
     */
    boolean notify(JoinPoint joinPoint, Exception e);
    
    /**
     * 发送异常内容
     *
     * @param e {@link Exception}
     * @return true 发送成功  false 发送失败
     */
    boolean notify(Exception e);
    
    /**
     * 发送异常内容
     *
     * @param msg 异常消息
     * @return true 发送成功  false 发送失败
     */
    boolean notify(String msg);
}
