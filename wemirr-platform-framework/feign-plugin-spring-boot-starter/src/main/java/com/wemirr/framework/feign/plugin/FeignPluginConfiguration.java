package com.wemirr.framework.feign.plugin;

import com.wemirr.framework.feign.plugin.mock.FeignPluginInterceptor;
import com.wemirr.framework.feign.plugin.mock.MockLoadBalancerFeignClient;
import com.wemirr.framework.feign.plugin.mock.MockProperties;
import feign.Client;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * @author Levin
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {FeignPluginProperties.class, MockProperties.class})
public class FeignPluginConfiguration {


    @Bean
    @Primary
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = MockProperties.MOCK_PREFIX, name = "enabled", havingValue = "true")
    public Client feignClient(LoadBalancerClient loadBalancerClient, LoadBalancerProperties properties,
                              LoadBalancerClientFactory loadBalancerClientFactory, MockProperties mockProperties) {
        return new MockLoadBalancerFeignClient(new Client.Default(null, null),
                loadBalancerClient, properties, loadBalancerClientFactory, mockProperties);
    }


    @Bean
    @ConditionalOnProperty(prefix = FeignPluginProperties.PLUGIN_PREFIX, name = "enabled", havingValue = "true")
    public FeignPluginInterceptor mockFeignInterceptor(FeignPluginProperties properties) {
        return new FeignPluginInterceptor(properties);
    }
}
