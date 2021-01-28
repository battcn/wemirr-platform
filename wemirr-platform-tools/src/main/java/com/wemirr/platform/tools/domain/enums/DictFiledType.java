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
public enum DictFiledType implements DictionaryEnum<String> {
    /**
     * radio
     */
    RADIO("radio", "radio"),
    /**
     * checkbox
     */
    CHECKBOX("checkbox", "checkbox"),
    /**
     * select
     */
    SELECT("select", "select"),
    ;

    private String type;

    @Parameter(description = "描述信息")
    private String desc;


    @JsonCreator
    public static DictFiledType of(String type) {
        if (type == null) {
            return null;
        }
        for (DictFiledType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(DictFiledType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    public String getValue() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }


}
