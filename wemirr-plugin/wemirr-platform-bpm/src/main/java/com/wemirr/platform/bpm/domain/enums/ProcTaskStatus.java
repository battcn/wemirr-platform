package com.wemirr.platform.bpm.domain.enums;

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
public enum ProcTaskStatus implements IEnum<String> {

    /**
     * 数据来源
     *
     * @see org.camunda.bpm.engine.delegate.DelegateTask.getEventName
     * 流程任务状态 completed = 已完成 ; deleted 驳回/删除/拒绝
     */
    CREATE("create", "已创建/待处理"),
    PENDING("pending", "待处理"),
    COMPLETED("completed", "已完成"),
    DELETED("deleted", "已作废");;

    @EnumValue
    @JsonValue
    private String status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ProcTaskStatus of(String status) {
        if (status == null) {
            return null;
        }
        for (ProcTaskStatus info : values()) {
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
