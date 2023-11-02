package com.wemirr.framework.i18n;

import com.wemirr.framework.i18n.domain.I18nMessage;

import java.util.List;
import java.util.Locale;

/**
 * @author Levin
 */
public class DefaultI18nMessageProvider implements I18nMessageProvider {
    @Override
    public List<I18nMessage> selectI18nMessage() {
        I18nMessage m1 = I18nMessage.builder().code("i18n.test").message("i18n 测试").language("zh_CN").build();
        I18nMessage m2 = I18nMessage.builder().code("i18n.test").message("i18n test").language("en_US").build();
        return List.of(m1, m2);
    }

    @Override
    public I18nMessage getI18nMessage(String code, Locale locale) {
        return null;
    }
}
