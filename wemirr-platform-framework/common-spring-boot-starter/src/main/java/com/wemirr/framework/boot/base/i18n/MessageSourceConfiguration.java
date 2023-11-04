package com.wemirr.framework.boot.base.i18n;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.i18n.configuration.MineStaticMessageSource;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.time.Duration;
import java.util.List;


/**
 * @author Levin
 **/
@Configuration
public class MessageSourceConfiguration {


    @Bean
    @Primary
    public I18nMessageResource i18nMessageResource(MineStaticMessageSource messageSource) {
        return new I18nMessageResource(messageSource);
    }

    @Bean
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    /**
     * 系统国际化文件配置
     *
     * @return MessageSource
     */
    @Bean
    @Primary
    public MessageSource messageSource(MessageSourceProperties properties) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        List<String> baseNames = StrUtil.split(properties.getBasename(), ",");
        baseNames.add("classpath:/default-i18n/messages");
        baseNames.add("classpath:i18n/messages");
        baseNames.add("classpath:messages");
        baseNames.add("messages");
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        messageSource.setBasenames(ArrayUtil.toArray(baseNames, String.class));
        return messageSource;
    }

}
