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

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.robot.emums.NotifyType;

import java.util.Map;

/**
 * 发送消息接口
 *
 * @author Levin
 */
public interface RobotMessageHandler {

    /**
     * 消息通知
     *
     * @param message 消息内容
     */
    String notify(String message);

    /**
     * 格式化文本，使用 {varName} 占位<br>
     * map = {a: "aValue", b: "bValue"} format("{a} and {b}", map) ---=》 aValue and bValue
     *
     * @param message    文本模板，被替换的部分用 {key} 表示
     * @param map        参数值对
     * @param ignoreNull 是否忽略 {@code null} 值，忽略则 {@code null} 值对应的变量不被替换，否则替换为""
     * @return 格式化后的文本
     * @since 5.7.10
     */
    String notify(String message, Map<?, ?> map, boolean ignoreNull);

    String getUrl();

    /**
     * 通知类型
     *
     * @return 通知类型
     */
    NotifyType notifyType();

    default String request(Map<String, Object> body) {
        return HttpUtil.post(this.getUrl(), JSON.toJSONString(body));
    }

}
