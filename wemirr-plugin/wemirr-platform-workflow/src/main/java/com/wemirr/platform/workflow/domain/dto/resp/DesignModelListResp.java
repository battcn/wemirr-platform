package com.wemirr.platform.workflow.domain.dto.resp;

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
@Schema(name = "DesignModelListResp")
public class DesignModelListResp {

    @Schema(description = "模型ID")
    private Long id;

    @Schema(description = "ICON")
    private String diagramIcon;

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "模型身份ID")
    private Long definitionId;


}
