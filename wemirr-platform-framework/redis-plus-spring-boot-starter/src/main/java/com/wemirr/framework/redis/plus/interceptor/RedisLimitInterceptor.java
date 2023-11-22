package com.wemirr.framework.redis.plus.interceptor;

import com.wemirr.framework.redis.plus.RedisLimitHelper;
import com.wemirr.framework.redis.plus.anontation.RedisLimit;
import com.wemirr.framework.redis.plus.utils.RedisAopUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * redisson分布式限流器切面处理
 *
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLimitInterceptor {

    private final RedisLimitHelper redisLimitHelper;


    @Pointcut("@annotation(com.wemirr.framework.redis.plus.anontation.RedisLimit)")
    public void redissonRateAspectPointcut() {
    }

    /**
     * 切面处理redisson限流器
     *
     * @param point point
     */
    @Around("redissonRateAspectPointcut()")
    public Object doRedissonRateAround(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        RedisLimit rateLimiter = AnnotationUtils.getAnnotation(method, RedisLimit.class);
        if (rateLimiter == null) {
            return point.proceed();
        }
        String key = RedisAopUtils.parse(rateLimiter.prefix(), rateLimiter.useArgs(), method, point.getArgs(), point);
        try {
            final boolean tryAcquire = redisLimitHelper.tryAcquire(key, rateLimiter.limit(), rateLimiter.timeout(), rateLimiter.unit(), rateLimiter.type(), rateLimiter.retryTime());
            if (tryAcquire) {
                log.debug("Redisson rate limiter obtained the token success with key: {}", key);
                return point.proceed();
            } else {
                log.error("Redisson rate limiter blocked the request with key: {}", key);
                throw new RuntimeException("Redisson rate limiter blocked the request");
            }
        } catch (InterruptedException e) {
            log.error("Redisson rate limiter encountered an error with key: {}, error:", key, e);
        }
        return null;
    }


}
