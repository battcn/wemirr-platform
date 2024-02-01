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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 异常捕获切面处理类
 * <p>
 *
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RobotExceptionNotifyAspect {
    
    private final IRobotExceptionMessage sendException;
    
    /**
     * 切入点配置
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }
    
    /**
     * 切点方法执行异常调用
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        if (!sendException.notify(joinPoint, e)) {
            log.error("exception message sending failed", e);
        }
    }
}
