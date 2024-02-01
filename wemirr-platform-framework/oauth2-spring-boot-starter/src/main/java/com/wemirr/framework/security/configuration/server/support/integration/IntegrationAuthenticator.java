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
package com.wemirr.framework.security.configuration.server.support.integration;

import com.wemirr.framework.security.domain.UserInfoDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;

/**
 * @author Levin
 **/
public interface IntegrationAuthenticator extends Ordered {
    
    String DEFAULT_AUTH_TYPE = "password";
    
    /**
     * 处理集成认证
     *
     * @param integrationAuthentication integrationAuthentication
     * @return 认证信息
     */
    UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication);
    
    /**
     * 进行预处理
     *
     * @param integrationAuthentication integrationAuthentication
     */
    void prepare(IntegrationAuthentication integrationAuthentication);
    
    /**
     * 判断是否支持集成认证类型
     *
     * @param integrationAuthentication integrationAuthentication
     * @return 是否统一处理
     */
    default boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.equals(integrationAuthentication.getLoginType(), loginType());
    }
    
    /**
     * 登录方式
     *
     * @return 默认密码登录
     */
    default String loginType() {
        return DEFAULT_AUTH_TYPE;
    }
    
    /**
     * 认证结束后执行
     *
     * @param integrationAuthentication integrationAuthentication
     */
    default void complete(IntegrationAuthentication integrationAuthentication) {
        
    }
    
}