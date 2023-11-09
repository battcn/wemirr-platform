package com.wemirr.framework.i18n.configuration;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.i18n.aspect.I18nAspect;
import com.wemirr.framework.i18n.core.DynamicMessageSource;
import com.wemirr.framework.i18n.core.I18nMessageResource;
import com.wemirr.framework.i18n.core.I18nRedisTemplate;
import com.wemirr.framework.i18n.core.MessageSourceHierarchicalChanger;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.List;


/**
 * @author Levin
 **/
@Order
@Configuration
public class I18nConfiguration {

    @Bean
    public MessageSourceProperties properties() {
        return new MessageSourceProperties();
    }

    @Bean
    public DynamicMessageSource dynamicMessageSource(RedisTemplate<String, Object> redisTemplate) {
        return new DynamicMessageSource(redisTemplate);
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

    @Bean
    public MessageSourceHierarchicalChanger messageSourceHierarchicalChanger(){
        return new MessageSourceHierarchicalChanger();
    }

    @Bean
    @Order
    public I18nRedisTemplate i18nPublishTemplate(StringRedisTemplate redisTemplate) {
        return new I18nRedisTemplate(redisTemplate);
    }

    @Bean
    public I18nMessageResource i18nMessageResource(MessageSource messageSource) {
        return new I18nMessageResource(messageSource);
    }

    @Bean
    public I18nAspect i18nAspect(I18nMessageResource messageSource) {
        return new I18nAspect(messageSource);
    }



}
