package com.wemirr.platform.flow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 流程任务信息
 * @author Levin
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Schema
public class ProcessTaskResp {

    @Schema(description = "任务ID")
    private String id;
    @Schema(description = "任务名称")
    private String taskName;
    @Schema(description = "流程实例ID")
    private String procInstId;
    @Schema(description = "流程实例名称")
    private String procInstName;
    @Schema(description = "申请人")
    private String startUser;
    @Schema(description = "模型类型")
    private String processCategoryId;
    @Schema(description = "流程名称")
    private String diagramName;
    @Schema(description = "流程状态")
    private Integer processInstStatus;
    @Schema(description = "跟进日期")
    private Date startTime;
    @Schema(description = "到期日期")
    private Date endTime;
    @Schema(description = "部署名称")
    private String deploymentName;
    @Schema(description = "执行人")
    private String createdName;
    @Schema(description = "申请时间")
    private Date createdTime;
}
