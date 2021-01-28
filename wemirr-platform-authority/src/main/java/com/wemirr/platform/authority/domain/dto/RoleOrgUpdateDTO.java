package com.wemirr.platform.authority.domain.dto;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 角色组织关系
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
@Schema(name= "RoleOrgUpdateDTO", description = "角色组织关系")
public class RoleOrgUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 角色ID
     * #c_auth_role
     */
    @Schema(description = "角色ID")
    private Long roleId;
    /**
     * 组织ID
     * #c_core_org
     */
    @Schema(description = "组织ID")
    private Long orgId;

}
