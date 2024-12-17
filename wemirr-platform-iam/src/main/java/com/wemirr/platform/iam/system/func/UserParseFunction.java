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

package com.wemirr.platform.iam.system.func;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.service.IParseFunction;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Levin
 */
@Slf4j
@Component
public class UserParseFunction implements IParseFunction {
    
    public static final String NAME = "getById";
    
    @Resource
    private UserService userService;
    
    @Override
    public String functionName() {
        return NAME;
    }
    
    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }
        
        // 获取用户信息
        User user = userService.getById(Convert.toLong(value));
        if (user == null) {
            log.warn("[apply][获取用户{{}}为空", value);
            return "";
        }
        // 返回格式 WP(13888888888)
        String nickname = user.getNickName();
        if (StrUtil.isEmpty(user.getMobile())) {
            return nickname;
        }
        return StrUtil.format("{}({})", nickname, user.getMobile());
    }
    
}