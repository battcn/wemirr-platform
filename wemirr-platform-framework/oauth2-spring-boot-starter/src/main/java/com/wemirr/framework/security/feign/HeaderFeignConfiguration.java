package com.wemirr.framework.security.feign;

import feign.Feign;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * fegin 配置增强
 *
 * @author L.cm
 */
@Slf4j
@Configuration
@ConditionalOnClass(Feign.class)
@Import(HeaderFeignClientInterceptor.class)
public class HeaderFeignConfiguration {

//    @Primary
//    @Bean
//    public OAuth2ClientContext oAuth2ClientContext() {
//        return new DefaultOAuth2ClientContext();
//    }

    @Primary
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
