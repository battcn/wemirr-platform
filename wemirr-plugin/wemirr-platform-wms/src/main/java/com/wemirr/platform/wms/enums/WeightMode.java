package com.wemirr.platform.wms.enums;

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
@Schema(description = "WeightMode")
public enum WeightMode implements DictEnum<String> {


    /**
     * 禁用、启用、条件
     */
    DISABLED("disabled", "不启用"),
    ENABLED("enabled", "启用"),
    CONDITIONAL("conditional", "条件启用"),
    ;

    @EnumValue
    @JsonValue
    private String type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static WeightMode of(String type) {
        if (type == null) {
            return null;
        }
        for (WeightMode info : values()) {
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
        return type;
    }

}
