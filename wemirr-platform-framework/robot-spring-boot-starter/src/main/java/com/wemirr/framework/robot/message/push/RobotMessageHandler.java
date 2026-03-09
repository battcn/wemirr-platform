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

import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.robot.emums.NotifyType;
import com.wemirr.framework.robot.message.RobotMessage;

import java.util.Map;

/**
 * 机器人消息处理器接口
 * <p>支持钉钉、企业微信、飞书等多种机器人</p>
 *
 * @author Levin
 */
public interface RobotMessageHandler {

    /**
     * 发送文本消息
     *
     * @param message 消息内容
     * @return 响应结果
     */
    String notify(String message);

    /**
     * 发送模板消息
     *
     * @param message    文本模板，被替换的部分用 {key} 表示
     * @param map        参数值对
     * @param ignoreNull 是否忽略 {@code null} 值
     * @return 响应结果
     */
    String notify(String message, Map<?, ?> map, boolean ignoreNull);

    /**
     * 发送消息对象
     *
     * @param robotMessage 消息对象
     * @return 响应结果
     */
    default String notify(RobotMessage robotMessage) {
        return notify(robotMessage.getContent());
    }

    /**
     * 获取机器人WebHook地址
     */
    String getUrl();

    /**
     * 获取通知类型
     */
    NotifyType notifyType();

    /**
     * 发送HTTP请求（使用hutool HttpRequest）
     */
    default String request(Map<String, Object> body) {
        return cn.hutool.http.HttpRequest.post(this.getUrl())
                .body(JacksonUtils.toJson(body))
                .timeout(5000)
                .execute()
                .body();
    }
}
