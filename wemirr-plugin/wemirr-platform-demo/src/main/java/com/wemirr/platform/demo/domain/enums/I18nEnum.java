package com.wemirr.platform.demo.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.i18n.Language;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Levin
 */

@Getter
@Schema(description = "枚举")
@JsonFormat
@RequiredArgsConstructor
public enum I18nEnum implements Language, IEnum<Integer> {


    /**
     * 测试
     */
    MI(0, "小米", "i18n.type.mi"),
    APPLE(1, "苹果", "i18n.type.apple"),
    ;
    @EnumValue
    @JsonValue
    private final Integer type;
    private final String desc;
    private final String language;

    @JsonCreator
    public static I18nEnum of(Integer type) {
        if (type == null) {
            return null;
        }
        for (I18nEnum info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public Integer getValue() {
        return this.type;
    }
}
