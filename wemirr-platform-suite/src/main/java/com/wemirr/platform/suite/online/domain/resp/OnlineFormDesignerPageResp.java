package com.wemirr.platform.suite.online.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class OnlineFormDesignerPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "定义KEY")
    private String definitionKey;

    @Schema(description = "编号")
    private String code;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "备注")
    private String description;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;
}
