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
 * DeliveryMode
 * 送货方式（自提、送达）
 *
 * @author ddCat
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "DeliveryMode")
public enum DeliveryMode implements IEnum<String> {

    /**
     * 送货方式（自提、送达）
     */
    SELF_PICKUP("SELF_PICKUP", "自提"),
    DELIVERY("DELIVERY", "送达")
    ;

    @EnumValue
    @JsonValue
    private String status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static DeliveryMode of(String status) {
        if (status == null) {
            return null;
        }
        for (DeliveryMode info : values()) {
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
        return String.valueOf(status);
    }

}
