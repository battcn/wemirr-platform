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

package com.wemirr.framework.robot.message.push;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.wemirr.framework.robot.RobotProperties;
import com.wemirr.framework.robot.emums.NotifyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 飞书机器人
 * 文档 <a href="https://open.feishu.cn/document/ukTMukTMukTM/ucTM5YjL3ETO24yNxkjN">在线文档</a>
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class FeiShuRobotMessageHandler implements RobotMessageHandler {
    
    private static final String OPEN_API_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/%s";
    
    private final RobotProperties robotProperties;
    
    @Override
    public String notify(String message) {
        final String secret = robotProperties.getFeiShu().getSecret();
        final long currentTimeMillis = System.currentTimeMillis() / 1000L;
        final String stringToSign = currentTimeMillis + "\n" + secret;
        String sign = SecureUtil.hmacSha256(stringToSign).digestBase64(StrUtil.EMPTY, false);
        final Map<String, Object> body = Map.ofEntries(
                Map.entry("timestamp", currentTimeMillis),
                Map.entry("sign", sign),
                Map.entry("msg_type", "text"),
                Map.entry("content", Map.of("text", message)));
        String response = this.request(body);
        log.info("fei shu notify response - {}", response);
        return response;
    }
    
    @Override
    public String getUrl() {
        return String.format(OPEN_API_URL, robotProperties.getFeiShu().getKey());
    }
    
    @Override
    public NotifyType notifyType() {
        return NotifyType.FEI_SHU;
    }
    
}
