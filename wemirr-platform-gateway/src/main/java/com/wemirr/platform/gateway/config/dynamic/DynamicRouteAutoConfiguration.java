package com.wemirr.platform.gateway.config.dynamic;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.wemirr.platform.gateway.route.NacosRouteDefinitionRepository;
import com.wemirr.platform.gateway.route.RedisRouteDefinitionRepository;
import com.wemirr.platform.gateway.route.RedisRouteDynamicGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 动态路由自动装配
 *
 * @author Levin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicRouteProperties.class)
@ConditionalOnProperty(prefix = "spring.cloud.gateway", name = "enabled", havingValue = "true",matchIfMissing = true)
public class DynamicRouteAutoConfiguration {

    private final ApplicationEventPublisher publisher;


    @Bean
    @ConditionalOnProperty(prefix = "spring.cloud.gateway.dynamic-route", name = "type", havingValue = "redis", matchIfMissing = true)
    public RedisRouteDefinitionRepository redisRouteDefinitionRepository(StringRedisTemplate redisTemplate) {
        log.info(" init redisRouteDefinitionRepository ");
        return new RedisRouteDefinitionRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.cloud.gateway.dynamic-route", name = "type", havingValue = "redis", matchIfMissing = true)
    public RedisRouteDynamicGatewayService redisRouteDynamicGatewayService(RedisRouteDefinitionRepository redisRouteDefinitionRepository) {
        log.info(" init redisRouteDynamicGatewayService ");
        return new RedisRouteDynamicGatewayService(publisher, redisRouteDefinitionRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.cloud.gateway.dynamic-route", name = "type", havingValue = "nacos")
    public NacosRouteDefinitionRepository nacosRouteDefinitionRepository(NacosConfigManager nacosConfigManager) {
        log.info(" init nacosRouteDefinitionRepository ");
        return new NacosRouteDefinitionRepository(publisher, nacosConfigManager);
    }


}
