package com.wemirr.platform.authority.domain.entity.baseinfo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.database.mybatis.auth.DataScopeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author battcn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role")
public class Role extends Entity<Long> {

    private Long tenantId;

    private String name;

    private String code;

    @TableField("`super`")
    private Boolean superRole;

    private String description;

    private Boolean readonly;

    private Boolean locked;
    /**
     * 数据权限类型
     * #DataScopeType
     */
    private DataScopeType scopeType;
}
