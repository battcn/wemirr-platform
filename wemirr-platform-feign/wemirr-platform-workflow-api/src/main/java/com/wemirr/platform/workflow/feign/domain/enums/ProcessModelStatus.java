package com.wemirr.platform.workflow.feign.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * ProcessModelStatus
 *
 * @author Levin
 */
@Getter
@JsonFormat
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "模型状态")
public enum ProcessModelStatus implements DictEnum<Integer> {

    /**
     * 0-未部署,1-已经部署,2-新版本待部署
     */
    NOT_EXIST(-1, "不存在"),
    NOT_DEPLOYED(0, "未部署"),
    DEPLOYED(1, "已经部署"),
    NEW_VERSION_TO_DEPLOY(2, "新版本待部署"),
    ;

    @EnumValue
    @JsonValue
    private Integer value;

    @Schema(description = "描述")
    private String label;

    @JsonCreator
    public static ProcessModelStatus of(Integer type) {
        if (type == null) {
            return null;
        }
        for (ProcessModelStatus info : values()) {
            if (info.value.equals(type)) {
                return info;
            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
