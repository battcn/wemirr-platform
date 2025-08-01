
package com.wemirr.platform.warmflow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 流程模型管理查询Response
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessModelDetailResp")
public class ProcessModelDetailResp {

    @Schema(description = "模型ID")
    private String id;

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "模型内容")
    private String diagramData;

    @Schema(description = "类别ID")
    private Long categoryId;

    @Schema(description = "类别编码")
    private String categoryCode;

    @Schema(description = "类别名称")
    private String categoryName;

    @Schema(description = "模型状态:0-未部署,1-已经部署,2-新版本待部署,参考常量字段:ModelStateType")
    private Integer state;

    @Schema(description = "是否pool模型,0-不是,1-是。默认0")
    private Integer havePool;

    @Schema(description = "流程定义key,多个逗号隔开")
    private String definitionKey;

    @Schema(description = "部署名称")
    private String deploymentName;

    @Schema(description = "部署时间", example = "2020-12-21 12:22")
    protected Instant deploymentTime;

    @Schema(description = "部署id")
    private String deploymentId;

    @Schema(description = "当前模型版本")
    private Integer version;

    @Schema(description = "创建人姓名")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;


}
