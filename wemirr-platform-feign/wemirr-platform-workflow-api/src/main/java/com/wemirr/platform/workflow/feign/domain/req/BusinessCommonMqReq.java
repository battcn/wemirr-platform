package com.wemirr.platform.workflow.feign.domain.req;

import com.wemirr.platform.workflow.feign.domain.enums.ApprovalAction;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务公共mq发送实体
 * @author battcn
 * @since 2025/5/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessCommonMqReq {
    /**
     * 实例Id
     */
    private Long instanceId;
    /**
     * 租户信息
     */
    private String tenantId;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 扩展业务数据，json格式
     */
    private WorkflowCommonReq workflowCommonReq;

    /**
     * 流程实例状态
     */
    private ApprovalStatus instanceStatus;

    /**
     *  操作人
     */
    private String createName;

    /**
     * 操作人id
     */
    private String createBy;

    private ApprovalAction approvalAction;
}
