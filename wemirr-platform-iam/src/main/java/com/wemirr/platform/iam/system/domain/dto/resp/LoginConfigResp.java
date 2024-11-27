package com.wemirr.platform.iam.system.domain.dto.resp;

import com.wemirr.platform.iam.system.domain.enums.LoginConfigType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginConfigResp {

    @Schema(description = "登录类型")
    private LoginConfigType type;

    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "编码")
    private String tenantCode;
    @Schema(description = "名称")
    private String tenantName;
    @Schema(description = "LOGO")
    private String tenantLogo;
    @Schema(description = "WEB站点")
    private String webSite;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "创建时间")
    private Instant createdTime;


}
