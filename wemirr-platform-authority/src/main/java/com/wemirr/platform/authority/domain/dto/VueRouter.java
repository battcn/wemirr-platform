package com.wemirr.platform.authority.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wemirr.framework.commons.entity.TreeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 构建 Vue路由
 *
 * @author Levin
 * @since 2019-10-20 15:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter extends TreeEntity<VueRouter, Long> {

    private static final long serialVersionUID = -3327478146308500708L;
    @Schema(description = "路径")
    private String path;
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "组件")
    private String component;
    @Schema(description = "重定向")
    private String redirect;
    @Schema(description = "元数据")
    private RouterMeta meta;
    @Schema(description = "是否隐藏")
    private Boolean hidden = false;
    @Schema(description = "总是显示")
    private Boolean alwaysShow = false;

    @Override
    @JsonIgnore
    public Long getId() {
        return this.id;
    }

    @Override
    @JsonIgnore
    public Long getParentId() {
        return this.parentId;
    }

//    @Schema(description = "子路由")
//    private List<VueRouter> children;
//
//    @Override
//    public List<VueRouter> getChildren() {
//        return this.children;
//    }

    public Boolean getAlwaysShow() {
        return getChildren() != null && !getChildren().isEmpty();
    }

    public String getComponent() {
        if (getChildren() != null && !getChildren().isEmpty()) {
            return "Layout";
        }
        return this.component;
    }
}
