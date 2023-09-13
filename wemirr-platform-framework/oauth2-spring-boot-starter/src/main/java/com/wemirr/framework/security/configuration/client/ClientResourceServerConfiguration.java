package com.wemirr.framework.security.configuration.client;

import com.google.common.collect.Lists;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.configuration.server.store.RedisTokenStore;
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

import java.util.List;

/**
 * @author Levin
 */
@RequiredArgsConstructor
@Import({RedisTokenStore.class, ResourceAuthExceptionEntryPoint.class})
@EnableConfigurationProperties(SecurityExtProperties.class)
public class ClientResourceServerConfiguration {

    private final SecurityExtProperties properties;
    private final RestTemplate restTemplate;
    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    @Bean
    private OpaqueTokenIntrospector opaqueTokenIntrospector() {
        final SecurityExtProperties.OpaqueToken opaqueToken = properties.getClient().getOpaqueToken();
        if (opaqueToken.getType() == SecurityExtProperties.OpaqueTokenType.redis) {
            return new RedisOpaqueTokenIntrospector();
        }
        if (opaqueToken.getType() == SecurityExtProperties.OpaqueTokenType.rest) {
            return new RestOpaqueTokenIntrospector(restTemplate, properties);
        }
        throw new RuntimeException("未匹配到合适的 OpaqueTokenTemplate ");
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> urls = Lists.newArrayList();
        urls.addAll(properties.getClient().getIgnore().getResourceUrls());
        urls.addAll(properties.getDefaultIgnoreUrls());
        AntPathRequestMatcher[] requestMatchers = urls.stream()
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
