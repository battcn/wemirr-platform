package com.wemirr.framework.i18n.configuration;


import com.wemirr.framework.i18n.DefaultI18nMessageProvider;
import com.wemirr.framework.i18n.I18nMessageProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Levin
 **/
@Configuration
public class I18nConfiguration {

    @Bean
    public I18nMessageProvider i18nMessageProvider() {
        return new DefaultI18nMessageProvider();
    }

    @Bean
    public I18nAspect i18nAspect(MessageSource messageSource) {
        return new I18nAspect(messageSource);
    }


    @Bean
    public MineStaticMessageSource mineStaticMessageSource(I18nMessageProvider provider) {
        return new MineStaticMessageSource(provider);
    }
}
