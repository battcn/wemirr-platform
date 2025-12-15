package com.wemirr.framework.log.diff.service;

/**
 * 函数服务接口，用于管理和调用自定义解析函数
 *
 * @author Levin
 */
public interface IFunctionService {

    /**
     * 调用指定名称的解析函数
     *
     * @param functionName 函数名称
     * @param value        函数入参
     * @return 解析后的文本
     */
    String apply(String functionName, Object value);

    /**
     * 判断函数是否需要在方法执行前调用
     *
     * @param functionName 函数名称
     * @return true 表示需要在执行前调用
     */
    boolean beforeFunction(String functionName);
}
