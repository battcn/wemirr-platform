package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class InstanceExtDetailResp {

    @Schema(description = "流程实例ID")
    private Long instanceId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "来源类型 例如新增 或者更新等")
    private String operationType;

    @Schema(description = "来源类型  比如网点")
    private String businessType;

    @Schema(description = "附件")
    private String attachment;

    @Schema(description = "变量")
    private String variable;

    @Schema(description = "业务id")
    private String businessId;

    @Schema(description = "业务编号")
    private String businessCode;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "完成时间")
    private Instant finishTime;

    @Schema(description = "表单预览数据")
    private ProcessInstanceFormPreviewResp formPreview;

    @Schema(description = "任务清单")
    private List<FlowTaskApproveListResp> taskList;
}
