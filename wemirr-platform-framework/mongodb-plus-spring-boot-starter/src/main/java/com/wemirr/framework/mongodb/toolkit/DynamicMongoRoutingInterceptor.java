package com.wemirr.framework.mongodb.toolkit;

import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.lang.reflect.Field;

/**
 * @author levin
 */
@Slf4j
@Aspect
public class DynamicMongoRoutingInterceptor {


    @Around("execution(public * *(..)) && @annotation(com.wemirr.framework.mongodb.annotation.DynamicMongo)")
    public Object route(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Object o = joinPoint.getTarget();
        Field[] fields = o.getClass().getDeclaredFields();
        DynamicMongoTemplate mongoTemplate = null;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldObject = field.get(o);
                Class<?> fieldclass = fieldObject.getClass();
                if (fieldclass != MongoTemplate.class && fieldclass != DynamicMongoTemplate.class) {
                    continue;
                }
                // 获取项目对应的 MongoDatabaseFactory
                MongoDatabaseFactory factory = DynamicMongoFactoryHolder.poll();
                // 如果第一次，赋值成自定义的MongoTemplate子类
                if (fieldclass == MongoTemplate.class) {
                    mongoTemplate = new DynamicMongoTemplate(factory, SpringUtil.getBean(MongoConverter.class));
                } else {
                    mongoTemplate = (DynamicMongoTemplate) fieldObject;
                }
                //设置 MongoDatabaseFactory
                mongoTemplate.push(factory);
                //重新赋值
                field.set(o, mongoTemplate);
                break;
            }
        } catch (CheckedException e) {
            log.error("动态 MongoDB AOP 异常 - {}", e.getLocalizedMessage());
        } catch (Exception e) {
            log.error("动态 MongoDB 系统异常", e);
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            log.error("AOP方法调用 异常", t);
            throw new RuntimeException(t);
        } finally {
            //清理ThreadLocal的变量
            if (mongoTemplate != null) {
                mongoTemplate.clear();
            }
        }
        return result;
    }
}