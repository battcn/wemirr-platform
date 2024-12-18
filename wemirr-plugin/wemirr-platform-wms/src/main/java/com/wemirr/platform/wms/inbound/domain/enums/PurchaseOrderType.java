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
 * 采购订单类型
 *
 * @author ddCat
 * @since 2024-08-02
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "PurchaseOrderType")
public enum PurchaseOrderType implements IEnum<String> {

    PURCHASE_INVENTORY("PURCHASE_INVENTORY", "采购入库"),
    RETURN_INVENTORY("RETURN_INVENTORY", "退货入库"),
    ;

    @EnumValue
    @JsonValue
    private String status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static PurchaseOrderType of(String status) {
        if (status == null) {
            return null;
        }
        for (PurchaseOrderType info : values()) {
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
