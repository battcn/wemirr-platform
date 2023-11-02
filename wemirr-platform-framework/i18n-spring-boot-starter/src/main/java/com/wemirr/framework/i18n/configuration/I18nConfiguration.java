package com.wemirr.framework.i18n.configuration;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Levin
 **/
@Configuration
public class I18nConfiguration {

    @Bean
    public I18nAspect i18nAspect(MessageSource messageSource) {
        return new I18nAspect(messageSource);
    }

}
