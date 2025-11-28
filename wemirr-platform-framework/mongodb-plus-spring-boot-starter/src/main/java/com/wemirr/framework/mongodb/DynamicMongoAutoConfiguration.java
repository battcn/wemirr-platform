package com.wemirr.framework.mongodb;

import com.wemirr.framework.mongodb.toolkit.DynamicMongoRoutingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mongodb plus
 *
 * @author levin
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class DynamicMongoAutoConfiguration {

    @Bean
    public DynamicMongoRoutingInterceptor dynamicMongoRoutingInterceptor() {
        return new DynamicMongoRoutingInterceptor();
    }

}
