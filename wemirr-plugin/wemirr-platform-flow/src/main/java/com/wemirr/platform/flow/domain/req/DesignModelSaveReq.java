package com.wemirr.platform.flow.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * 流程模型管理添加或修改Request
 *
 * @author Levin
 */
@Data
@Schema(name = "DesignModelSaveReq")
public class DesignModelSaveReq {


    @Schema(description = "bpmn 流程图")
    @NotBlank(message = "bpmn 流程图不能为空")
    private String diagramData;

    @Schema(description = "模型图标")
//    @NotBlank(message = "模型图标不能为空")
    private String diagramIcon;

    @Schema(description = "模型名称")
    @NotBlank(message = "模型名称不能为空")
    private String diagramName;

    @Schema(description = "流程定义key")
    @NotBlank(message = "流程定义Key不能为空")
    private String definitionKey;

    @Schema(description = "模型类别")
    @NotBlank(message = "模型类别不能为空")
    private String categoryId;

    @Schema(description = "是否pool模型,0-不是,1-是。默认0")
    @NotNull(message = "是否pool不能为空")
    @Range(min = 0, max = 0, message = "暂不支持池模型")
    private Integer havePool;
}
