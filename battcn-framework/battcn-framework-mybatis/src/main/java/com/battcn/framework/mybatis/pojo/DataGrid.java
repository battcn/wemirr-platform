package com.battcn.framework.mybatis.pojo;


/**
 * mybatis分页基础数据
 *
 * @author Levin
 */
public class DataGrid implements java.io.Serializable {

    private static final long serialVersionUID = -6760880318327799198L;

    private int pageSize = 20;
    private int pageNum = 1;
    private String sort;
    private String order = " desc";

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}