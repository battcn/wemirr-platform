package com.wemirr.platform.workflow.feign;

import com.wemirr.framework.commons.FeignConstants;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowInstanceTerminationReq;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowStartReq;
import com.wemirr.platform.workflow.feign.domain.resp.InstanceStartResp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 工作流相关feign
 * @author battcn
 * @since 2025/5/27
 **/
@FeignClient(name = FeignConstants.WORKFLOW_FEIGN_NAME)
public interface WorkflowFeign {

    /**
     * 开启一个流程实例,根据流程定义id 或者流程定义编码
     * @param req req
     * @return 流程实例
     */
    @PostMapping("/definition/start")
    @Operation(summary = "开启一个流程", description = "开启一个流程")
    InstanceStartResp startDefinition(@RequestBody WorkflowStartReq req);

    /**
     * 根据流程实例id 终止流程
     * @param id 流程实例id
     * @param req 审批信息
     */
    @PostMapping("/instance/{id}/termination")
    @Operation(summary = "实例终止 - [DONE]", description = "根据实例id终止实例")
    void terminationInstance(@PathVariable("id") Long id, @RequestBody WorkflowInstanceTerminationReq req);

}
