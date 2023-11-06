package com.wemirr.framework.i18n.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class I18nMessageResource {

    public static final String PATTERN = "\\{(.+?)\\}";

    private final MessageSource messageSource;


    public String getMessage(String code, Object... args) {
        final Locale locale = LocaleContextHolder.getLocale();
        final String message = messageSource.getMessage(code, args, code, locale);
        final List<String> codeList = resolveMessage(message);
        // 可以根据自己表达式去做增强 比如 测试带表达式的内容 {i18n.name} XXX
        if (CollUtil.isEmpty(codeList)) {
            return message;
        }
        return StrUtil.replace(message, PATTERN, (param -> messageSource.getMessage(param.group(1), args, param.group(), locale)));
    }


    public List<String> resolveMessage(String message) {
        return ReUtil.findAll(PATTERN, message, 0, new ArrayList<>());
    }

}
