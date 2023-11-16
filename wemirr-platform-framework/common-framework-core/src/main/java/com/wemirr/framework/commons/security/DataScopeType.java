package com.wemirr.framework.commons.security;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 数据权限范围，值越大，权限越大
 * </p>
 *
 * @author Levin
 */
@Getter
@JsonFormat
@AllArgsConstructor
public enum DataScopeType implements IEnum<Integer> {


    /**
     * ALL=5全部
     */
    ALL(50, "全部"),

    /**
     * THIS_LEVEL_CHILDREN=3本级以及子级
     */
    THIS_LEVEL_CHILDREN(40, "本级以及子级"),
    /**
     * THIS_LEVEL=4本级
     */
    THIS_LEVEL(30, "本级"),

    /**
     * CUSTOMIZE=2自定义
     */
    CUSTOMIZE(20, "自定义"),
    /**
     * SELF=1个人
     */
    SELF(10, "个人"),

    IGNORE(0, "跟随系统上下文"),
    ;

    @EnumValue
    @JsonValue
    private final Integer type;
    private final String desc;

    @JsonCreator
    public static DataScopeType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (DataScopeType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        return type;
    }
}
