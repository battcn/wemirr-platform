
package com.wemirr.platform.warmflow.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 流程模型历史查询Response
 *
 * @author Levin
 */
@Data
@Schema(name = "DesignModelHistoryResp")
public class DesignModelHistoryResp {

    @Schema(name = "historyModelId", description = "历史模型id")
    private String historyModelId;

    @Schema(name = "modelId", description = "模型id")
    private String modelId;

    @Schema(name = "categoryName", description = "类别名称")
    private String categoryName;

    @Schema(name = "diagramNames", description = "模型名称")
    private String diagramNames;

    @Schema(name = "processDefinitionKeys", description = "流程定义key,多个逗号隔开")
    private String processDefinitionKeys;

    @Schema(name = "processDefinitionIds", description = "流程定义ids,多个逗号隔开")
    private String processDefinitionIds;

    @Schema(name = "diagramData", description = "bpmn模型(转换为base64存储)")
    private String diagramData;

    @Schema(name = "deploymentName", description = "部署名称")
    private String deploymentName;

    @Schema(name = "deploymentTime", description = "部署时间", example = "2020-12-21 12:22")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime deploymentTime;

    @Schema(name = "deploymentId", description = "部署id")
    private String deploymentId;

    @Schema(name = "resourceNames", description = "资源名称")
    private String resourceNames;

    @Schema(name = "resourceIds", description = "资源ids")
    private String resourceIds;

    @Schema(name = "category", description = "模型类别")
    private String category;

    @Schema(name = "version", description = "当前模型版本")
    private Integer version;

    @Schema(name = "remark", description = "备注")
    private String remark;

    @Schema(name = "createUserId", description = "创建用户id")
    private String createUserId;

    @Schema(name = "createUserName", description = "创建用户姓名")
    private String createUserName;

    @Schema(name = "active", description = "活动数量")
    private long active;

    @Schema(name = "suspended", description = "挂起数量")
    private long suspended;

    @Schema(name = "completed", description = "完成数量")
    private long completed;

    @Schema(name = "externallyTerminated", description = "外部终止")
    private long externallyTerminated;

    @Schema(name = "internallyTerminated", description = "内部终止")
    private long internallyTerminated;

    @Schema(name = "totalNum", description = "所有状态数量")
    private long totalNum;

    @Schema(name = "createTime", description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Instant createTime;

    @Schema(name = "havePool", description = "是否pool模型,0-不是,1-是。默认0")
    private Integer havePool;

}
