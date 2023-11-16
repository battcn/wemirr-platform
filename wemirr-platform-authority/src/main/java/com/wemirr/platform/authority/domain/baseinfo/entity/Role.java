package com.wemirr.platform.authority.domain.baseinfo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author battcn
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends Entity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "超级角色")
    @TableField("`super`")
    private Boolean superRole;

    @Schema(description = "租户描述")
    private String description;

    @Schema(description = "是否只读")
    private Boolean readonly;

    @Schema(description = "是否被锁定")
    private Boolean locked;

    @Schema(description = "权限类型")
    private DataScopeType scopeType;
}
