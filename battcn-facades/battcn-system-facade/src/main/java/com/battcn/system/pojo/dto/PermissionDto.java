package com.battcn.system.pojo.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class PermissionDto implements java.io.Serializable {

    private static final long serialVersionUID = 6340927911117184730L;

    private Integer id;
    private Integer menuId;
    private String name;
    private String menuName;
    private Integer parentId;
    private String option;
    private List<PermissionDto> children = Lists.newArrayList();

}
