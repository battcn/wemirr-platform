package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name= "UserRoleSaveDTO", description = "角色分配 账号角色绑定")
public class UserRoleSaveReq implements Serializable {

    

    /**
     * 角色ID
     * #c_auth_role
     */
    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    /**
     * 用户ID
     * #c_core_accou
     */
    @Schema(description = "用户ID")
    @Size(min = 1, message = "用户不能为空")
    private List<Long> userIdList;

}
