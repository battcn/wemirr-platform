package com.battcn.bookstore.consumer.pojo;

import com.battcn.framework.redis.annotation.LockParam;
import io.swagger.annotations.ApiModel;

/**
 * Vo
 *
 * @author Levin
 * @since 2018/3/22 0022
 */
@ApiModel
public class PayVo {

    @LockParam
    private Integer payNo;
    @LockParam
    private String bookNo;

    public Integer getPayNo() {
        return payNo;
    }

    public void setPayNo(Integer payNo) {
        this.payNo = payNo;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }
}
