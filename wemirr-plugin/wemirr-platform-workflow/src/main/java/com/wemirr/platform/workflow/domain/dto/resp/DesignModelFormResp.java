package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignModelFormResp {

    @Schema(defaultValue = "脚本")
    private String script;

    @NotEmpty(message = "表单字段不能为空")
    private ArrayList<Object> schemas;

    @NotEmpty(message = "模型ID")
    private Long modelId;

}
