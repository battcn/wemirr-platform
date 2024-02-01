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
package com.wemirr.framework.boot.remote.configuration;

import com.wemirr.framework.boot.remote.RemoteService;
import com.wemirr.framework.commons.annotation.remote.RemoteResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * RemoteResult 注解的 AOP 工具
 *
 * @author Levin
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class RemoteResultAspect {
    
    private final RemoteService remoteService;
    
    @Pointcut("@annotation(com.wemirr.framework.commons.annotation.remote.RemoteResult)")
    public void methodPointcut() {
    }
    
    @Around("methodPointcut() && @annotation(rr)")
    public Object interceptor(ProceedingJoinPoint pjp, RemoteResult rr) throws Throwable {
        Object proceed = pjp.proceed();
        remoteService.action(proceed, rr.ignoreFields());
        return proceed;
    }
}
