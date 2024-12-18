package com.wemirr.platform.wms.inbound.domain.enums;


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
 * 容器占用类型
 *
 * @author ddCat
 * @since 2024-06-28
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "ContainerTaskType")
public enum ContainerTaskType implements IEnum<String> {


    RECEIVING_PLAN("RECEIVING_PLAN", "收货计划"),

    ;

    @EnumValue
    @JsonValue
    private String code;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ContainerTaskType of(String code) {
        if (code == null) {
            return null;
        }
        for (ContainerTaskType info : values()) {
            if (info.code.equals(code)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return code;
    }

}
