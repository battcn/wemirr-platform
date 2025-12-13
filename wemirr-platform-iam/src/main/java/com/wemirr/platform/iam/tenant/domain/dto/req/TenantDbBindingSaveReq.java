package com.wemirr.platform.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 *
 */
@Data
public class TenantDbBindingSaveReq {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "物理节点ID")
    private Long dbInstanceId;

    @Schema(description = "隔离策略: DATABASE, SCHEMA, COLUMN")
    private String strategy;

    @Schema(description = "运行时Schema名称")
    private String schemaName;

    @Schema(description = "是否为主数据源")
    private Boolean isPrimary;
}
