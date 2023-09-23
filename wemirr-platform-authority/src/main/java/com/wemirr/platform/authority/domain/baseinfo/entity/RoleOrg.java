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
 * 角色组织关系
 * </p>
 *
 * @author Levin
 * @since 2020-10-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_org")
public class RoleOrg {


    @Schema(description = "角色ID")
    @TableField("role_id")
    private Long roleId;


    @Schema(description = "部门ID")
    @TableField("org_id")
    private Long orgId;

}
