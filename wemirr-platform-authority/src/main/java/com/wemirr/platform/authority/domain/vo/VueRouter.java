package com.wemirr.platform.authority.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 构建 Vue路由
 *
 * @author Levin
 * @since 2019-10-20 15:17
 */
@Data
public class VueRouter {

    private static final long serialVersionUID = -3327478146308500708L;

    private Long id;
    private Long parentId;
    @Schema(description = "路径")
    private String path;
    @Schema(description = "按钮名称")
    private String name;
    @Schema(description = "菜单名称")
    private String label;
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "组件")
    private String component;
    @Schema(description = "重定向")
    private String redirect;
    @Schema(description = "元数据")
    private RouterMeta meta;

    private String model;

    private String permission;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sequence;
    @Schema(description = "类型（1=菜单;2=按钮;3=路由;5=一键发布模板）")
    private Integer type;

    private Boolean global;
    private Boolean status;
}
