package com.wemirr.platform.authority.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class RoleDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "ID")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;

    /**
     * 是否内置角色
     */
    @Schema(description = "是否内置角色")
    private Boolean readonly;
    /**
     * 关联的组织id
     */
    @Schema(description = "权限")
    private RoleResVO authority;
}
