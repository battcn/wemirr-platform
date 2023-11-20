package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.authority.domain.baseinfo.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author Levin
 * @since 2019-11-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_resource")
@Schema(name = "Resource", description = "资源")
public class Resource extends SuperEntity<Long> {

    

    /**
     * 资源编码
     * 规则：
     * 链接：
     * 数据列：
     * 按钮：
     */
    @Schema(description = "权限编码")
    @TableField(value = "permission", condition = LIKE)
    private String permission;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "`label`", condition = LIKE)
    private String label;

    @Schema(description = "是否只读")
    private Boolean readonly;

    /**
     * 菜单ID
     * #c_auth_menu
     */
    @Schema(description = "菜单ID")
    private Long parentId;

    /**
     * '资源类型（1=按钮，0=菜单）'
     */
    @TableField("`type`")
    @Schema(description = "资源类型")
    private ResourceType type;

    @TableField("`sequence`")
    @Schema(description = "排序")
    private Integer sequence;

    @TableField("`style`")
    @Schema(description = "样式")
    private String style;

    @TableField("`icon`")
    @Schema(description = "图标")
    private String icon;

    @TableField("`path`")
    @Schema(description = "路径")
    private String path;

    @TableField("`component`")
    @Schema(description = "组件")
    private String component;

    @TableField(value = "`model`", exist = false)
    private String model;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @TableField("`global`")
    @Schema(description = "是否公开")
    private Boolean global;
    @TableField("`status`")
    @Schema(description = "状态")
    private Boolean status;

}
