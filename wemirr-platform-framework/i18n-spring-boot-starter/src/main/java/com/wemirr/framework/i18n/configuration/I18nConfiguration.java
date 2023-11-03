package com.wemirr.framework.i18n.configuration;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * @author Levin
 **/
@Configuration
public class I18nConfiguration {

    @Bean
    public I18nRedisTemplate i18nPublishTemplate(StringRedisTemplate redisTemplate) {
        return new I18nRedisTemplate(redisTemplate);
    }

    @Bean
    public I18nAspect i18nAspect(MessageSource messageSource) {
        return new I18nAspect(messageSource);
    }


    @Bean
    public MineStaticMessageSource mineStaticMessageSource(StringRedisTemplate redisTemplate) {
        return new MineStaticMessageSource(redisTemplate);
    }
}
