package com.wemirr.platform.authority.domain.tenant.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author levin
 */
@Data
public class TenantDatasourceResp {

    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "租户编码")
    private String tenantCode;
    @Schema(description = "租户名称")
    private String tenantName;
    /**
     * 数据库类型(只支持Mysql)
     */
    @Schema(description = "数据库类型(只支持Mysql)")
    private String dbType;

    @Schema(description = "连接驱动")
    private String driverClassName;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "HOST")
    private String host;

}
