package com.wemirr.platform.flow.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ProcessTaskPageReq")
public class ProcessTaskPageReq extends PageRequest {

    @Schema(description = "审批人", hidden = true)
    private Long approverId;

    @Schema(description = "任务名称")
    private String procTaskName;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程定义Id")
    private String procDefName;


}
