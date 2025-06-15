package com.wemirr.platform.warmflow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "ProcessMarketPageResp")
public class ProcessMarketPageResp {

    @Schema(description = "ID")
    private String id;
    @Schema(description = "流程名称")
    private String processName;
    @Schema(description = "流程类别")
    private String category;
    @Schema(description = "部署名称")
    private String deployName;
    @Schema(description = "当前版本")
    private String version;
    @Schema(description = "流程定义Key")
    private String processDefKey;
    @Schema(description = "状态")
    private String status;


}
