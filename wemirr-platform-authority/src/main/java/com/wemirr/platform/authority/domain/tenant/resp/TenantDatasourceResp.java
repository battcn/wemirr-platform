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
    @Schema(description = "编码")
    private String code;
    @Schema(description = "名称")
    private String name;
    /**
     * 连接池名称
     */
    @Schema(description = "连接池名称")
    private String poolName;
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

    @Schema(description = "host")
    private String host;

}
