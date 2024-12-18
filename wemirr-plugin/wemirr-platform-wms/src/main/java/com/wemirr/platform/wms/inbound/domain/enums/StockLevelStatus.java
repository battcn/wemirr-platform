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
 * StockLevelStatus
 * 库存等级
 *
 * @author ddCat
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "StockLevelStatus")
public enum StockLevelStatus implements IEnum<Integer> {

    /**
     * 物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)
     */
    GOODS(0, "良品"),
    WAIT(10, "待检品"),
    BAD(20, "不良品"),
    REWORK(30, "返工品"),
    SCRAP(40, "报废品"),
    RETURN(50, "退货品"),
    ;

    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static StockLevelStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (StockLevelStatus info : values()) {
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
