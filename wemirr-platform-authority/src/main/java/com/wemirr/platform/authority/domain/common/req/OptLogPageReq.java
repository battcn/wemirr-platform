package com.wemirr.platform.authority.domain.common.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OptLogPageReq")
public class OptLogPageReq extends PageRequest {

    @Schema(description = "HTTP 请求方式")
    private String httpMethod;

    @Schema(description = "请求平台")
    private String platform;

    @Schema(description = "日志状态")
    private Boolean status;

}
