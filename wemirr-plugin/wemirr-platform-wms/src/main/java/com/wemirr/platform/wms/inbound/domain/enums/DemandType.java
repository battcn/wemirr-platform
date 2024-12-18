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
 * 需求类型[0-出库,1-入库,2-调拨,3-退箱]
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "DemandType")
public enum DemandType implements DictEnum<Integer> {


    /**
     * 需求类型[0-出库,1-入库,2-调拨,5-退货]
     */
    OUT(0, "出库"),
    IN(1, "入库"),
    DISPATCH(2, "调拨"),
    RETURN(5, "退货"),
    ;

    @EnumValue
    @JsonValue
    private Integer type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static DemandType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (DemandType info : values()) {
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

    @Override
    public String toString() {
        return String.valueOf(type);
    }

}
