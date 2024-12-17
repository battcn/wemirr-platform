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

public class MessageTemplateTest {
    
    @Test
    public void format() {
        // 模板字符串
        String template = "欢迎 {username} 来到 WP 系统";
        
        // 参数映射
        Map<String, String> params = new HashMap<>();
        params.put("username", "张三");
        // 使用 Hutool 进行模板替换
        System.out.println(StrUtil.format(template, params)); // 输出: 欢迎 张三 来到 WP 系统
        params.put("username", "李四");
        // 创建占位符解析器
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        // 替换占位符
        System.out.println(helper.replacePlaceholders(template, params::get)); // 输出: 欢迎 李四 来到 WP 系统
    }
    
    @Test
    public void test2() {
        // 模板字符串
        String template = "欢迎 ${context.username} 来到 ${os} 系统,${user.nickName},${xs.test},${xs.xx}";
        
        // 层级变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("context", Map.of("username", "张三"));
        variables.put("os", "WP");
        // variables.put("xs.test", "os-xxxx");
        // variables.put("xs", "osadsadx");
        variables.put("user", Map.of("nickName", "小三"));
        System.out.println(MvelHelper.getVariables(template));
        // 输出结果
        System.out.println(MvelHelper.format(template, MvelHelper.transNestedMap(variables)));
    }
    
}
