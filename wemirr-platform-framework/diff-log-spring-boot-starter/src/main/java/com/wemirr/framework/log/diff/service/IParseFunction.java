package com.wemirr.framework.log.diff.service;

/**
 * 自定义解析函数接口
 * <p>
 * 实现此接口可以在 DiffLog 中通过函数名调用，用于将ID等转换为可读文本
 *
 * @author Levin
 */
public interface IParseFunction {

    /**
     * 是否在执行之前处理
     *
     * @return 默认 false
     */
    default boolean executeBefore() {
        return false;
    }

    /**
     * 函数名称
     *
     * @return 函数名称
     */
    String functionName();

    /**
     * 执行解析函数
     *
     * @param value 函数入参，支持任意类型
     * @return 解析后的可读文本
     */
    String apply(Object value);
}
