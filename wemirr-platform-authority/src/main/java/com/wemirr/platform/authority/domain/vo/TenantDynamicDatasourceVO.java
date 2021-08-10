package com.wemirr.platform.authority.domain.vo;

import lombok.Data;

/**
 * @author levin
 */
@Data
public class TenantDynamicDatasourceVO {

    private Long tenantId;
    private String code;
    private String name;
    /**
     * 连接池名称
     */
    private String poolName;
    /**
     * 数据库类型(只支持Mysql)
     */
    private String dbType;

    private String driverClassName;

    private String username;

    private String password;

    private String host;

}
