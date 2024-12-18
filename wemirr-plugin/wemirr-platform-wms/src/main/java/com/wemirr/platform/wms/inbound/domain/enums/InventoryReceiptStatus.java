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
 * 入库单状态
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "InventoryReceiptStatus")
public enum InventoryReceiptStatus implements IEnum<Integer> {

    DRAFT(0, "草稿"),
    WAIT(10, "待执行"),
    IN_EXECUTION(20, "执行中"),
    COMPLETED(30, "已完成"),
    CANCEL(-10, "已取消"),
    ;

    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static InventoryReceiptStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (InventoryReceiptStatus info : values()) {
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
