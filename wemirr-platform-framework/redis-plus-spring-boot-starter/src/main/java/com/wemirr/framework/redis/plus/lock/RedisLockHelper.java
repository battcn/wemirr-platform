package com.wemirr.framework.redis.plus.lock;


import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.redis.plus.exception.RedisLockException;
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
public record RedisLockHelper(RedissonClient redissonClient) {


    /**
     * 批量上锁
     *
     * @param keys keys
     * @return 锁集合
     */
    public List<RLock> batchLock(List<String> keys) {
        return batchLock(keys, -1L);
    }


    /**
     * 不带回调的锁
     *
     * @param key  分布式锁KEY
     * @param func 回调函数
     */
    public void execute(String key, Runnable func) {
        RLock lock = redissonClient.getLock(key);
        boolean tryLock = lock.tryLock();
        log.info("当前锁 - {} , 上锁状态 - {}", key, tryLock);
        if (!tryLock) {
            throw new RedisLockException("Redis 已被获取,请勿重复操作");
        }
        try {
            func.run();
        } finally {
            unlock(List.of(lock));
        }
    }

    /**
     * 批量上锁
     *
     * @param keys     keys
     * @param waitTime 等待时间
     * @param func     回调函数
     * @param <T>      T
     * @return 执行结果
     */
    public <T> T execute(List<String> keys, long waitTime, Supplier<T> func) {
        List<RLock> locks = batchLock(keys, waitTime);
        try {
            return func.get();
        } finally {
            unlock(locks);
        }
    }

    /**
     * 函数回调执行
     *
     * @param key      分布式锁KEY
     * @param waitTime 等待时间
     * @param unit     时间单位
     * @param func     回调函数
     * @param <T>      T
     * @return 执行结果
     */
    public <T> T execute(String key, long waitTime, TimeUnit unit, Supplier<T> func) {
        return execute(key, waitTime, -1L, unit, func);
    }

    /**
     * 函数回调执行
     *
     * @param key       分布式锁KEY
     * @param waitTime  等待时间
     * @param leaseTime 分布式锁租期 -1 就是开启 watch dog 自动续签
     * @param unit      时间单位
     * @param func      执行回调的函数
     * @param <T>       T
     * @return 执行结果
     */
    public <T> T execute(String key, long waitTime, long leaseTime, TimeUnit unit, Supplier<T> func) {
        RLock rLock = redissonClient.getLock(key);
        try {
            final boolean success = rLock.tryLock(waitTime, leaseTime, unit);
            if (!success) {
                throw new RedisLockException("Redis 锁获取失败");
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
     * @param keys     锁的key集合
     * @param waitTime time
     */
    public List<RLock> batchLock(Collection<String> keys, long waitTime) {
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
                lockStatus = lock.tryLock(waitTime, TimeUnit.SECONDS);
                if (!lockStatus) {
                    // 加锁失败
                    throw new RedisLockException("加锁失败 KEY ：" + key);
                }
            }
        } catch (Exception e) {
            log.error("批量创建分布式锁异常 - {}", e.getLocalizedMessage());
            throw new RedisLockException("批量创建分布式锁异常");
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
