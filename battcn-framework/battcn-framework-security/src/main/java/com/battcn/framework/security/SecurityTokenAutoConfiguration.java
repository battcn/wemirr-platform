package com.battcn.framework.security;

import com.battcn.framework.security.extractor.HeaderTokenExtractor;
import com.battcn.framework.security.extractor.TokenExtractor;
import com.battcn.framework.security.model.token.TokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>RedisSequenceAutoConfiguration</p>
 *
 * @author Levin
 * @since 2018/03/20
 */

@Configuration
@EnableConfigurationProperties(value = SecurityTokenProperties.class)
public class SecurityTokenAutoConfiguration {

    private final SecurityTokenProperties securityTokenProperties;

    @Autowired
    public SecurityTokenAutoConfiguration(SecurityTokenProperties securityTokenProperties) {
        this.securityTokenProperties = securityTokenProperties;
    }

    @Bean
    public TokenExtractor tokenExtractor() {
        return new HeaderTokenExtractor();
    }

    @Bean
    public TokenFactory tokenFactory() {
        return new TokenFactory(securityTokenProperties);
    }

}
