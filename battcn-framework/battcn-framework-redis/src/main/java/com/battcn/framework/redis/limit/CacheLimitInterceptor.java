package com.battcn.framework.redis.limit;

import com.battcn.framework.redis.CacheKeyGenerator;
import com.battcn.framework.redis.annotation.CacheLimit;
import com.battcn.framework.redis.constant.RedisConstant;
import com.battcn.framework.redis.exception.CacheLimitException;
import com.battcn.framework.redis.exception.RedisException;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Levin
 * @since 2018/2/5 0005
 */

@Aspect
@Configuration
@ConditionalOnProperty(
        prefix = "spring.redis.battcn.limit",
        name = "enabled",
        havingValue = "true"
)
public class CacheLimitInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CacheLimitInterceptor.class);

    private final CacheKeyGenerator limitKeyGenerator;
    private final RedisTemplate<String, String> limitRedisTemplate;

    @Autowired
    public CacheLimitInterceptor(@Qualifier(RedisConstant.LIMIT_TEMPLATE_NAME) RedisTemplate<String, String> limitRedisTemplate, @Qualifier(RedisConstant.LIMIT_KEY_GENERATOR) CacheKeyGenerator limitKeyGenerator) {
        this.limitRedisTemplate = limitRedisTemplate;
        this.limitKeyGenerator = limitKeyGenerator;
    }


    @Around("execution(public * *(..)) && @annotation(com.battcn.framework.redis.annotation.CacheLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLimit limitAnnotation = method.getAnnotation(CacheLimit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        String key;
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitKeyGenerator.getLockKey(pjp);
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix(), key));
        String luaScript = this.buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = this.limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        logger.info("Access try count is {} for name={} and key = {}", count, name, key);
        if (count.intValue() <= limitCount) {
            return pjp.proceed();
        } else {
            throw new CacheLimitException("You have been dragged into the blacklist");
        }
    }

    private String buildLuaScript() {
        // 调用不超过最大值，则直接返回
        // 执行计算器自加
        // 从第一次调用开始限流，设置对应键值的过期
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

    private static final String UNKNOWN = "unknown";

    private static String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}