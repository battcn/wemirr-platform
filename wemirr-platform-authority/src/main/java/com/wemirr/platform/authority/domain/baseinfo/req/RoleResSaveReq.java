package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-27
 */
@Data
@Schema(name = "RoleResSaveReq", description = "角色的资源")
public class RoleResSaveReq implements Serializable {

    @NotEmpty(message = "资源不能为空")
    @Size(min = 1, message = "至少勾选 {min} 条数据")
    private Set<Long> resIds;

    /**
     * 角色id
     * #c_auth_role
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色不能为空")
    private Long roleId;

}
