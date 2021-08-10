package com.wemirr.framework.database.configuration.dynamic.event.body;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * <p>
 * TenantType
 * </p>
 *
 * @author Levin
 * @since 2020-02-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum EventAction {

    /**
     * 添加
     */
    ADD(1, "添加"),
    /**
     * 删除
     */
    DEL(-1, "删除"),
    ;
    @EnumValue
    @JsonValue
    private Integer type;

    private String desc;

    public static EventAction of(Integer type) {
        if (type == null) {
            return null;
        }
        for (EventAction info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return String.valueOf(type);
    }


}
