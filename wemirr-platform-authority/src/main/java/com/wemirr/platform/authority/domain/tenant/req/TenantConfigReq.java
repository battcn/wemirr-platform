package com.wemirr.platform.authority.domain.tenant.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "TenantConfigReq")
public class TenantConfigReq {

    @NotNull(message = "动态数据源ID不能为空")
    @Schema(description = "动态数据源ID")
    private Long datasourceId;

    @NotBlank(message = "配置原因不能为空")
    @Schema(description = "配置原因")
    private String description;

    @Schema(description = "是否延迟加载")
    private boolean lazy;

}
