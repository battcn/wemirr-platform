package com.wemirr.framework.robot.emums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * NotifyType
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "枚举")
@JsonFormat
public enum NotifyType {

    /**
     * 钉钉
     */
    DING_TALK("ding-talk", "钉钉"),
    /**
     * 微信
     */
    WECHAT("wechat", "微信"),
    FEI_SHU("fei-shu", "飞书"),
    EMAIL("email", "邮箱"),
    ;
    @EnumValue
    @JsonValue
    private String type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static NotifyType of(String type) {
        if (type == null) {
            return null;
        }
        for (NotifyType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return String.valueOf(type);
    }


}