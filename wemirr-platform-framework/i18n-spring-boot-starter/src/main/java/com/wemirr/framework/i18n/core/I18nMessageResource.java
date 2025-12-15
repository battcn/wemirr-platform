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

package com.wemirr.framework.i18n.core;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 国际化消息资源工具类
 * <p>
 * 支持嵌套表达式解析，如 "测试内容 {i18n.name} XXX"
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class I18nMessageResource {

    /**
     * 预编译正则表达式，避免重复编译（性能优化）
     */
    private static final Pattern I18N_PATTERN = Pattern.compile("\\{(.+?)}");

    private final MessageSource messageSource;

    /**
     * 获取国际化消息
     *
     * @param code 消息代码
     * @param args 参数
     * @return 翻译后的消息
     */
    public String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(code, args, code, locale);
        // 检查是否包含嵌套表达式
        if (!containsExpression(message)) {
            return message;
        }
        // 解析嵌套表达式
        return resolveNestedExpressions(message, args, locale);
    }

    /**
     * 快速检查是否包含表达式（避免不必要的正则匹配）
     */
    private boolean containsExpression(String message) {
        return message != null && message.contains("{") && message.contains("}");
    }

    /**
     * 解析嵌套的国际化表达式
     */
    private String resolveNestedExpressions(String message, Object[] args, Locale locale) {
        Matcher matcher = I18N_PATTERN.matcher(message);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String innerCode = matcher.group(1);
            String replacement = messageSource.getMessage(innerCode, args, matcher.group(), locale);
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
