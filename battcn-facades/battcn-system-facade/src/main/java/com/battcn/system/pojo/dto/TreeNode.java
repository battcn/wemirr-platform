package com.battcn.system.pojo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构
 *
 * @author Levin
 * @since 2018-01-10
 */
@Data
public class TreeNode implements java.io.Serializable {

    private static final long serialVersionUID = 7167292131071749703L;

    private Integer id;
    private Integer pid;
    private String text;
    private String icon;
    private Object attributes;
    private List<TreeNode> children = new ArrayList<>();
    public TreeNode() {
    }

    public TreeNode(Integer id, String text, String icon) {
        super();
        this.id = id;
        this.text = text;
        this.icon = icon;
    }

    public TreeNode(Integer id, String text, String icon, List<TreeNode> children) {
        super();
        this.id = id;
        this.text = text;
        this.icon = icon;
        this.children = children;
    }

    public TreeNode(Integer id, Integer pid, String text, Object attributes, List<TreeNode> children) {
        super();
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.attributes = attributes;
        this.children = children;
    }
}