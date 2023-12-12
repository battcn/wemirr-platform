package com.wemirr.platform.authority.domain.common.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "LoginLogPageReq")
public class LoginLogPageReq extends PageRequest {

    @Schema(description = "账号")
    private String principal;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "请求平台")
    private String platform;


}
