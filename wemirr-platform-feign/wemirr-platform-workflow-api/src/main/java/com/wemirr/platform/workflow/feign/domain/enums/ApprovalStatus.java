package com.wemirr.platform.workflow.feign.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批状态枚举
 *
 * @author battcn
 * @since 2025/6/04
 */
@Getter
@AllArgsConstructor
public enum ApprovalStatus implements DictEnum<String> {


    IN_PROGRESS("1", "待审批"),
    TERMINATED("4", "终止"),
    INVALID("5", "作废"),
    REVOKED("6", "撤销"),
    RETRIEVED("7", "取回"),
    COMPLETED("8", "审批通过"),
    REJECTED("9", "审批驳回"),
    EXPIRED("10", "失效");

    ;
    /**
     * 状态码
     */
    @EnumValue
    @JsonValue
    private final String value;

    /**
     * 描述
     */
    private final String label;


    /**
     * 根据 code 获取枚举
     *
     * @param code 状态码
     * @return 枚举值
     */
    public static String fromCode(String code) {
        for (ApprovalStatus status : values()) {
            if (status.getValue().equals(code)) {
                return status.getLabel();
            }
        }
        throw new IllegalArgumentException("未知的审批状态码: " + code);
    }

    public static ApprovalStatus of(String code) {
        for (ApprovalStatus status : values()) {
            if (status.getValue().equals(code)) {
                return status;
            }
        }
        return null;
    }


    /**
     * 判断是否为结束状态
     * 结束状态包括：终止、审批通过、审批驳回
     *
     * @return true-结束状态, false-非结束状态
     */
    public boolean isEndStatus() {
        return this == TERMINATED || this == COMPLETED || this == REJECTED;
    }


}
