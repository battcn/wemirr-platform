package com.wemirr.platform.suite.online.domain.resp;

import com.alibaba.fastjson2.JSONArray;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OnlineFormDesignerDetailResp")
public class OnlineFormDesignerDetailResp extends OnlineFormDesignerPageResp {

    @Schema(defaultValue = "脚本")
    private String formScript;

    @Schema(description = "表单配置")
    private JSONArray formSchemas;

    @Schema(description = "表单crud配置")
    private String formCrudConfig;

}
