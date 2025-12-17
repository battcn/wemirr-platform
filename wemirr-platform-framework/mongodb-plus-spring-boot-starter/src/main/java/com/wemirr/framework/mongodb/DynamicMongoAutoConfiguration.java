package com.wemirr.framework.mongodb;

import com.wemirr.framework.mongodb.audit.MongoAuditorAware;
import com.wemirr.framework.mongodb.toolkit.DynamicMongoRoutingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * MongoDB Plus 自动配置
 * <p>提供动态数据源、审计字段自动填充等功能</p>
 *
 * @author levin
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableMongoAuditing
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class DynamicMongoAutoConfiguration {

    /**
     * 动态数据源路由拦截器
     */
    @Bean
    public DynamicMongoRoutingInterceptor dynamicMongoRoutingInterceptor() {
        return new DynamicMongoRoutingInterceptor();
    }

    /**
     * 审计用户提供者（自动填充@CreatedBy和@LastModifiedBy）
     */
    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    public AuditorAware<Long> mongoAuditorAware() {
        log.info("Initializing MongoDB auditor aware");
        return new MongoAuditorAware();
    }
}
