package com.wemirr.framework.i18n;

import com.wemirr.framework.i18n.domain.I18nMessage;

import java.util.List;
import java.util.Locale;

/**
 * @author Levin
 */
public interface I18nMessageProvider {

    /**
     * 加载全部的 i18n 数据
     *
     * @return 国际化消息
     */
    List<I18nMessage> selectI18nMessage();

    /**
     * 获取 I18nMessage 对象
     *
     * @param code   国际化唯一标识
     * @param locale 语言
     * @return 国际化消息
     */
    I18nMessage getI18nMessage(String code, Locale locale);

}
