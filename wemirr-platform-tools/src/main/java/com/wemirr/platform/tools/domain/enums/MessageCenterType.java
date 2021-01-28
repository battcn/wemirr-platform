package com.wemirr.platform.tools.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.database.mybatis.DictionaryEnum;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息中心表
 * </p>
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum MessageCenterType implements DictionaryEnum<Integer> {
    /**
     * WAIT="待办"
     */
    WAIT(0, "待办"),

    /**
     * NOTIFY="通知"
     */
    NOTIFY(1, "通知"),

    /**
     * PUBLICITY="公告"
     */
    PUBLICITY(2, "公告"),

    /**
     * WARN="预警"
     */
    WARN(-1, "预警"),
    ;

    private Integer type;

    @Parameter(description = "描述信息")
    private String desc;


    @JsonCreator
    public static MessageCenterType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (MessageCenterType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(MessageCenterType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
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
