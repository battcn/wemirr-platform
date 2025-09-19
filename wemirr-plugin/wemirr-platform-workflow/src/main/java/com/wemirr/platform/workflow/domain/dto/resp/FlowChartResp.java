package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 流程图 返回体
 *
 * @author battcn
 * @since 2025/5/22
 **/
@Data
@Builder
public class FlowChartResp {

    @Schema(description = "base64 的字符串")
    public String chartJson;
}
