package com.wemirr.platform.wms.inbound.domain.enums;

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
 * 承运商类型（0=外贸、1=内贸）
 *
 * @author ddCat
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "CarrierType")
public enum CarrierType implements DictEnum<String> {

    TRADE_OUT("TRADE_OUT", "外贸"),
    TRADE_IN("TRADE_IN", "内贸");

    @EnumValue
    @JsonValue
    private String type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static CarrierType of(String type) {
        if (type == null) {
            return null;
        }
        for (CarrierType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public String getValue() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

}
