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

package com.wemirr.platform.iam;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.security.utils.PasswordEncoderHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordEncoderHelperTest {

    @Test
    public void passwordEncoderTest() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createelegatingPasswordEncoder();
        String rawPassword = "123456";
        String oldPassword = "{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K";
        log.info("encode - {}", passwordEncoder.encode("123456"));
        log.info("encode - {}", passwordEncoder.encode("123456"));
        log.info("matches - {}", passwordEncoder.matches("123456", oldPassword));

        String xx = BCrypt.hashpw("123456");
        System.out.println(BCrypt.checkpw("123456", xx));
        System.out.println(BCrypt.checkpw("123456", "$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K"));

        System.out.println(PasswordEncoderHelper.encode(rawPassword));
        System.out.println(PasswordEncoderHelper.matches(rawPassword, oldPassword));
        System.out.println(PasswordEncoderHelper.matches(rawPassword, "{noop}123456"));
        System.out.println(PasswordEncoderHelper.matches(rawPassword, "{noop}1234567"));
    }

    @Test
    public void test2() {
        String path = "/sys/message/publish";
        System.out.println(StrUtil.subBefore(path, "/", true));
    }
}
