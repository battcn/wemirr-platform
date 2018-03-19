package com.battcn.framework.core.cros;

import com.battcn.framework.core.cros.properties.CorsFilterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Levin
 * @since 2017/12/5 0005
 */
@Configuration
@EnableConfigurationProperties(value = {CorsFilterProperties.class})
@ConditionalOnProperty(
        prefix = "spring.cors",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class CorsFilterAutoConfiguration {

    private static final String PATH = "/**";

    private final CorsFilterProperties properties;

    @Autowired
    public CorsFilterAutoConfiguration(CorsFilterProperties properties) {
        this.properties = properties;
    }

    private static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(defaultString(properties.getOrigin(), CorsConfiguration.ALL));
        corsConfiguration.addAllowedHeader(defaultString(properties.getAllowedHeader(), CorsConfiguration.ALL));
        corsConfiguration.addAllowedMethod(defaultString(properties.getMethod(), CorsConfiguration.ALL));
        if (properties.getExposedHeader() != null) {
            corsConfiguration.addExposedHeader(properties.getExposedHeader());
        }
        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     *
     * @return Cors过滤器
     */
    @Bean
    @ConditionalOnMissingBean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(defaultString(properties.getPath(), PATH), buildConfig());
        return new CorsFilter(source);
    }

}
