package com.wemirr.platform.authority.domain.baseinfo.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class DynamicDatasourceReq {

    @NotBlank(message = "连接池名称不能为空")
    private String poolName;
    @NotBlank(message = "数据库类型不能为空")
    private String dbType;
    private String database;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "host 不能为空")
    private String host;
    @NotBlank(message = "驱动类不能为空")
    private String driverClassName;
    /**
     * 是否禁用
     */
    private Boolean locked;

    /**
     * 描述
     */
    @Length(max = 300, message = "长度不能超过 {max} 个字符")
    private String description;
}
