package com.wemirr.platform.wms.outbound.domain.enums;


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
 * 出库单类型
 *
 * @author ddCat
 * @since 2024-08-06
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "OutboundType")
public enum OutboundType implements IEnum<Integer> {

    SALES(10, "销售出库"),
    TRANSFER(20, "调拨出库"),
    RETURN(30, "退货出库"),
    EXCHANGE(40, "换货出库"),
    REPAIR(50, "维修出库"),
    DESTROY(60, "销毁出库"),
    ;

    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static OutboundType of(Integer status) {
        if (status == null) {
            return null;
        }
        for (OutboundType info : values()) {
            if (info.status.equals(status)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public Integer getValue() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }

}
