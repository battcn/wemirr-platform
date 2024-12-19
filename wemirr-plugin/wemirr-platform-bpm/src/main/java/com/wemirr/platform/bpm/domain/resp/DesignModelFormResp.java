package com.wemirr.platform.bpm.domain.resp;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DesignModelFormResp")
public class DesignModelFormResp {

    private String tableId;
    private JSONObject formConfig;
    private JSONArray formFields;
    private Long modelId;

}
