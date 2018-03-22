package com.battcn.framework.redis.lock;

import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.redis.annotation.LockParam;
import com.battcn.framework.redis.constant.RedisConstant;
import com.battcn.framework.redis.exception.RedisException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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

    private final RedisTemplate<String, String> lockRedisTemplate;

    @Autowired
    public CacheLockInterceptor(@Qualifier(RedisConstant.LOCK_TEMPLATE_NAME) RedisTemplate<String, String> lockRedisTemplate) {
        this.lockRedisTemplate = lockRedisTemplate;
    }

    @Around(value = "execution(public * *(..)) && @annotation(com.battcn.framework.redis.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.lockedPrefix())) {
            throw new RedisException("lock key don't null...");
        }
        final String lockKey = getLockKey(pjp, method, lock);
        System.out.println(lockKey);
        try {
            final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "1");
            if (!success) {
                throw new RuntimeException("请勿重复请求");
            }
            lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            lockRedisTemplate.delete(lockKey);
        }
        return null;
    }

    private static String getLockKey(ProceedingJoinPoint pjp, Method method, CacheLock lock) {
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        // TODO 默认解析方法里面带 LockParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++) {
            final LockParam annotation = parameters[i].getAnnotation(LockParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(lock.delimiter()).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final LockParam annotation = field.getAnnotation(LockParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lock.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return lock.lockedPrefix() + builder.toString();
    }
}