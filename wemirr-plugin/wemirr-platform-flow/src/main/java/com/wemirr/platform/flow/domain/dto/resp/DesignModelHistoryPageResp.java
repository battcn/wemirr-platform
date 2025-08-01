
package com.wemirr.platform.flow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 流程模型历史分页查询Response
 *
 * @author Levin
 */
@Data
@Schema(name = "DesignModelHistoryPageResp", description = "流程模型历史分页查询")
public class DesignModelHistoryPageResp {

    @Schema(description = "历史模型id")
    private String historyModelId;

    @Schema(description = "模型id")
    private String modelId;

    @Schema(description = "模型名称")
    private String diagramNames;

    @Schema(description = "部署名称")
    private String deploymentName;

    @Schema(description = "资源名称")
    private String resourceNames;

    @Schema(description = "资源ids")
    private String resourceIds;

    @Schema(description = "部署id")
    private String deploymentId;

    @Schema(description = "部署时间", example = "2020-12-21 12:22")
    protected Instant deploymentTime;

    @Schema(description = "模型类别")
    private String category;

    @Schema(description = "类别名称")
    private String categoryName;

    @Schema(description = "当前模型版本")
    private Integer version;

    @Schema(description = "流程定义key,多个逗号隔开")
    private String processDefinitionKeys;

    @Schema(description = "流程定义ids,多个逗号隔开")
    private String processDefinitionIds;

    @Schema(description = "是否pool模型,0-不是,1-是。默认0")
    private Integer havePool;

    @Schema(description = "活动数量")
    private Long active;

    @Schema(description = "挂起数量")
    private Long suspended;

    @Schema(description = "完成数量")
    private Long completed;

    @Schema(description = "外部终止")
    private Long externallyTerminated;

    @Schema(description = "内部终止")
    private Long internallyTerminated;

    @Schema(description = "所有状态数量")
    private Long totalNum;

    @Schema(description = "创建用户姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
