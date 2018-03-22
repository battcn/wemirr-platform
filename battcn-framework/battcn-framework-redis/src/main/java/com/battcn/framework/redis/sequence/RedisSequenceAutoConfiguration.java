package com.battcn.framework.redis.sequence;

import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * <p>RedisSequenceAutoConfiguration</p>
 *
 * @author Levin
 * @since 2018/03/20
 */

@Configuration
@EnableConfigurationProperties(value = RedisSequenceProperties.class)
@ConditionalOnProperty(
        prefix = "spring.redis.battcn.sequence",
        name = "enabled",
        havingValue = "true"
)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisSequenceAutoConfiguration {

    private final JedisConnectionFactory jedisConnectionFactory;
    private final RedisSequenceProperties redisSequenceProperties;

    @Autowired
    public RedisSequenceAutoConfiguration(JedisConnectionFactory jedisConnectionFactory, RedisSequenceProperties redisSequenceProperties) {
        this.redisSequenceProperties = redisSequenceProperties;
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Bean(name = RedisConstant.SEQUENCE_TEMPLATE_NAME)
    public StringRedisTemplate sequenceRedisTemplate() {
        StringRedisTemplate temple = new StringRedisTemplate();
        jedisConnectionFactory.setDatabase(redisSequenceProperties.getDb());
        temple.setConnectionFactory(jedisConnectionFactory);
        return temple;
    }

    @Bean(name = "sequenceRedisHelper")
    public SequenceRedisHelper sequenceRedisHelper(@Qualifier(RedisConstant.SEQUENCE_TEMPLATE_NAME) StringRedisTemplate sequenceRedisTemplate) {
        SequenceRedisHelper helper = new SequenceRedisHelper();
        helper.setSequenceRedisTemplate(sequenceRedisTemplate);
        return helper;
    }

    @Bean(name = RedisConstant.SEQUENCE_GENERATOR)
    public SequenceGenerator sequenceGenerator(SequenceRedisHelper sequenceRedisHelper) {
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        sequenceGenerator.setRedisHelper(sequenceRedisHelper);
        sequenceGenerator.setRedisSequenceProperties(redisSequenceProperties);
        return sequenceGenerator;
    }
}
