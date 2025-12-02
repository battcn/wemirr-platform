package com.wemirr.platform.ai.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xJh
 * @date 2025/9/29
 **/
@Component
public class RateLimitHelper {
    private final StringRedisTemplate redisTemplate;
    private final ReentrantLock scriptLoadLock = new ReentrantLock();

    private DefaultRedisScript<Long> acquireScript;

    public RateLimitHelper(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        String script = """
            local key = KEYS[1]
            local limit = tonumber(ARGV[1])
            local expire = tonumber(ARGV[2])
            local current = redis.call('INCR', key)
            if current == 1 then
                redis.call('EXPIRE', key, expire)
            end
            if current <= limit then
                return 1
            else
                return 0
            end
            """;

        acquireScript = new DefaultRedisScript<>();
        acquireScript.setScriptText(script);
        acquireScript.setResultType(Long.class);
    }

    /**
     * 尝试获取限流许可
     * @param key Redis Key
     * @param maxRequests 最大请求数
     * @param period 限流周期
     * @return 是否允许
     */
    public boolean tryAcquire(String key, int maxRequests, java.time.Duration period) {
        Long expireSeconds = period.getSeconds();
        List<String> keys = Collections.singletonList(key);
        Long result = redisTemplate.execute(
                acquireScript,
                keys,
                String.valueOf(maxRequests),
                String.valueOf(expireSeconds)
        );
        return result != null && result == 1L;
    }
}
