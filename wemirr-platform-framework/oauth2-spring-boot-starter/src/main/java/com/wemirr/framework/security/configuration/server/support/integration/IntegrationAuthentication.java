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

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

import java.util.Map;

/**
 * @author Levin
 * @since 2019-04-03
 **/
@Data
public class IntegrationAuthentication {
    
    private String loginType;
    private String username;
    private String password;
    private String clientId;
    private String tenantCode;
    private Map<String, String[]> authParameters;
    
    public String getAuthParameter(String parameter) {
        String[] values = this.authParameters.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
    
    public IntegrationAuthentication() {
        
    }
    
    public IntegrationAuthentication(HttpServletRequest request) {
        this.setLoginType(request.getParameter("login_type"));
        this.setUsername(request.getParameter("username"));
        this.setPassword(request.getParameter("password"));
        this.setClientId(request.getParameter("client_id"));
        this.setTenantCode(request.getParameter("tenant_code"));
        this.setAuthParameters(request.getParameterMap());
    }
}