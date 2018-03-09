package com.battcn.system.pojo.po;


import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_sys_log")
public class Log extends RecordEntity {

    private static final long serialVersionUID = 8674280943865600110L;

    /**
     * 自增ID
     */
    @Id
    private Integer id;
    /**
     * 账号
     */
    private String account;

    /**
     * 标题
     */
    private String title;

    /**
     * 执行的方法
     */
    private String methods;

    /**
     * 消息
     */
    private String message;

    /**
     * IP
     */
    private String ip;

    /**
     * 执行时间
     */
    private Short duration;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String params;

    public Log() {
    }

    public Log(String title, String methods, String message) {
        super();
        this.title = title;
        this.methods = methods;
        this.message = message;
    }


}