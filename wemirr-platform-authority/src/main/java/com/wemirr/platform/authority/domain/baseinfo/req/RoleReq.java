package com.wemirr.platform.authority.domain.baseinfo.req;

import com.wemirr.framework.commons.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author Levin
 * @since 2019-11-11
 */
@Data
@Schema(name = "RoleReq", description = "角色")
public class RoleReq {


    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能超过30")
    private String name;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    @Length(max = 20, message = "角色编码长度不能超过20")
    private String code;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 100, message = "描述长度不能超过100")
    private String description;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean locked;

    @Schema(description = "内置角色")
    private Boolean readonly;
    /**
     * 数据权限类型
     */
    @Schema(description = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private DataScopeType scopeType;

    /**
     * 关联的组织id
     */
    @Schema(description = "关联的组织id")
    private List<Long> orgList;
}
