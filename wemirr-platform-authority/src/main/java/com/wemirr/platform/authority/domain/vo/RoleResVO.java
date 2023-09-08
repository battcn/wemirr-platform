package com.wemirr.platform.authority.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name= "RoleResVO", description = "角色的资源")
public class RoleResVO implements Serializable {

    

    /**
     * 菜单ID
     * #c_auth_menu
     */
    @Schema(description = "菜单ID")
    private List<Long> menuIdList;

    /**
     * 资源id
     * #c_auth_resource
     */
    @Schema(description = "资源ID")
    private List<Long> resourceIdList;

}
