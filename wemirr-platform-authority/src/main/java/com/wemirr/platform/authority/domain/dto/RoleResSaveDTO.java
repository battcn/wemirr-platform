package com.wemirr.platform.authority.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name = "RoleResSaveDTO", description = "角色的资源")
public class RoleResSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 资源ID
     */
    private Set<Long> resIds;

    /**
     * 角色id
     * #c_auth_role
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

}
