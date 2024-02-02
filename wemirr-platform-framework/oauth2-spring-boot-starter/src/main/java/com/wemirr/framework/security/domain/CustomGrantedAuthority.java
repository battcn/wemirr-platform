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

package com.wemirr.framework.security.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义权限类
 *
 * @author Levin
 */
@Data
@JsonSerialize
public class CustomGrantedAuthority implements GrantedAuthority {
    
    private final String authority;
    private final boolean role;
    private static final String ROLE_ = "ROLE_";
    
    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
        this.role = false;
    }
    
    public CustomGrantedAuthority(String authority, boolean role) {
        this.role = role;
        if (role) {
            // 拼接 ROLE_ 以便于支持 @PreAuthorize("hasRole('ROLE_ADMIN')")
            this.authority = authority.contains(ROLE_) ? authority : ROLE_ + authority;
        } else {
            this.authority = authority;
        }
        
    }
    
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
