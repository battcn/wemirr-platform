package com.wemirr.framework.i18n.configuration;


import cn.hutool.core.util.ReflectUtil;
import com.wemirr.framework.commons.i18n.Language;
import com.wemirr.framework.i18n.annotation.I18nField;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 动态国际化处理
 *
 * @author Levin
 */
@Aspect
@RequiredArgsConstructor
public class I18nAspect {


    private final MessageSource messageSource;

    /***
     * 定义controller切入点拦截规则：拦截标记SysLog注解和指定包下的方法
     * execution(public * com.wemirr.base.controller.*.*(..)) 解释：
     * 第一个* 任意返回类型
     * 第三个* 类下的所有方法
     * ()中间的.. 任意参数
     * annotation(com.wemirr.framework.commons.annotation.log.SysLog) 解释：
     * 标记了@SysLog 注解的方法
     */
    @Pointcut("execution(public * com.wemirr..*.*(..)) && @annotation(com.wemirr.framework.i18n.annotation.I18nMethod)")
    public void i18nAspect() {

    }


    /**
     * 返回通知
     *
     * @param ret ret
     */
    @AfterReturning(returning = "obj", pointcut = "i18nAspect()")
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        parse(obj);
    }

    private void parseList(Collection<?> list) {
        list.forEach(this::parse);
    }

    /**
     * 遍历字段，解析出那些字段上标记了指定注解的字段
     *
     * @param obj 对象
     */
    private void parse(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Collection<?> list) {
            parseList(list);
            return;
        }
        //解析方法上的注解，计算出obj对象中所有需要查询的数据
        Field[] fields = ReflectUtil.getFields(obj.getClass());
        for (Field field : fields) {
            I18nField annotation = field.getAnnotation(I18nField.class);
            if (annotation == null) {
                continue;
            }
            final Object fieldValue = ReflectUtil.getFieldValue(obj, field);
            if (fieldValue == null) {
                continue;
            }
            if (fieldValue instanceof Language item) {
                ReflectUtil.setFieldValue(obj, annotation.target(), getMessage(item.getLanguage(), fieldValue));
            } else {
                ReflectUtil.setFieldValue(obj, annotation.target(), getMessage(annotation.code(), fieldValue));
            }
        }
    }

    public String getMessage(String code, Object... value) {
        return messageSource.getMessage(code, value, code, LocaleContextHolder.getLocale());
    }

}

