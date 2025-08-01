package com.wemirr.platform.flow.domain.enums;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.exception.CheckedException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema
@JsonFormat
public enum ApprovalStatus implements IEnum<String> {

    /**
     * 状态。00：草稿；10：提交/待审批；20：审批通过；-10：审批撤回；-20：审批拒绝
     */
    DRAFT("00","草稿"),

    WAIT("10","待审批"),

    APPROVED("20","审批通过"),

    RETURN("-10","审批撤回"),

    REJECT("-20","审批拒绝"),
    /**
     * 待审核
     */
    WAITING("waiting", "待审核"),
    /**
     * 已终止
     */
    TERMINATION("termination", "已终止"),
    /**
     * 已完成
     */
    FINISH("finish", "已完成"),
    /**
     * 已作废
     */
    INVALID("invalid", "已作废"),
    /**
     * 已撤销
     */
    CANCEL("cancel", "已撤销"),

    BACK("back", "已退回"),

    /**
     * 已终止
     */

            ;

    @EnumValue
    @JsonValue
    private String status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ApprovalStatus of(String status) {
        if (status == null) {
            return null;
        }
        for (ApprovalStatus info : values()) {
            if (info.status.equals(status)) {
                return info;
            }
        }
        return null;
    }

    /**
     * 启动流程校验
     *
     * @param status 状态
     */
    public static void checkStartStatus(String status) {
        if (WAITING.getStatus().equals(status)) {
            throw new CheckedException("该单据已提交过申请,正在审批中！");
        } else if (FINISH.getStatus().equals(status)) {
            throw new CheckedException("该单据已完成申请！");
        } else if (INVALID.getStatus().equals(status)) {
            throw new CheckedException("该单据已作废！");
        } else if (TERMINATION.getStatus().equals(status)) {
            throw new CheckedException("该单据已终止！");
        } else if (StrUtil.isBlank(status)) {
            throw new CheckedException("流程状态为空！");
        }
    }

    /**
     * 撤销流程校验
     *
     * @param status 状态
     */
    public static void checkCancelStatus(String status) {
        if (CANCEL.getStatus().equals(status)) {
            throw new CheckedException("该单据已撤销！");
        } else if (FINISH.getStatus().equals(status)) {
            throw new CheckedException("该单据已完成申请！");
        } else if (INVALID.getStatus().equals(status)) {
            throw new CheckedException("该单据已作废！");
        } else if (TERMINATION.getStatus().equals(status)) {
            throw new CheckedException("该单据已终止！");
        } else if (BACK.getStatus().equals(status)) {
            throw new CheckedException("该单据已退回！");
        } else if (StrUtil.isBlank(status)) {
            throw new CheckedException("流程状态为空！");
        }
    }

    /**
     * 驳回流程校验
     *
     * @param status 状态
     */
    public static void checkBackStatus(String status) {
        if (BACK.getStatus().equals(status)) {
            throw new CheckedException("该单据已退回！");
        } else if (FINISH.getStatus().equals(status)) {
            throw new CheckedException("该单据已完成申请！");
        } else if (INVALID.getStatus().equals(status)) {
            throw new CheckedException("该单据已作废！");
        } else if (TERMINATION.getStatus().equals(status)) {
            throw new CheckedException("该单据已终止！");
        } else if (CANCEL.getStatus().equals(status)) {
            throw new CheckedException("该单据已撤销！");
        } else if (StrUtil.isBlank(status)) {
            throw new CheckedException("流程状态为空！");
        }
    }

    /**
     * 判断是否为指定的状态之一：草稿、已撤销或已退回
     *
     * @param status 要检查的状态
     * @return 如果状态为草稿、已撤销或已退回之一，则返回 true；否则返回 false
     */
    public static boolean isDraftOrCancelOrBack(String status) {
        return DRAFT.status.equals(status) || CANCEL.status.equals(status) || BACK.status.equals(status);
    }

    /**
     * 作废,终止流程校验
     *
     * @param status 状态
     */
    public static void checkInvalidStatus(String status) {
        if (FINISH.getStatus().equals(status)) {
            throw new CheckedException("该单据已完成申请！");
        } else if (INVALID.getStatus().equals(status)) {
            throw new CheckedException("该单据已作废！");
        } else if (TERMINATION.getStatus().equals(status)) {
            throw new CheckedException("该单据已终止！");
        } else if (StrUtil.isBlank(status)) {
            throw new CheckedException("流程状态为空！");
        }
    }

    /**
     * 判断是否为撤销，退回，作废，终止
     *
     * @param status status
     * @return 结果
     */
    public static boolean initialState(String status) {
        return CANCEL.status.equals(status) || BACK.status.equals(status) || INVALID.status.equals(status) || TERMINATION.status.equals(status);
    }

    /**
     * 获取运行中的实例状态列表
     *
     * @return 包含运行中实例状态的不可变列表
     * （包含 DRAFT、WAITING、BACK 和 CANCEL 状态）
     */
    public static List<String> runningStatus() {
        return Arrays.asList(DRAFT.status, WAITING.status, BACK.status, CANCEL.status);
    }

    /**
     * 获取结束实例的状态列表
     *
     * @return 包含结束实例状态的不可变列表
     * （包含 FINISH、INVALID 和 TERMINATION 状态）
     */
    public static List<String> finishStatus() {
        return Arrays.asList(FINISH.status, INVALID.status, TERMINATION.status);
    }


    @Override
    public String getValue() {
        return this.status;
    }

    @Override
    public String toString() {
        return status;
    }
}
