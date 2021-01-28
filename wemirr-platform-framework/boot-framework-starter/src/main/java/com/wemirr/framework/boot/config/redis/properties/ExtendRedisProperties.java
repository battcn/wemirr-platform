package com.wemirr.framework.boot.config.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.wemirr.framework.boot.config.redis.properties.ExtendRedisProperties.REDIS;


/**
 * @author Levin
 * @since 2019/07/31
 */
@Data
@ConfigurationProperties(prefix = REDIS)
public class ExtendRedisProperties {

    public static final String REDIS = "extend.redis";


    private RedisType lock;
    private RedisType limit;

    @Data
    public static class RedisType {

        private boolean enabled;
        /**
         * 是否开启 @RedisLimit 的拦截器功能
         */
        private boolean interceptor = true;

    }


}