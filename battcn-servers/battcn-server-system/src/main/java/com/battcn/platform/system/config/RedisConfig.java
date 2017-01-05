package com.battcn.platform.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.battcn.platform.framework.redis.JedisBuilder;
import com.battcn.platform.framework.redis.impl.RedisOperatorImpl;

@Component
public class RedisConfig {

	@Value("${redis.host}")
	public String host;
	@Value("${redis.port}")
	public Integer port;
	@Value("${redis.timeout}")
	public Integer timeout;
	@Value("${redis.maxidle}")
	public Integer maxidle;

	// @Bean
	public JedisBuilder builder() {
		return new JedisBuilder(host, port, timeout, maxidle, true);
	}

	@Bean
	public RedisOperatorImpl redisOperator() {
		return new RedisOperatorImpl(builder());
	}

}
