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

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.MvelHelper;
import org.junit.jupiter.api.Test;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息模板解析测试
 *
 * @author Levin
 */
public class MessageTemplateTest {

    @Test
    void shouldFormatTemplateWithHutoolAndSpring() {
        // 模板字符串
        String template = "欢迎 {username} 来到 WP 系统";

        // Hutool格式化
        Map<String, String> params = new HashMap<>();
        params.put("username", "张三");
        String hutoolResult = StrUtil.format(template, params);
        System.out.println("Hutool格式化: " + hutoolResult);

        // Spring PropertyPlaceholderHelper格式化
        params.put("username", "李四");
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String springTemplate = "欢迎 ${username} 来到 WP 系统";
        String springResult = helper.replacePlaceholders(springTemplate, params::get);
        System.out.println("Spring格式化: " + springResult);
    }

    @Test
    void shouldFormatNestedTemplateWithMvel() {
        // 嵌套变量模板
        String template = "欢迎 ${context.username} 来到 ${os} 系统, 昵称: ${user.nickName}";

        // 层级变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("context", Map.of("username", "张三"));
        variables.put("os", "WP");
        variables.put("user", Map.of("nickName", "小三"));

        // 提取变量并格式化
        System.out.println("提取的变量: " + MvelHelper.getVariables(template));
        String result = MvelHelper.format(template, MvelHelper.transNestedMap(variables));
        System.out.println("MVEL格式化: " + result);
    }
}
