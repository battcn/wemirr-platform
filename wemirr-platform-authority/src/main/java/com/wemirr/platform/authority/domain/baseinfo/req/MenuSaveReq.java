package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
@Schema(name = "MenuSaveReq", description = "菜单")
public class MenuSaveReq {


    /**
     * 菜单名称
     */
    @Schema(description = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 20, message = "名称长度不能超过20")
    private String label;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String description;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @Schema(description = "公共菜单")
    private Boolean global;
    /**
     * 路径
     */
    @Schema(description = "路径")
    @Length(max = 255, message = "路径长度不能超过 {max}")
    private String path;
    /**
     * 组件
     */
    @Schema(description = "组件")
    @Length(max = 255, message = "组件长度不能超过 {max}")
    private String component;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sequence;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @Length(max = 255, message = "菜单图标长度不能超过 {max}")
    private String icon;
    /**
     * 父级菜单id
     */
    @Schema(description = "父级菜单id")
    private Long parentId;

}
