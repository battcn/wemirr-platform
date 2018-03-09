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
@Table(name = "t_sys_menu")
public class Menu extends RecordEntity {


    private static final long serialVersionUID = 5460695457049484647L;

    /**
     * 自增ID
     */
    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图标
     */
    private String icon;

    /**
     * 渠道
     */
    private String channel;

    private String param;

    /**
     * 是否启用 0=禁用 1=启用
     */
    private Boolean locked;

    /**
     * 父级编号
     */
    @Column(name = "pater_id")
    private Integer paterId;

    /**
     * 排序编号
     */
    private Integer ordno;

    /**
     * 级别
     */
    @Column(name = "n_level")
    private Integer nLevel;

    /**
     * tree所需
     */
    private String scort;


    public Menu() {
    }

    public Menu(Boolean locked) {
        super();
        this.locked = locked;
    }


}