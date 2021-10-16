package com.wemirr.framework.redis.plus.interceptor;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.redis.plus.RedisLimitHelper;
import com.wemirr.framework.redis.plus.anontation.RedisLimit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * redisson分布式限流器切面处理
 *
 * @author 付为地
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLimitInterceptor {

    private final RedisLimitHelper redisLimitHelper;

    private final ExpressionParser parser = new SpelExpressionParser();
    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

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
        String key = null;
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            //注解别名时，需要用此种方法获取
            RedisLimit rateLimiter = AnnotationUtils.getAnnotation(method, RedisLimit.class);
            if (!ObjectUtils.isEmpty(rateLimiter)) {
                key = parse(rateLimiter.prefix(), rateLimiter.useArgs(), method, point.getArgs(), point);
                final boolean tryAcquire = redisLimitHelper.tryAcquire(key, rateLimiter.limit(), rateLimiter.timeout(), rateLimiter.unit(), rateLimiter.type(), rateLimiter.retryTime());
                if (tryAcquire) {
                    log.debug("redisson rate limiter obtained the token success with key:{}", key);
                    return point.proceed();
                } else {
                    log.error("redisson rate limiter block the request with key:{}", key);
                    throw new RuntimeException("redisson rate limiter block the request ");
                }
            } else {
                return point.proceed();
            }
        } catch (InterruptedException e) {
            log.error("redisson rate limiter obtained the token error with key:{},error:{}", key, e);
        }
        return null;
    }

    /**
     * 解析spring EL表达式,无参数方法
     *
     * @param key     表达式
     * @param method  方法
     * @param args    方法参数
     * @param point   切面对象
     * @param useArgs 限流是否到接口参数级别
     * @return parse return
     */
    private String parse(String key, boolean useArgs, Method method, Object[] args, ProceedingJoinPoint point) {
        if (!useArgs) {
            return parseDefaultKey(key, false, method, point);
        }
        String[] params = discoverer.getParameterNames(method);
        //指定spel表达式，并且有适配参数时
        if (Objects.nonNull(params) && Objects.nonNull(key)) {
            EvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < params.length; i++) {
                context.setVariable(params[i], args[i]);
            }
            return parser.parseExpression(key).getValue(context, String.class);
        } else {
            return parseDefaultKey(key, true, method, point);
        }
    }

    /**
     * 生成key的分三部分，类名+方法名，参数,key
     * 不满足指定SPEL表达式并且有适配参数时，
     * 采用本方法生成最终redis的限流器key
     *
     * @param key    key
     * @param method method
     * @param point  point
     * @return parseDefaultKey
     */
    private String parseDefaultKey(String key, boolean useArgs, Method method, ProceedingJoinPoint point) {
        //保证key有序
        Map<String, Object> keyMap = new LinkedHashMap<>();
        //放入target的名字
        keyMap.put("target", point.getTarget().getClass().toGenericString());
        //放入method的名字
        keyMap.put("method", method.getName());
        Object[] params = point.getArgs();
        //把所有参数放进去
        if (useArgs && Objects.nonNull(params)) {
            for (int i = 0; i < params.length; i++) {
                keyMap.put("params-" + i, params[i]);
            }
        }
        //key表达式
        key = StringUtils.isEmpty(key) ? "" : "_" + key;
        //使用MD5生成位移key
        return MD5.create().digestHex(JSONObject.toJSON(keyMap).toString() + key).toUpperCase();
    }


}
