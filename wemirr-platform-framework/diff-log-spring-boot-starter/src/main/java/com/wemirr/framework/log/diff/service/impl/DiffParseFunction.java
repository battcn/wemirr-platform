package com.wemirr.framework.log.diff.service.impl;

import com.wemirr.framework.log.diff.IDiffItemsToLogContentService;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.framework.log.diff.domain.FieldChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 差异解析函数，用于比较新旧对象并生成差异内容
 *
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
        Object[] normalized = normalizeObjects(source, target);
        if (normalized == null) {
            return "";
        }
        return diffItemsToLogContentService.toLogContent(normalized[0], normalized[1]);
    }

    public String diff(Object newObj) {
        Object oldObj = DiffLogContext.getMethodOrGlobal(OLD_OBJECT);
        return diff(oldObj, newObj);
    }

    /**
     * 获取字段变更列表
     */
    public List<FieldChange> diffToFieldChanges(Object source, Object target) {
        Object[] normalized = normalizeObjects(source, target);
        if (normalized == null) {
            return Collections.emptyList();
        }
        return diffItemsToLogContentService.toFieldChanges(normalized[0], normalized[1]);
    }

    /**
     * 获取字段变更列表（从上下文获取旧对象）
     */
    public List<FieldChange> diffToFieldChanges(Object newObj) {
        Object oldObj = DiffLogContext.getMethodOrGlobal(OLD_OBJECT);
        return diffToFieldChanges(oldObj, newObj);
    }

    /**
     * 标准化对象，处理 null 情况
     */
    private Object[] normalizeObjects(Object source, Object target) {
        if (source == null && target == null) {
            return null;
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
            return null;
        }
        return new Object[]{source, target};
    }

    public void setDiffItemsToLogContentService(IDiffItemsToLogContentService diffItemsToLogContentService) {
        this.diffItemsToLogContentService = diffItemsToLogContentService;
    }
}
