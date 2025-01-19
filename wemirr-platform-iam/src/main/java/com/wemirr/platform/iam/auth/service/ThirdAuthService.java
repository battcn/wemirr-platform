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

package com.wemirr.platform.iam.auth.service;

import com.wemirr.platform.iam.auth.domain.dto.resp.ThirdAuthResp;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;

/**
 * @author Levin
 */
public interface ThirdAuthService {
    
    /**
     * 三方授权平台
     *
     * @return 授权平台
     */
    ThirdAuthType platform();
    
    /**
     * AuthRequest
     *
     * @return AuthRequest
     */
    AuthRequest authRequest();
    
    /**
     * 授权信息
     *
     * @return 授权信息
     */
    ThirdAuthResp authorize();
    
    /**
     * 回调
     *
     * @param user user
     */
    void callback(AuthUser user);
    
}
