/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wemirr.platform.warmflow.util;

import com.alibaba.fastjson2.JSONObject;

/**
 * @author Levin
 */
public interface JsonKeywordHandler {


    /**
     * 提取JSON中关键词的值
     *
     * @param content    context是参数名称 ${id} ${username}
     * @param jsonObject jsonObject
     * @return 结果
     */
    String handleKeyword(String content, JSONObject jsonObject);
}

