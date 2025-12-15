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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 用户信息解析函数，用于 DiffLog 中解析用户ID为可读文本
 *
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserParseFunction implements IParseFunction {

    /**
     * 函数名称，在 SpEL 中使用 {getUserDisplay{#userId}} 调用
     */
    public static final String FUNCTION_NAME = "getUserDisplay";

    private final UserService userService;

    @Override
    public String functionName() {
        return FUNCTION_NAME;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }
        return Optional.ofNullable(userService.getById(Convert.toLong(value)))
                .map(this::formatUserDisplay)
                .orElseGet(() -> {
                    log.warn("[用户解析] 获取用户[{}]为空", value);
                    return "";
                });
    }

    /**
     * 格式化用户显示信息，格式：昵称(手机号) 或 昵称
     */
    private String formatUserDisplay(User user) {
        String nickname = user.getNickName();
        return StrUtil.isEmpty(user.getMobile())
                ? nickname
                : "%s(%s)".formatted(nickname, user.getMobile());
    }
}