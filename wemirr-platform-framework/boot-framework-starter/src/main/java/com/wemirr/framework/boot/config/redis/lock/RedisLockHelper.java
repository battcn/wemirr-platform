package com.wemirr.framework.boot.config.redis.lock;

import cn.hutool.core.thread.NamedThreadFactory;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class RedisLockHelper {

    private final RedisConnectionFactory connectionFactory;
    private final StringRedisTemplate stringRedisTemplate;

    private static final String DELIMITER = "|";

    /**
     * 如果要求比较高可以通过注入的方式分配
     */
    private static final ScheduledThreadPoolExecutor EXECUTOR_SERVICE =
            new ScheduledThreadPoolExecutor(10, new NamedThreadFactory("redis-unlock-thread-pool", false));

    /**
     * 获取锁
     *
     * @param lockKey lockKey
     * @param uuid    UUID
     * @param timeout 超时时间
     * @param unit    过期单位
     * @return true or false
     */
    public boolean tryLock(String lockKey, final String uuid, long timeout, final TimeUnit unit) {
        final Boolean execute = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uuid, timeout, unit);
        return execute != null && execute;
    }


    /**
     * @param lockKey 锁Key
     * @param value   锁Value
     * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public void unlock(String lockKey, String value) {
        unlock(lockKey, value, 0, TimeUnit.MILLISECONDS);
    }


    /**
     * 定义释放锁的lua脚本
     */
    private final static DefaultRedisScript<Long> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call(\"get\",KEYS[1]) == KEYS[2] then return redis.call(\"del\",KEYS[1]) else return -1 end"
            , Long.class
    );

    /**
     * 延迟unlock
     *
     * @param lockKey   key
     * @param uuid      client(最好是唯一键的)
     * @param delayTime 延迟时间
     * @param unit      时间单位
     */
    public void unlock(final String lockKey, final String uuid, long delayTime, TimeUnit unit) {
        if (StringUtils.isBlank(lockKey)) {
            return;
        }
        if (delayTime <= 0) {
            doUnlock(lockKey, uuid);
        } else {
            EXECUTOR_SERVICE.schedule(() -> doUnlock(lockKey, uuid), delayTime, unit);
        }
    }

    /**
     * @param lockKey key
     * @param uuid    client(最好是唯一键的)
     */
    private void doUnlock(final String lockKey, final String uuid) {
        stringRedisTemplate.execute(UNLOCK_LUA_SCRIPT, Lists.newArrayList(lockKey, uuid));
    }


}
