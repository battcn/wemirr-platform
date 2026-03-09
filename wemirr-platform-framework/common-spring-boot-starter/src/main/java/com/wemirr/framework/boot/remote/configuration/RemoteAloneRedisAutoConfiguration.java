package com.wemirr.framework.boot.remote.configuration;

import com.wemirr.framework.boot.remote.exception.RemoteAloneRedisException;
import com.wemirr.framework.boot.remote.properties.RemoteProperties;
import com.wemirr.framework.redis.plus.RedisPlusAutoConfiguration;
import io.lettuce.core.api.StatefulConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.data.redis.autoconfigure.DataRedisProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 远程数据注入服务的独立 Redis 配置。
 * 支持 standalone / sentinel / cluster 三种模式，可通过 extend.boot.remote.alone 前缀配置独立的
 * Redis 连接。
 *
 * @author YanCh
 * @since 2025-05-22 16:03
 **/
@Slf4j
@ConditionalOnProperty(prefix = RemoteProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RemoteAloneRedisAutoConfiguration implements EnvironmentAware {

    public static final String REMOTE_ALONE_PREFIX = RemoteProperties.AloneRedis.PREFIX;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 创建远程数据注入服务专属的 LettuceConnectionFactory
     * 支持 standalone / sentinel / cluster 三种模式
     */
    public LettuceConnectionFactory remoteAloneLettuceConnectionFactory() {
        // 检查是否存在关键配置
        if (!environment.containsProperty(REMOTE_ALONE_PREFIX + ".host") &&
                !environment.containsProperty(REMOTE_ALONE_PREFIX + ".url") &&
                !environment.containsProperty(REMOTE_ALONE_PREFIX + ".cluster.nodes") &&
                !environment.containsProperty(REMOTE_ALONE_PREFIX + ".sentinel.master")) {
            log.error("未找到关联数据注入服务的独立 Redis 配置 (" + REMOTE_ALONE_PREFIX + ")，将不会创建独立连接。");
            throw new RemoteAloneRedisException("未找到关联数据注入服务的独立 Redis：" + REMOTE_ALONE_PREFIX);
        }

        DataRedisProperties cfg = Binder.get(environment).bind(REMOTE_ALONE_PREFIX, DataRedisProperties.class).get();

        // 1. 根据配置决定 Redis 连接模式
        RedisConfiguration redisConfig = buildRedisConfiguration(cfg);

        // 2. 构建 Lettuce 客户端配置（含连接池）
        LettuceClientConfiguration clientConfig = buildLettuceClientConfiguration(cfg);

        // 3. 创建并初始化连接工厂
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisConfig, clientConfig);
        factory.afterPropertiesSet();
        log.info("远程数据注入服务独立 Redis 连接工厂创建成功 [{}]", REMOTE_ALONE_PREFIX);
        return factory;
    }

    /**
     * 根据配置构建 RedisConfiguration（支持 sentinel / cluster / standalone）
     */
    private RedisConfiguration buildRedisConfiguration(DataRedisProperties cfg) {
        if (cfg.getSentinel() != null && cfg.getSentinel().getMaster() != null) {
            return buildSentinelConfiguration(cfg);
        }
        if (cfg.getCluster() != null && cfg.getCluster().getNodes() != null && !cfg.getCluster().getNodes().isEmpty()) {
            return buildClusterConfiguration(cfg);
        }
        return buildStandaloneConfiguration(cfg);
    }

    /**
     * 单体模式
     */
    private RedisStandaloneConfiguration buildStandaloneConfiguration(DataRedisProperties cfg) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(cfg.getHost());
        config.setPort(cfg.getPort());
        config.setDatabase(cfg.getDatabase());
        config.setPassword(RedisPassword.of(cfg.getPassword()));
        trySetUsername(config, cfg.getUsername());
        log.info("远程 Redis 使用【单体模式】 {}:{}/{}", cfg.getHost(), cfg.getPort(), cfg.getDatabase());
        return config;
    }

    /**
     * 哨兵模式
     */
    private RedisSentinelConfiguration buildSentinelConfiguration(DataRedisProperties cfg) {
        DataRedisProperties.Sentinel sentinel = cfg.getSentinel();
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();
        config.setDatabase(cfg.getDatabase());
        config.setMaster(sentinel.getMaster());
        config.setPassword(RedisPassword.of(cfg.getPassword()));
        trySetUsername(config, cfg.getUsername());
        if (sentinel.getPassword() != null && !sentinel.getPassword().isEmpty()) {
            config.setSentinelPassword(RedisPassword.of(sentinel.getPassword()));
        }
        List<RedisNode> nodes = sentinel.getNodes().stream().map(node -> {
            String[] parts = node.split(":");
            return new RedisNode(parts[0].trim(), Integer.parseInt(parts[1].trim()));
        }).collect(Collectors.toList());
        config.setSentinels(nodes);
        log.info("远程 Redis 使用【哨兵模式】 master={}, nodes={}", sentinel.getMaster(), sentinel.getNodes());
        return config;
    }

    /**
     * 集群模式
     */
    private RedisClusterConfiguration buildClusterConfiguration(DataRedisProperties cfg) {
        DataRedisProperties.Cluster cluster = cfg.getCluster();
        RedisClusterConfiguration config = new RedisClusterConfiguration();
        config.setPassword(RedisPassword.of(cfg.getPassword()));
        trySetUsername(config, cfg.getUsername());
        List<RedisNode> nodes = cluster.getNodes().stream().map(node -> {
            String[] parts = node.split(":");
            return new RedisNode(parts[0].trim(), Integer.parseInt(parts[1].trim()));
        }).collect(Collectors.toList());
        config.setClusterNodes(nodes);
        if (cluster.getMaxRedirects() != null) {
            config.setMaxRedirects(cluster.getMaxRedirects());
        }
        log.info("远程 Redis 使用【集群模式】 nodes={}", cluster.getNodes());
        return config;
    }

    /**
     * 构建 Lettuce 客户端配置（含连接池和超时配置）
     */
    private LettuceClientConfiguration buildLettuceClientConfiguration(DataRedisProperties cfg) {
        DataRedisProperties.Lettuce lettuce = cfg.getLettuce();
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration
                .builder();

        // 连接池配置
        if (lettuce != null && lettuce.getPool() != null) {
            DataRedisProperties.Pool pool = lettuce.getPool();
            GenericObjectPoolConfig<StatefulConnection<?, ?>> poolConfig = new GenericObjectPoolConfig<>();
            poolConfig.setMaxTotal(pool.getMaxActive());
            poolConfig.setMaxIdle(pool.getMaxIdle());
            poolConfig.setMinIdle(pool.getMinIdle());
            if (pool.getMaxWait() != null) {
                poolConfig.setMaxWait(pool.getMaxWait());
            }
            builder.poolConfig(poolConfig);
        }

        // 超时配置
        if (cfg.getTimeout() != null) {
            builder.commandTimeout(cfg.getTimeout());
        }
        if (lettuce != null && lettuce.getShutdownTimeout() != null) {
            builder.shutdownTimeout(lettuce.getShutdownTimeout());
        }

        // SSL 配置
        if (cfg.getSsl() != null && cfg.getSsl().isEnabled()) {
            builder.useSsl();
        }

        return builder.build();
    }

    /**
     * 辅助方法，尝试设置 username，兼容低版本 Spring Data Redis
     */
    private void trySetUsername(RedisConfiguration redisConfig, String username) {
        if (username == null || username.isEmpty()) {
            return;
        }
        try {
            if (redisConfig instanceof RedisStandaloneConfiguration) {
                ((RedisStandaloneConfiguration) redisConfig).setUsername(username);
            } else if (redisConfig instanceof RedisSentinelConfiguration) {
                ((RedisSentinelConfiguration) redisConfig).setUsername(username);
            } else if (redisConfig instanceof RedisClusterConfiguration) {
                ((RedisClusterConfiguration) redisConfig).setUsername(username);
            }
            // 添加其他 RedisConfiguration 子类如果需要
        } catch (NoSuchMethodError e) {
            log.warn("当前 Spring Data Redis 版本不支持 Redis 用户名设置，已忽略： {}", e.getMessage());
        }
    }

    /**
     * 创建远程数据服务专属的 RedisTemplate
     */
    @Bean(name = "remoteAloneRedisTemplate")
    @ConditionalOnMissingBean(name = "remoteAloneRedisTemplate") // 如果用户想自定义这个 template，可以覆盖
    @ConditionalOnProperty(prefix = RemoteAloneRedisAutoConfiguration.REMOTE_ALONE_PREFIX, name = "enabled", havingValue = "true")
    @Order(999)
    public RedisTemplate<String, Object> remoteAloneRedisTemplate() {
        LettuceConnectionFactory lettuceConnectionFactory = remoteAloneLettuceConnectionFactory();
        return RedisPlusAutoConfiguration.createRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * 如果不开启服务，使用默认的redis
     *
     * @param connectionFactory
     * @return
     */
    @Bean(name = "remoteAloneRedisTemplate")
    @ConditionalOnMissingBean(name = "remoteAloneRedisTemplate")
    @ConditionalOnProperty(prefix = RemoteAloneRedisAutoConfiguration.REMOTE_ALONE_PREFIX, name = "enabled", havingValue = "false", matchIfMissing = true)
    @Order(999)
    public RedisTemplate<String, Object> defaultRedisTemplate(RedisConnectionFactory connectionFactory) {
        return RedisPlusAutoConfiguration.createRedisTemplate(connectionFactory);
    }
}
