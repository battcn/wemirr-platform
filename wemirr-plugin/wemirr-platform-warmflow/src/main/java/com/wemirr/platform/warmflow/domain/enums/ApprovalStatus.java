package com.wemirr.platform.warmflow.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    @Override
    public String getValue() {
        return this.status;
    }

    @Override
    public String toString() {
        return status;
    }
}
