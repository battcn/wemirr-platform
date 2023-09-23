package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    

    @Schema(description = "资源ID")
    @TableField("res_id")
    private Long resId;


    /**
     * 角色id
     * #t_sys_role
     */
    @Schema(description = "角色ID")
    @TableField("role_id")
    private Long roleId;

}
