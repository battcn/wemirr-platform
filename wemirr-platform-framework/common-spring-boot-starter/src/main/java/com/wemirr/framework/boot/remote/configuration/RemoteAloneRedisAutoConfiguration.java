package com.wemirr.framework.boot.remote.configuration;

import com.wemirr.framework.boot.remote.exception.RemoteAloneRedisException;
import com.wemirr.framework.boot.remote.properties.RemoteProperties;
import com.wemirr.framework.redis.plus.RedisPlusAutoConfiguration;
import io.lettuce.core.api.StatefulConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
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
     */
    public LettuceConnectionFactory remoteAloneLettuceConnectionFactory() {
        // 获取cfg对象，解析开发者配置的 extend.boot.remote.alone 相关信息
        // 检查是否存在配置，如果不存在，则不创建这个独立的连接工厂
        if (!environment.containsProperty(REMOTE_ALONE_PREFIX + ".host") &&
                // Spring Boot 2.4+
                !environment.containsProperty(REMOTE_ALONE_PREFIX + ".url") &&
                !environment.containsProperty(REMOTE_ALONE_PREFIX + ".cluster.nodes") &&
                !environment.containsProperty(REMOTE_ALONE_PREFIX + ".sentinel.master")) {
            // 如果没有任何关键配置，可能用户不想为远程数据服务使用独立 Redis
            // 这种情况下，我们可以抛出异常，或者让后续的 DictLoadService Bean 创建失败
            // 或者，如果想让 DictLoadService 回退到使用默认 RedisTemplate，则此 Bean 不应创建
            // 这里我们选择抛出异常，如果配置了前缀但信息不全，Binder 会处理
            // 如果根本没有配置，我们在这里提前判断，避免 Binder 报错
            log.error("未找到关联数据注入服务的独立 Redis 配置 (" + REMOTE_ALONE_PREFIX + ")，将不会创建独立连接。");
            // 返回 null 或抛出异常，取决于你希望如何处理缺失配置
            throw new RemoteAloneRedisException("未找到关联数据注入服务的独立 Redis：" + REMOTE_ALONE_PREFIX);
        }

        RedisProperties cfg = Binder.get(environment).bind(REMOTE_ALONE_PREFIX, RedisProperties.class).get();

        // 1. Redis配置
        RedisConfiguration redisAloneConfig;

        // Spring Boot 2.x RedisProperties 会自动处理 URL，这里我们简化，直接用 cfg
        // 但为了兼容 SaToken 的 .pattern 方式，我们保留这部分逻辑
        // 实际 Spring Boot 的 RedisProperties 已经能根据属性自动判断模式了
        // 不过为了复用你的逻辑，我们这里沿用

        if (cfg.getSentinel() != null) {
            // 哨兵集群模式
            RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
            redisSentinelConfiguration.setDatabase(cfg.getDatabase());
            trySetUsername(redisSentinelConfiguration, cfg.getUsername());
            redisSentinelConfiguration.setPassword(RedisPassword.of(cfg.getPassword()));

            RedisProperties.Sentinel sentinel = cfg.getSentinel();
            redisSentinelConfiguration.setMaster(sentinel.getMaster());
            // Sentinel 密码，注意 Spring Boot 2.x 中 sentinel.getPassword() 是针对 Sentinel 服务器认证的
            // RedisProperties.getSentinelPassword() 才是节点密码，但这里 cfg.getPassword() 是主密码
            // 通常 sentinel 节点密码通过 sentinel.getPassword() 设置
            if (sentinel.getPassword() != null && !sentinel.getPassword().isEmpty()) {
                redisSentinelConfiguration.setSentinelPassword(RedisPassword.of(sentinel.getPassword()));
            }


            List<RedisNode> serverList = sentinel.getNodes().stream().map(node -> {
                String[] ipAndPort = node.split(":");
                return new RedisNode(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1]));
            }).collect(Collectors.toList());
            redisSentinelConfiguration.setSentinels(serverList);
            redisAloneConfig = redisSentinelConfiguration;

        } else if (cfg.getCluster() != null && cfg.getCluster().getNodes() != null && !cfg.getCluster().getNodes().isEmpty()) {
            // 普通集群模式
            RedisClusterConfiguration redisClusterConfig = new RedisClusterConfiguration();
            trySetUsername(redisClusterConfig, cfg.getUsername());
            redisClusterConfig.setPassword(RedisPassword.of(cfg.getPassword()));

            RedisProperties.Cluster cluster = cfg.getCluster();
            List<RedisNode> serverList = cluster.getNodes().stream().map(node -> {
                String[] ipAndPort = node.split(":");
                return new RedisNode(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1]));
            }).collect(Collectors.toList());
            redisClusterConfig.setClusterNodes(serverList);
            if (cluster.getMaxRedirects() != null) {
                redisClusterConfig.setMaxRedirects(cluster.getMaxRedirects());
            }
            redisAloneConfig = redisClusterConfig;
        } else {
            // 单体模式 (默认)
            RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
            redisConfig.setHostName(cfg.getHost());
            redisConfig.setPort(cfg.getPort());
            redisConfig.setDatabase(cfg.getDatabase());
            redisConfig.setPassword(RedisPassword.of(cfg.getPassword()));
            trySetUsername(redisConfig, cfg.getUsername());
            redisAloneConfig = redisConfig;
        }
        // 注意：你的代码中 socket 和 aws 模式是自定义的 pattern，Spring Boot RedisProperties 不直接支持
        // 如果需要这些，你需要像 SaAloneRedisInject 中那样显式地检查 pattern 属性
        // 2. 连接池配置
        // 使用泛型
        GenericObjectPoolConfig<StatefulConnection<?, ?>> poolConfig = new GenericObjectPoolConfig<>();
        LettuceClientConfiguration clientConfig;

        RedisProperties.Lettuce lettuce = cfg.getLettuce();
        if (lettuce != null && lettuce.getPool() != null) {
            RedisProperties.Pool pool = lettuce.getPool();
            poolConfig.setMaxTotal(pool.getMaxActive());
            poolConfig.setMaxIdle(pool.getMaxIdle());
            poolConfig.setMinIdle(pool.getMinIdle());
            if (pool.getMaxWait() != null) {
                poolConfig.setMaxWait(pool.getMaxWait());
            }
        }

        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        if (cfg.getTimeout() != null) {
            builder.commandTimeout(cfg.getTimeout());
        }
        if (lettuce != null && lettuce.getShutdownTimeout() != null) {
            builder.shutdownTimeout(lettuce.getShutdownTimeout());
        }

        // SSL 配置 (如果需要)
        if (cfg.getSsl() != null && cfg.getSsl().isEnabled()) {
            builder.useSsl();
        }

        clientConfig = builder.poolConfig(poolConfig).build();

        // 创建Factory对象
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisAloneConfig, clientConfig);
        factory.afterPropertiesSet(); // 初始化
        return factory;
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

    /**
     * 只是为了有ide提示 没有实际作用
     *
     * @return
     */
    @ConfigurationProperties(prefix = REMOTE_ALONE_PREFIX)
    public RedisProperties getRemoteAloneRedisConfig() {
        return new RedisProperties();
    }
}
