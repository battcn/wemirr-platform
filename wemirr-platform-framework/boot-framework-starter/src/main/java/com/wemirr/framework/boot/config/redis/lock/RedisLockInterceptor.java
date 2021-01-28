package com.wemirr.framework.boot.config.redis.lock;


import com.wemirr.framework.boot.config.redis.RedisKeyGenerator;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.UUID;

import static com.wemirr.framework.boot.config.redis.lock.RedisLockInterceptor.REDIS_LOCK_INTERCEPTOR;


/**
 * @author Levin
 */
@Slf4j
@Aspect
@ConditionalOnProperty(prefix = REDIS_LOCK_INTERCEPTOR, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RedisLockInterceptor {

    public static final String REDIS_LOCK_INTERCEPTOR = "extend.redis.lock.interceptor";

    @Resource
    private RedisLockHelper redisLockHelper;
    @Resource
    private RedisKeyGenerator redisKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.wemirr.framework.boot.config.redis.lock.RedisLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisLock lock = method.getAnnotation(RedisLock.class);
        final String proxyName = StringUtils.join(pjp.getTarget().getClass().getName(), lock.delimiter(), method.getName());
        final String prefix = StringUtils.defaultIfBlank(lock.prefix(), proxyName);
        log.debug("[proxyName] - [{}] - [prefix] - [{}]", proxyName, prefix);
        if (StringUtils.isEmpty(prefix)) {
            throw CheckedException.notFound("lock key prefix don't null...");
        }
        final String lockKey = redisKeyGenerator.generate(prefix, lock.delimiter(), pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLockHelper.tryLock(lockKey, value, lock.expire(), lock.timeUnit());
            if (log.isDebugEnabled()) {
                log.debug("Redis lock key is [{}] and status is [{}]", lockKey, success);
            }
            if (!success) {
                throw new RuntimeException(lock.message());
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                log.error("[server exception]", throwable);
                throw new RuntimeException("server exception");
            }
        } finally {
            // 判断是否需要自动释放锁
            if (lock.unlock()) {
                redisLockHelper.unlock(lockKey, value);
            }
        }
    }
}