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
 * StockChangeType
 * 库存变动类型
 *
 * @author ddCat
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "StockChangeType")
public enum StockChangeType implements IEnum<Integer> {

    /**
     * 库存变动类型(入库、出库、盘盈入库、盘亏出库、调整入库、调整出库、报废出库、加工入库、加工出库、移库)
     */
    IN(0, "入库"),
    OUT(1, "出库"),
    INVENTORY_IN(2, "盘盈入库"),
    INVENTORY_OUT(3, "盘亏出库"),
    ADJUST_IN(4, "调整入库"),
    ADJUST_OUT(5, "调整出库"),
    SCRAP_OUT(6, "报废出库"),
    PROCESS_IN(7, "加工入库"),
    PROCESS_OUT(8, "加工出库"),
    MOVE_IN(9, "移库");
    ;

    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static StockChangeType of(Integer status) {
        if (status == null) {
            return null;
        }
        for (StockChangeType info : values()) {
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
