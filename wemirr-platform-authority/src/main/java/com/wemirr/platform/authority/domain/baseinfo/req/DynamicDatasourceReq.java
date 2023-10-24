package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class DynamicDatasourceReq {

    @NotBlank(message = "连接池名称不能为空")
    @Schema(description = "连接池名称")
    private String poolName;
    @Schema(description = "数据库类型")
    @NotBlank(message = "数据库类型不能为空")
    private String dbType;

    private String database;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "HOST")
    @NotBlank(message = "host 不能为空")
    private String host;

    @NotBlank(message = "驱动类不能为空")
    @Schema(description = "驱动类型")
    private String driverClassName;

    @Schema(description = "是否禁用")
    private Boolean locked;

    /**
     * 描述
     */
    @Length(max = 300, message = "长度不能超过 {max} 个字符")
    private String description;
}
