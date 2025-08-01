package com.wemirr.platform.flow.domain.enums;

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
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema
@JsonFormat
public enum ProcInstStatus implements IEnum<String> {

    /**
     * 流程实例状态 处理中，已完成，已作废（后续扩展转办之类的）
     */
    IN_PROGRESS("in_progress", "处理中"),
    DONE("done", "已完成"),
    CANCEL("cancel", "已作废");;

    @EnumValue
    @JsonValue
    private String status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ProcInstStatus of(String status) {
        if (status == null) {
            return null;
        }
        for (ProcInstStatus info : values()) {
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
