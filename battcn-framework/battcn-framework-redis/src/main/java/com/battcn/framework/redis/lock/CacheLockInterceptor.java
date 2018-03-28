package com.battcn.framework.redis.lock;

import com.battcn.framework.redis.CacheKeyGenerator;
import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.redis.constant.RedisConstant;
import com.battcn.framework.redis.exception.CacheException;
import com.battcn.framework.redis.exception.CacheLockException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author Levin
 * @since 2018/3/22 0022
 */
@Aspect
@Configuration
@ConditionalOnProperty(
        prefix = "spring.redis.battcn.lock",
        name = "enabled",
        havingValue = "true"
)
public class CacheLockInterceptor {

    private final CacheKeyGenerator lockKeyGenerator;
    private final RedisTemplate<String, Object> lockRedisTemplate;

    @Autowired
    public CacheLockInterceptor(@Qualifier(RedisConstant.LOCK_TEMPLATE_NAME) RedisTemplate<String, Object> lockRedisTemplate, @Qualifier(RedisConstant.LOCK_KEY_GENERATOR) CacheKeyGenerator lockKeyGenerator) {
        this.lockRedisTemplate = lockRedisTemplate;
        this.lockKeyGenerator = lockKeyGenerator;
    }

    @Around(value = "execution(public * *(..)) && @annotation(com.battcn.framework.redis.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new CacheException("lock key don't null...");
        }
        final String lockKey = lockKeyGenerator.getLockKey(pjp);
        try {
            final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "1");
            if (!success) {
                throw new CacheLockException("请勿重复请求");
            }
            lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
            return pjp.proceed();
        } finally {
            lockRedisTemplate.delete(lockKey);
        }
    }
}