package com.wemirr.platform.authority.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.authority.domain.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_resource")
@Schema(name = "Resource", description = "资源")
public class Resource extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

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
    private ResourceType type;
    @TableField("`sequence`")
    private Integer sequence;
    @TableField("`style`")
    private String style;
    private String icon;
    @TableField("`path`")
    private String path;
    @TableField("`component`")
    private String component;
    @TableField(value = "`model`", exist = false)
    private String model;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @TableField("`global`")
    private Boolean global;
    @TableField("`status`")
    private Boolean status;

}
