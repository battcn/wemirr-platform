package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author Levin
 * @since 2019-07-27
 */
@Data
@Schema(name = "UserRoleSaveReq", description = "角色分配 账号角色绑定")
public class UserRoleSaveReq {


    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @Schema(description = "用户ID")
    @NotEmpty(message = "分配用户不能为空")
    @Size(min = 1, message = "用户不能为空")
    private List<Long> userIdList;

}
