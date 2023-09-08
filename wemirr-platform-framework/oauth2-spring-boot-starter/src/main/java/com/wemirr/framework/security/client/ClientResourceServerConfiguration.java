package com.wemirr.framework.security.client;

import com.wemirr.framework.security.properties.CustomSecurityProperties;
import com.wemirr.framework.security.server.store.RedisTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

/**
 * @author Levin
 */
@RequiredArgsConstructor
@Import({RedisTokenStore.class, ResourceAuthExceptionEntryPoint.class})
@EnableConfigurationProperties(CustomSecurityProperties.class)
public class ClientResourceServerConfiguration {

    private final CustomSecurityProperties properties;
    private final RestTemplate restTemplate;
    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    @Bean
    private OpaqueTokenIntrospector opaqueTokenIntrospector() {
        final CustomSecurityProperties.OpaqueToken opaqueToken = properties.getOpaqueToken();
        if (opaqueToken.getType() == CustomSecurityProperties.OpaqueTokenType.redis) {
            return new RedisOpaqueTokenIntrospector();
        }
        if (opaqueToken.getType() == CustomSecurityProperties.OpaqueTokenType.rest) {
            return new RestOpaqueTokenIntrospector(restTemplate, properties);
        }
        throw new RuntimeException("未匹配到合适的 OpaqueTokenTemplate ");
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AntPathRequestMatcher[] requestMatchers = properties.getClientIgnore().getResourceUrls().stream()
                .map(AntPathRequestMatcher::new)
                .toList()
                .toArray(new AntPathRequestMatcher[]{});
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(requestMatchers)
                        .permitAll().anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                        .opaqueToken(token -> token.introspector(opaqueTokenIntrospector())))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
