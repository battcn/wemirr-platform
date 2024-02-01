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
package com.wemirr.platform.authority.service;

import cn.hutool.captcha.CircleCaptcha;
import com.wemirr.framework.commons.entity.Result;

/**
 * @author Levin
 */
public interface VerificationService {
    
    /**
     * 创建验证码
     *
     * @param key    key
     * @param width  宽度
     * @param height 高度
     * @return 验证码结果
     */
    CircleCaptcha create(String key, Integer width, Integer height);
    
    /**
     * 验证图形验证码
     *
     * @param key   key
     * @param value val
     * @return 验证结果
     */
    Result<Boolean> valid(String key, String value);
    
}
