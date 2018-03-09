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
@Table(name = "t_sys_operate")
public class Operate extends RecordEntity {


    private static final long serialVersionUID = 5943708964724195548L;

    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 选项
     */
    private String op;

    /**
     * 名字
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序号
     */
    private Integer ordno;

    /**
     * 是否显示出来
     */
    @Column(name = "display")
    private Boolean display;

    public Operate() {
    }

    public Operate(Integer menuId) {
        super();
        this.menuId = menuId;
    }

}