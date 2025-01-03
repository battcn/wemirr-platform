package com.wemirr.platform.iam.system.domain.enums;

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
 * ThirdAuthType
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "枚举 - ThirdAuthType")
public enum ThirdAuthType implements IEnum<String> {

    GITEE("gitee", "GITEE 平台"),
    ;
    @EnumValue
    @JsonValue
    private String type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ThirdAuthType of(String type) {
        if (type == null) {
            return null;
        }
        for (ThirdAuthType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }

    @Override
    public String getValue() {
        return type;
    }
}
