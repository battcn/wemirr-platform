package com.wemirr.framework.mongodb.toolkit;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.mongodb.annotation.DynamicMongo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 动态MongoDB数据源路由拦截器
 * <p>支持根据注解配置或租户上下文自动切换数据源</p>
 *
 * @author levin
 */
@Slf4j
@Aspect
public class DynamicMongoRoutingInterceptor {

    @Around("execution(public * *(..)) && @annotation(com.wemirr.framework.mongodb.annotation.DynamicMongo)")
    public Object route(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicMongoTemplate mongoTemplate = null;
        try {
            // 获取注解配置
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            DynamicMongo annotation = method.getAnnotation(DynamicMongo.class);

            // 获取数据源Factory
            MongoDatabaseFactory factory = resolveFactory(annotation);
            if (factory == null) {
                log.debug("No MongoDB factory resolved, using default");
                return joinPoint.proceed();
            }

            // 切换MongoTemplate的数据源
            mongoTemplate = switchDataSource(joinPoint.getTarget(), factory);
            return joinPoint.proceed();
        } catch (CheckedException e) {
            log.error("动态MongoDB切换异常: {}", e.getLocalizedMessage());
            throw e;
        } catch (Throwable t) {
            log.error("动态MongoDB执行异常", t);
            throw t;
        } finally {
            if (mongoTemplate != null) {
                mongoTemplate.clear();
            }
        }
    }

    /**
     * 解析数据源Factory
     */
    private MongoDatabaseFactory resolveFactory(DynamicMongo annotation) {
        // 如果指定了数据源且强制使用
        if (annotation != null && StrUtil.isNotBlank(annotation.value())) {
            return DynamicMongoFactoryHolder.get(annotation.value());
        }
        // 否则根据租户上下文获取
        return DynamicMongoFactoryHolder.poll();
    }

    /**
     * 切换数据源
     */
    private DynamicMongoTemplate switchDataSource(Object target, MongoDatabaseFactory factory) throws IllegalAccessException {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldObject = field.get(target);
            if (fieldObject == null) {
                continue;
            }
            Class<?> fieldClass = fieldObject.getClass();
            if (fieldClass != MongoTemplate.class && fieldClass != DynamicMongoTemplate.class) {
                continue;
            }

            DynamicMongoTemplate mongoTemplate;
            if (fieldClass == MongoTemplate.class) {
                mongoTemplate = new DynamicMongoTemplate(factory, SpringUtil.getBean(MongoConverter.class));
            } else {
                mongoTemplate = (DynamicMongoTemplate) fieldObject;
            }
            mongoTemplate.push(factory);
            field.set(target, mongoTemplate);
            return mongoTemplate;
        }
        return null;
    }
}