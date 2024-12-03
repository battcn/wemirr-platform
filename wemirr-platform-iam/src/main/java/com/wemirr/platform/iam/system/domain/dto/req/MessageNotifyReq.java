package com.wemirr.platform.iam.system.domain.dto.req;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class MessageNotifyReq {

    @Schema(description = "模板编码")
    private String code;

    @Schema(description = "类型")
    private Integer type;

    @NotEmpty(message = "订阅者不能为空")
    @Schema(description = "订阅人")
    private List<Long> subscriberIdList;

    @Schema(description = "变量")
    private JSONObject variables;
}
