package com.battcn.system.pojo.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Levin
 */
public class PermissionDto {

    private Integer id;
    private Integer menuId;
    private String name;
    private String menuName;
    private Integer paterId;
    private String op;
    private List<PermissionDto> children = Lists.newArrayList();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPaterId() {
        return paterId;
    }

    public void setPaterId(Integer paterId) {
        this.paterId = paterId;
    }

    public List<PermissionDto> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDto> children) {
        this.children = children;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
