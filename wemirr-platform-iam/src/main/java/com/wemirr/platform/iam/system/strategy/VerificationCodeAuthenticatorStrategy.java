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

package com.wemirr.platform.iam.system.strategy;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.configuration.server.support.AuthenticationPrincipal;
import com.wemirr.platform.iam.system.service.CaptchaService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 集成验证码认证.
 *
 * @author Levin
 **/
@Component
public class VerificationCodeAuthenticatorStrategy extends UsernamePasswordAuthenticatorStrategy {
    
    private static final String VERIFICATION_CODE_AUTH_TYPE = "vc";
    
    @Resource
    private CaptchaService captchaService;
    
    @Override
    public void prepare(final AuthenticationPrincipal principal) {
        String vcToken = principal.getParameter("vc_token");
        String vcCode = principal.getParameter("vc_code");
        // 验证验证码
        final Result<Boolean> result = captchaService.valid(vcToken, vcCode);
        if (!result.isSuccessful()) {
            throw CheckedException.badRequest(result.getMessage());
        }
        super.prepare(principal);
    }
    
    @Override
    public String loginType() {
        return VERIFICATION_CODE_AUTH_TYPE;
    }
}
