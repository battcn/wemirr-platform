package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.warm.flow.orm.entity.FlowTask;

/**
 * @author battcn
 * @since 2025/5/24
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "WorkflowTaskResp")
public class WorkflowTaskResp extends FlowTask {

    /**
     * 计划审批人
     */
    private String approver;

    /**
     * 转办人
     */
    private String transferredBy;

    /**
     * 委派人
     */
    private String delegate;

}
