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
@JsonFormat
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "CarrierType")
public enum CarrierType implements DictEnum<String> {

    TRADE_OUT("TRADE_OUT", "外贸"),
    TRADE_IN("TRADE_IN", "内贸");

    @EnumValue
    @JsonValue
    private String value;

    @Schema(description = "描述")
    private String label;

    @JsonCreator
    public static CarrierType of(String type) {
        if (type == null) {
            return null;
        }
        for (CarrierType info : values()) {
            if (info.value.equals(type)) {
                return info;
            }
        }
        return null;
    }
}
