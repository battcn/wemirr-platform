package com.wemirr.framework.database.mybatis.auth;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.database.mybatis.DictionaryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 数据权限范围，值越大，权限越大
 * </p>
 *
 * @author Levin
 * @since 2020-10-01
 */
@Getter
@AllArgsConstructor
@JsonFormat
public enum DataScopeType implements DictionaryEnum<Integer> {


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
    ;

    @EnumValue
    @JsonValue
    private final Integer val;
    private final String desc;

    @JsonCreator
    public static DataScopeType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (DataScopeType info : values()) {
            if (info.val.equals(type)) {
                return info;
            }
        }
        return null;
    }

    public static DataScopeType match(String val, DataScopeType def) {
        for (DataScopeType enm : DataScopeType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static DataScopeType match(Integer val, DataScopeType def) {
        if (val == null) {
            return def;
        }
        for (DataScopeType enm : DataScopeType.values()) {
            if (val.equals(enm.getVal())) {
                return enm;
            }
        }
        return def;
    }

    public static DataScopeType get(String val) {
        return match(val, null);
    }

    public static DataScopeType get(Integer val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(DataScopeType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    public Integer getValue() {
        return this.val;
    }
}
