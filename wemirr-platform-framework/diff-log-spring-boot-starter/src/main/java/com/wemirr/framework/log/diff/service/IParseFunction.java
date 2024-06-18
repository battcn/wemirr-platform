package com.wemirr.framework.log.diff.service;

/**
 * 解析
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
     * @param value 函数入参
     * @return 文案
     * @since 1.1.0 参数从String 修改为Object类型，可以处理更多的场景，可以通过SpEL表达式传递对象了
     */
    String apply(Object value);
}
