package com.wemirr.platform.authority.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class RoleResMenuMapperVo {
    @Schema(description = "资源ID")
    private Long id;
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "资源类型（1=菜单;2=按钮）")
    private Integer type;

}
