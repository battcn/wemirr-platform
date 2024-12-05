package com.wemirr.platform.iam.base.domain.dto.req;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class MessageChannelSaveReq {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态(0=禁用;1=启用)")
    private Boolean status;

    @Schema(description = "设置")
    private JSONObject setting;

    @Schema(description = "描述")
    private String description;

}
