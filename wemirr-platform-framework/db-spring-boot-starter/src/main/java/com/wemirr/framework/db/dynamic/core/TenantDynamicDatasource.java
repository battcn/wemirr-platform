package com.wemirr.framework.db.dynamic.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author levin
 */
@Data
public class TenantDynamicDatasource implements java.io.Serializable {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "租户编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "连接池名称")
    private String poolName;

    @Schema(description = "数据库类型(只支持Mysql)")
    private String dbType;

    @Schema(description = "驱动类")
    private String driverClassName;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "端口")
    private String host;

    /**
     * @see EventAction
     */
    @Schema(description = "指令类型")
    private Integer action;


}
