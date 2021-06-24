package com.wemirr.framework.database.mybatis.conditions;


import cn.hutool.core.util.ReflectUtil;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.framework.database.mybatis.conditions.query.QueryWrap;
import com.wemirr.framework.database.mybatis.conditions.update.LbuWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Wrappers 工具类， 该方法的主要目的是为了 缩短代码长度
 *
 * @author Levin
 * @since 2019/06/14
 */
public class Wraps {

    private Wraps() {
    }

    /**
     * 获取 QueryWrap&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return QueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrap<T> q() {
        return new QueryWrap<>();
    }

    /**
     * 获取 QueryWrap&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return QueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrap<T> q(T entity) {
        return new QueryWrap<>(entity);
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LbqWrapper<T> lbQ() {
        return new LbqWrapper<>();
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LbqWrapper<T> lbQ(T entity) {
        return new LbqWrapper<>(entity);
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LbuWrapper<T> lbU() {
        return new LbuWrapper<>();
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LbuWrapper<T> lbU(T entity) {
        return new LbuWrapper<>(entity);
    }

    /**
     * 替换 实体对象中类型为String 类型的参数，并将% 和 _ 符号转义
     *
     * @param source 源对象
     * @return 最新源对象
     */
    public static <T> T replace(Object source) {
        if (source == null) {
            return null;
        }
        Class<?> srcClass = source.getClass();
        Field[] fields = ReflectUtil.getFields(srcClass);
        for (Field field : fields) {
            Object classValue = ReflectUtil.getFieldValue(source, field);
            if (classValue == null) {
                continue;
            }
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (!(classValue instanceof String)) {
                continue;
            }
            String srcValue = (String) classValue;
            if (srcValue.contains("%") || srcValue.contains("_")) {
                String tarValue = srcValue.replaceAll("%", "\\\\%");
                tarValue = tarValue.replaceAll("_", "\\\\_");
                ReflectUtil.setFieldValue(source, field, tarValue);
            }
        }
        return (T) source;
    }
}
