package com.wemirr.framework.excel.handler.head;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Levin
 */
@Data
public class HeadMeta {

    /**
     * <p>
     * 自定义头部
     * </p>
     * 使用参考：<a href="https://www.yuque.com/easyexcel/doc/write#b4b9de00">...</a>
     */
    private List<List<String>> head;

    /**
     * 忽略头对应字段名称
     */
    private Set<String> ignoreFields;

}