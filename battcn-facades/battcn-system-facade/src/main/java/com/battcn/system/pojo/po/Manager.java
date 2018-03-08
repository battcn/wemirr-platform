package com.battcn.system.pojo.po;


import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_sys_manager")
public class Manager extends RecordEntity {

    private static final long serialVersionUID = -3467360899069048896L;

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
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    @Column(name = "role_id")
    private Byte roleId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 验证凭证
     */
    private String credential;

    /**
     * 是否启用 0=禁用 1=启用
     */
    private Boolean locked;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 类型
     */
    private String type;

    /**
     * 头像
     */
    private String photo;

    /**
     * 登陆IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    public Manager() {
    }

    public Manager(String name) {
        super();
        this.name = name;
    }

}