package com.wemirr.framework.redis.plus.interceptor;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.exception.RedisLockException;
import com.wemirr.framework.redis.plus.RedisKeyGenerator;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;


/**
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLockInterceptor {

    private final RedissonClient redissonClient;
    private final RedisKeyGenerator redisKeyGenerator;

    private final ExpressionParser parser = new SpelExpressionParser();

    private final StandardReflectionParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();


    @SneakyThrows
    @Around("execution(public * *(..)) && @annotation(com.wemirr.framework.redis.plus.anontation.RedisLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RLock rLock = null;
        RedisLock lock = method.getAnnotation(RedisLock.class);
        final String proxyName = StrUtil.join(pjp.getTarget().getClass().getName(), lock.delimiter(), method.getName());
        final String prefix = StrUtil.blankToDefault(lock.prefix(), proxyName);
        log.debug("proxyName - {} - prefix - {}", proxyName, prefix);
        if (StrUtil.isBlank(prefix)) {
            throw CheckedException.notFound("lock key prefix don't null...");
        }
        final String lockKey = redisKeyGenerator.generate(prefix, lock.delimiter(), pjp);
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            rLock = getLock(lockKey, lock.lockType());
            final boolean success = rLock.tryLock(lock.waitTime(), lock.expire(), lock.timeUnit());
            if (log.isDebugEnabled()) {
                log.debug("redis lock key is {} and status is {}", lockKey, success);
            }
            if (!success) {
                throw new RedisLockException(lock.message());
            }
            return pjp.proceed();
        } catch (InterruptedException e) {
            log.error("redis try lock interruptedException", e);
            throw new RedisLockException("线程中断" + e.getLocalizedMessage());
        } finally {
            // 判断是否需要自动释放锁
            if (rLock != null && lock.unlock()) {
                rLock.unlock();
                log.debug("redisson distributed release lock success with key:{}", lockKey);
            }
        }
    }

    /**
     * 解析spring EL表达式,无参数方法
     *
     * @param key    表达式
     * @param method 方法
     * @param args   方法参数
     * @param point  切面对象
     * @return String
     */
    private String parse(String key, Method method, Object[] args, ProceedingJoinPoint point) {
        String[] params = discoverer.getParameterNames(method);
        //指定 SPEL 表达式，并且有适配参数时
        if (!ObjectUtils.isEmpty(params) && StringUtils.isNotEmpty(key)) {
            EvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < params.length; i++) {
                context.setVariable(params[i], args[i]);
            }
            return parser.parseExpression(key).getValue(context, String.class);
        } else {
            return parseDefaultKey(key, method, point.getArgs(), point);
        }
    }

    /**
     * 生成key的分三部分，类名+方法名，参数,key
     * 不满足指定SPEL表达式并且有适配参数时，
     * 采用本方法生成最终redis的限流器key
     *
     * @param key    key
     * @param method method
     * @param args   args
     * @param point  point
     * @return String
     */
    private String parseDefaultKey(String key, Method method, Object[] args, ProceedingJoinPoint point) {
        //保证key有序
        Map<String, Object> keyMap = Maps.newLinkedHashMap();
        //放入target的名字
        keyMap.put("target", point.getTarget().getClass().toGenericString());
        //放入method的名字
        keyMap.put("method", method.getName());
        //把所有参数放进去
        Object[] params = point.getArgs();
        if (Objects.nonNull(params)) {
            for (int i = 0; i < params.length; i++) {
                keyMap.put("params-" + i, params[i]);
            }
        }
        //key表达式
        key = Objects.isNull(key) ? "" : "_" + key;
        final String jsonText = JSON.toJSONString(keyMap) + key;
        //使用MD5生成位移key
        return MD5.create().digestHex(jsonText);
    }


    /**
     * 获取指定类型锁
     *
     * @param key      key
     * @param lockType lockType
     * @return RLock
     */
    private RLock getLock(String key, RedisLock.LockType lockType) {
        switch (lockType) {
            case REENTRANT_LOCK -> {
                return redissonClient.getLock(key);
            }
            case FAIR_LOCK -> {
                return redissonClient.getFairLock(key);
            }
            case READ_LOCK -> {
                return redissonClient.getReadWriteLock(key).readLock();
            }
            case WRITE_LOCK -> {
                return redissonClient.getReadWriteLock(key).writeLock();
            }
            case RED_LOCK -> {
                return new RedissonRedLock(redissonClient.getLock(key));
            }
            case MULTI_LOCK -> {
                return new RedissonMultiLock(redissonClient.getLock(key));
            }
            default -> {
                log.error("do not support lock type:" + lockType.name());
                throw new RuntimeException("do not support lock type:" + lockType.name());
            }
        }
    }
}