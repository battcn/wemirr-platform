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
@Table(name = "t_sys_role")
public class Role extends RecordEntity {


    private static final long serialVersionUID = -3235005077249555523L;

    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

}