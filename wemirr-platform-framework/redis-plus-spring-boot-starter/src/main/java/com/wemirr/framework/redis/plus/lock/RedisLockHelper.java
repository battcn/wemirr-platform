package com.wemirr.framework.redis.plus.lock;


import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.redis.plus.exception.RedisLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author levin
 */
@Slf4j
@RequiredArgsConstructor
public class RedisLockHelper {


    private final RedissonClient redissonClient;

    public List<RLock> batchLock(List<String> keys) {
        return batchLock(keys, -1L);
    }


    public <T> T execute(List<String> keys, long time, Supplier<T> func) {
        List<RLock> locks = batchLock(keys, time);
        try {
            return func.get();
        } finally {
            unlock(locks);
        }
    }

    public <T> T execute(String key, long waitTime, TimeUnit unit, Supplier<T> func) {
        return execute(key, waitTime, -1L, unit, func);
    }

    public <T> T execute(String key, long waitTime, long leaseTime, TimeUnit unit, Supplier<T> func) {
        RLock rLock = redissonClient.getLock(key);
        try {
            final boolean success = rLock.tryLock(waitTime, leaseTime, unit);
            if (!success) {
                throw new RedisLockException("");
            }
            return func.get();
        } catch (InterruptedException e) {
            log.error("Redis try lock InterruptedException", e);
            throw new RedisLockException("线程中断" + e.getLocalizedMessage());
        } finally {
            unlock(List.of(rLock));
        }
    }

    /**
     * 加锁（可重入）
     *
     * @param keys 锁的key集合
     * @param time time
     */
    public List<RLock> batchLock(Collection<String> keys, long time) {
        if (CollUtil.isEmpty(keys)) {
            return null;
        }
        List<RLock> locks = new ArrayList<>();
        List<String> newKeys = keys.stream().distinct().sorted().toList();
        boolean lockStatus = false;
        try {
            for (String key : newKeys) {
                RLock lock = redissonClient.getLock(key);
                locks.add(lock);
                lockStatus = lock.tryLock(time, TimeUnit.SECONDS);
                if (!lockStatus) {
                    //加锁失败
                    throw CheckedException.badRequest("当前分布式锁 KEY - {0}", key);
                }
            }
        } catch (Exception e) {
            log.error("批量创建分布式锁异常 - {}", e.getLocalizedMessage());
            //加锁失败
            throw new CheckedException("添加锁异常");
        } finally {
            if (!lockStatus) {
                unlock(locks);
            }
        }
        return locks;
    }

    /**
     * 解锁
     *
     * @param locks 加锁时保存的锁集合
     */
    public void unlock(List<RLock> locks) {
        if (CollectionUtils.isEmpty(locks)) {
            return;
        }
        for (RLock lock : locks) {
            if (lock == null || !lock.isHeldByCurrentThread()) {
                continue;
            }
            lock.unlock();
        }
    }

}
