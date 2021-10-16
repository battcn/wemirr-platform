package com.wemirr.framework.redis.plus;


import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 默认的Key生成器
 *
 * @author Levin
 */
public class DefaultRedisKeyGenerator implements RedisKeyGenerator {

    @Override
    public String generate(String prefix, String delimiter, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        //  默认解析方法里面带 RedisParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++) {
            final RedisParam annotation = parameters[i].getAnnotation(RedisParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(delimiter).append(args[i]);
        }
        for (Object arg : args) {
            final Field[] fields = ReflectUtil.getFields(arg.getClass());
            for (Field field : fields) {
                final RedisParam annotation = field.getAnnotation(RedisParam.class);
                if (annotation == null) {
                    continue;
                }
                final Object value = ReflectUtil.getFieldValue(arg, StringUtils.defaultIfBlank(annotation.name(), field.getName()));
                builder.append(delimiter).append(value);
            }
        }
        if (StrUtil.isBlank(builder)) {
            return prefix + builder;
        }
        final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            final Object object = args[i];
            final Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                final RedisParam annotation = field.getAnnotation(RedisParam.class);
                if (annotation == null) {
                    continue;
                }
                field.setAccessible(true);
                builder.append(delimiter).append(ReflectionUtils.getField(field, object));
            }
        }
        return prefix + builder;
    }
}