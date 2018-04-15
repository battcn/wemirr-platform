package com.battcn.framework.commons.entity;

import java.util.List;

/**
 * 自定义分页对象
 *
 * @author Levin
 * @since 2018/4/15 0015
 */
public class PageResult<T> implements java.io.Serializable {

    private static final long serialVersionUID = 3423642603195626288L;

    private Long total;
    private List<T> lists;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public PageResult() {

    }

    public PageResult(Long total, List<T> lists) {
        this.total = total;
        this.lists = lists;
    }

    public static <T> PageResult<T> of(Long total, List<T> lists) {
        return new PageResult<>(total, lists);
    }
}
