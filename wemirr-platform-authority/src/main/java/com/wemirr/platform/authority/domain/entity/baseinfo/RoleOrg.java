package com.wemirr.platform.authority.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

    

    /**
     * 角色ID
     * #c_auth_role
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 部门ID
     * #c_core_org
     */
    @TableField("org_id")
    private Long orgId;

}
