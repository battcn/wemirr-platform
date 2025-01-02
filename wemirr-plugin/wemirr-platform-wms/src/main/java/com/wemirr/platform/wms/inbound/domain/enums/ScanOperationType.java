package com.wemirr.platform.wms.inbound.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * 操作类型
 *
 * @author Levin
 */
@Getter
@JsonFormat
@RequiredArgsConstructor
public enum ScanOperationType implements IEnum<String> {

    /**
     * 入库
     */
    IN("in", "入库", "入库"),
    OUT("out", "出库", "出库"),
    INVENTORY("inventory", "盘点", "盘点"),
    ;

    @EnumValue
    @JsonValue
    private final String type;
    private final String desc;
    private final String format;


    @JsonCreator
    public static ScanOperationType of(String type) {
        if (type == null) {
            return null;
        }
        for (ScanOperationType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }


    public String getFormat(Object... args) {
        return String.format(this.format, args);
    }


    @Override
    public String getValue() {
        return type;
    }

}
