package com.wemirr.framework.i18n.configuration;

import com.wemirr.framework.i18n.I18nMessageProvider;
import com.wemirr.framework.i18n.domain.I18nMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;

import java.util.List;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class MineStaticMessageSource extends StaticMessageSource implements InitializingBean {

    private final I18nMessageProvider provider;

    @Override
    public void afterPropertiesSet() {
        final List<I18nMessage> messages = provider.selectI18nMessage();
        for (I18nMessage message : messages) {
            addMessage(message.getCode(), LocaleUtils.toLocale(message.getLanguage()), message.getMessage());
        }
    }
}