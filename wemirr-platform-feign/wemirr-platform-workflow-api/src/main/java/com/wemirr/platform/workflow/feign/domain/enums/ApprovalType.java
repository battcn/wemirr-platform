package com.wemirr.platform.workflow.feign.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author battcn
 * @since 2025/7/8
 **/
@Getter
@AllArgsConstructor
public enum ApprovalType implements DictEnum<String> {

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
    TERMINATION("TERMINATION", "审批批绝"),


    ;
    @EnumValue
    @JsonValue
    private final String value;

    private final String label;
}
