package com.wemirr.framework.log.diff.support.parse;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.framework.log.diff.service.impl.DiffParseFunction;

/**
 * DiffLog SpEL 内置函数。
 *
 * @author Levin
 */
public class DiffLogSpelFunctions {

    private DiffLogSpelFunctions() {
        throw new IllegalStateException("Utility class");
    }

    public static Object toBean(Object id, Object source) {
        Object oldObj = DiffLogContext.getMethodOrGlobal(DiffParseFunction.OLD_OBJECT);
        if (oldObj == null) {
            throw new IllegalStateException("oldObj is null, please configure DiffLog.oldObj or use toBeanByClass(...)");
        }
        return BeanUtilPlus.toBean(id, source, oldObj.getClass());
    }

    public static <T> T toBeanByClass(Object id, Object source, Class<T> clazz) {
        return BeanUtilPlus.toBean(id, source, clazz);
    }

    public static Object toBeanByClassName(Object id, Object source, String clazzName) {
        if (StrUtil.isBlank(clazzName)) {
            throw new IllegalArgumentException("clazzName is blank");
        }
        try {
            Class<?> clazz = Class.forName(clazzName);
            return BeanUtilPlus.toBean(id, source, clazz);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found: " + clazzName, e);
        }
    }
}
