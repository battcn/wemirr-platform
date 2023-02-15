package com.wemirr.framework.feign.plugin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wemirr.framework.feign.plugin.mock.FeignPluginInterceptor;
import com.wemirr.framework.feign.plugin.mock.MockLoadBalancerFeignClient;
import com.wemirr.framework.feign.plugin.mock.MockProperties;
import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenInterceptor;
import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenProperties;
import feign.Client;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.concurrent.TimeUnit;


/**
 * @author Levin
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {FeignPluginProperties.class, AutoRefreshTokenProperties.class, MockProperties.class})
public class FeignPluginConfiguration {


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = MockProperties.MOCK_PREFIX, name = "enabled", havingValue = "true")
    public Client feignClient(LoadBalancerClient loadBalancerClient, LoadBalancerClientFactory loadBalancerClientFactory, MockProperties mockProperties) {
        return new MockLoadBalancerFeignClient(new Client.Default(null, null),
                loadBalancerClient, loadBalancerClientFactory, mockProperties);
    }


    @Bean
    @Order(-999999)
    @ConditionalOnProperty(prefix = FeignPluginProperties.PLUGIN_PREFIX, name = "enabled", havingValue = "true")
    public FeignPluginInterceptor mockFeignInterceptor(FeignPluginProperties properties) {
        return new FeignPluginInterceptor(properties);
    }

    @Bean
    @ConditionalOnProperty(prefix = AutoRefreshTokenProperties.TOKEN_PREFIX, name = "enabled", havingValue = "true")
    public AutoRefreshTokenInterceptor feignTokenInterceptor(AutoRefreshTokenProperties properties) {
        final AutoRefreshTokenProperties.Cache cache = properties.getCache();
        Cache<String, String> tokenCache = CacheBuilder.newBuilder().initialCapacity(cache.getInitialCapacity())
                .maximumSize(cache.getMaximumSize()).expireAfterWrite(cache.getExpire(), TimeUnit.SECONDS).build();
        return new AutoRefreshTokenInterceptor(properties, tokenCache);
    }
}
