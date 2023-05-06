package com.wemirr.platform.authority.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_res")
@Builder
public class RoleRes {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     * #c_auth_resource
     * #c_auth_menu
     */
    @Schema(description = "资源id")
    @NotNull(message = "资源id不能为空")
    @TableField("res_id")
    private Long resId;


    /**
     * 角色id
     * #t_sys_role
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    @TableField("role_id")
    private Long roleId;

}
