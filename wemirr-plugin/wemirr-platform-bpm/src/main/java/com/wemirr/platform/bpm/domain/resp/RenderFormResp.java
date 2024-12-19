package com.wemirr.platform.bpm.domain.resp;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程表单渲染返回参数
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RenderFormResp {

    @Schema(description = "表单渲染数据")
    private DesignModelFormResp formDesign;

    @Schema(description = "表单数据")
    private JSONObject dataJson;

}
