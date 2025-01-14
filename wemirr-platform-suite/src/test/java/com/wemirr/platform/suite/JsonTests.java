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

package com.wemirr.platform.suite;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONPath;
import org.dromara.x.file.storage.core.hash.HashInfo;
import org.junit.jupiter.api.Test;

public class JsonTests {
    
    @Test
    public void test1() {
        String json = null;
        System.out.println(JSON.parseObject("", HashInfo.class));
        System.out.println(JSON.parseObject(json, HashInfo.class));
        System.out.println(StrUtil.removePrefix("str-xxx", "-"));
        System.out.println(StrUtil.removePrefix(null, "-"));
    }
    
    @Test
    public void test2() {
        String json = null;
        // 将JSON字符串解析为对象
        JSONArray result = (JSONArray) JSONPath.eval(EP_JSON, "$..[?(@.field)]");
        for (Object obj : result) {
            System.out.println(obj.toString());
        }
    }
    
    private static final String EP_JSON = """
            [
               {
                 "type": "page",
                 "id": "root",
                 "label": "页面",
                 "children": [
                   {
                     "label": "表单",
                     "type": "form",
                     "componentProps": {
                       "name": "default",
                       "labelWidth": "100px",
                       "labelLayout": "fixed",
                       "labelCol": {
                         "span": 5
                       },
                       "wrapperCol": {
                         "span": 19
                       }
                     },
                     "children": [
                       {
                         "label": "名称",
                         "type": "input",
                         "field": "name",
                         "input": true,
                         "componentProps": {
                           "placeholder": "请输入名称"
                         },
                         "id": "input_npnj7ex7",
                         "rules": [
                           {
                             "required": true,
                             "message": "必填项",
                             "type": "string",
                             "trigger": [
                               "change"
                             ]
                           }
                         ]
                       },
                       {
                         "label": "邮箱",
                         "type": "input",
                         "field": "email",
                         "input": true,
                         "componentProps": {
                           "placeholder": "请输入邮箱"
                         },
                         "id": "input_nrd81qrj",
                         "rules": [
                           {
                             "required": true,
                             "message": "必填项",
                             "type": "string",
                             "trigger": [
                               "change"
                             ]
                           }
                         ]
                       }
                     ],
                     "id": "form_rnxhlmui"
                   }
                 ],
                 "componentProps": {
                   "style": {
                     "padding": "16px"
                   }
                 }
               }
             ]
            """;
    
}
