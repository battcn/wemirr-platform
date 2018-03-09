package com.battcn.system.pojo.dto;


import com.battcn.system.pojo.po.Manager;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerDto extends Manager {

    private static final long serialVersionUID = 5314093955506271430L;

    private String roleName;
    private String roleCode;

}
