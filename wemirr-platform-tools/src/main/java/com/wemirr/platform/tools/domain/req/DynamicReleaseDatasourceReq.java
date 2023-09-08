package com.wemirr.platform.tools.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class DynamicReleaseDatasourceReq {

    @NotBlank(message = "数据库名称不能为空")
    private String database;
    @NotBlank(message = "账号名不能为空")
    private String username;
    private String password;
    @NotBlank(message = "服务器IP不能为空")
    private String host;
    private Integer port;
    private String description;

}
