package com.wemirr.platform.flow.domain.dto.resp;

import com.wemirr.platform.flow.domain.enums.ProcInstStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ProcessInstancePageResp")
public class ProcessInstancePageResp {

    @Schema(description = "流程实例扩展表ID")
    private Long id;
    @Schema(description = "流程实例Id")
    private String procInstId;
    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程实例版本")
    private String procInstVersion;

    @Schema(description = "流程实例名称")
    private String procInstCategoryName;
    @Schema(description = "流程定义名称")
    private String procDefName;
    @Schema(description = "流程定义编码")
    private String procDefKey;
    @Schema(description = "激活状态")
    private Boolean procInstActivate;
    @Schema(description = "流程状态")
    private ProcInstStatus procInstStatus;
    @Schema(description = "流程实例启动时间")
    private Instant procInstStartTime;
    @Schema(description = "流程实例结束时间")
    private Instant procInstEndTime;
    @Schema(description = "耗时")
    private Long duration;
    @Schema(description = "发起人")
    private String createdName;
    @Schema(description = "当前任务信息")
    private List<ProcessTaskResp> tasks;

    public Long getDuration() {
        if (procInstStartTime == null || procInstEndTime == null) {
            return null;
        }
        return Duration.between(procInstStartTime, procInstEndTime).getSeconds();
    }
}
