package com.wemirr.platform.workflow.feign.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批动作,后续业务可扩展(与 流程的ApprovalType 的关系为  ApprovalAction 的多个动作可能对应 ApprovalType的一个动作)
 * @author battcn
 * @since 2025/7/8
 **/
@Getter
@AllArgsConstructor
public enum ApprovalAction implements DictEnum<String> {

    /**
     * 审批通过
     */
    PASS("PASS", "审批通过"),
    /**
     * 审批驳回 退回上一节点
     */
    REJECT("REJECT", "审批驳回"),

    /**
     * 审批拒绝（终止流程）
     */
    TERMINATION("TERMINATION", "审批拒绝"),
    /**
     * 作废
     */
    CANCEL("CANCEL", "作废"),

    /**
     * 待审批
     */
    WAIT_APPROVE("WAIT_APPROVE", "待审批"),
    ;
    @EnumValue
    @JsonValue
    private final String value;

    private final String label;


    public static ApprovalAction getApprovalActionByCode(String code) {
        for (ApprovalAction status : values()) {
            if (status.getValue().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
