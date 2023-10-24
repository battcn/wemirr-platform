package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
@Schema(name = "RoleUserReq", description = "角色的资源")
public class RoleUserReq {


    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotEmpty(message = "分配用户不能为空")
    @Size(min = 1, message = "至少勾选 {min} 条数据")
    private List<Long> userIdList;
    /**
     * 角色id
     * #c_auth_role
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

}
