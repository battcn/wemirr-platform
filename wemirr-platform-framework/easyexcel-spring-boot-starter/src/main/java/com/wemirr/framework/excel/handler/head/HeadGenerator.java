package com.wemirr.framework.excel.handler.head;


/**
 * Excel头生成器
 *
 * @author Levin
 */
public interface HeadGenerator {

    /**
     * <p>
     * 自定义头部
     * </p>
     * 参考：<a href="https://www.yuque.com/easyexcel/doc/write#b4b9de00">...</a>
     *
     * @param clazz 当前sheet的数据类型
     * @return List<List < String>> Head头信息
     */
    HeadMeta head(Class<?> clazz);

}