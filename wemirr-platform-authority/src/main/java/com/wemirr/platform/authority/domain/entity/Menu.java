package com.wemirr.platform.authority.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.TreeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 菜单
 * </p>
 *
 * @author Levin
 * @since 2019-11-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@Schema(name= "Menu", description = "菜单")
public class Menu extends TreeEntity<Menu, Long> {

    private static final long serialVersionUID = 1L;

    protected String label;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @TableField(value = "description", condition = LIKE)
    private String description;

    private Boolean locked;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @Schema(description = "公共菜单")
    @TableField("`global`")
    private Boolean global;

    /**
     * 路径
     */
    @Schema(description = "路径")
    @TableField(value = "`path`", condition = LIKE)
    private String path;

    /**
     * 组件
     */
    @Schema(description = "组件")
    @TableField(value = "`component`", condition = LIKE)
    private String component;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @TableField(value = "icon", condition = LIKE)
    private String icon;


}
