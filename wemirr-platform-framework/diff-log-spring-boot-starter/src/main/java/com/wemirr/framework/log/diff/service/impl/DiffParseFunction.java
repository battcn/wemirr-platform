package com.wemirr.framework.log.diff.service.impl;

import com.wemirr.framework.log.diff.IDiffItemsToLogContentService;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author muzhantong
 */
@Slf4j
public class DiffParseFunction {
    public static final String DIFF_FUNCTION_NAME = "_DIFF";
    public static final String OLD_OBJECT = "_oldObj";
    public static final String NEW_OBJECT = "_newObj";

    private IDiffItemsToLogContentService diffItemsToLogContentService;

    public String functionName() {
        return DIFF_FUNCTION_NAME;
    }

    public String diff(Object source, Object target) {
        if (source == null && target == null) {
            return "";
        }
        if (source == null || target == null) {
            try {
                Class<?> clazz = source == null ? target.getClass() : source.getClass();
                source = source == null ? clazz.getDeclaredConstructor().newInstance() : source;
                target = target == null ? clazz.getDeclaredConstructor().newInstance() : target;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        if (!Objects.equals(AopUtils.getTargetClass(source.getClass()), AopUtils.getTargetClass(target.getClass()))) {
            log.error("diff的两个对象类型不同, source.class={}, target.class={}", source.getClass(), target.getClass());
            return "";
        }
        return diffItemsToLogContentService.toLogContent(source, target);
    }

    public String diff(Object newObj) {
        Object oldObj = DiffLogContext.getMethodOrGlobal(OLD_OBJECT);
        return diff(oldObj, newObj);
    }

    public void setDiffItemsToLogContentService(IDiffItemsToLogContentService diffItemsToLogContentService) {
        this.diffItemsToLogContentService = diffItemsToLogContentService;
    }
}
