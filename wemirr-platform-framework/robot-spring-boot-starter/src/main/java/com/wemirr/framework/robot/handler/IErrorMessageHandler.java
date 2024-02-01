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
package com.wemirr.framework.robot.handler;

import org.aspectj.lang.JoinPoint;

/**
 * 异常消息处理器
 * <p>
 *
 * @author Levin
 */
public interface IErrorMessageHandler {
    
    /**
     * 异常消息内容
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     * @return 异常消息内容
     */
    String message(JoinPoint joinPoint, Exception e);
    
    /**
     * 异常消息内容
     *
     * @param e {@link Exception}
     * @return 异常消息内容
     */
    String message(Exception e);
}
