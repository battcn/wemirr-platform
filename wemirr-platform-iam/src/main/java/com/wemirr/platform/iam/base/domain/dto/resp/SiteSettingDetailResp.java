package com.wemirr.platform.iam.base.domain.dto.resp;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(description = "平台站点详情")
public class SiteSettingDetailResp {

    @Schema(description = "租户编码")
    private String tenantCode;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "子标题")
    private String subTitle;

    @Schema(description = "LOGO")
    private String logo;

    @Schema(description = "扩展属性")
    private JSONObject ext;

}
