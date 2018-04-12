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
@Table(name = "t_sys_role_operate")
public class RoleOperate extends RecordEntity {
    /**
     *
     */
    private static final long serialVersionUID = 5504636135897681812L;

    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 操作表ID
     */
    @Id
    @Column(name = "operate_id")
    private Integer operateId;


    public RoleOperate() {
    }

    public RoleOperate(Integer roleId) {
        super();
        this.roleId = roleId;
    }

    public RoleOperate(Integer roleId, Integer operateId) {
        super();
        this.roleId = roleId;
        this.operateId = operateId;
    }


}