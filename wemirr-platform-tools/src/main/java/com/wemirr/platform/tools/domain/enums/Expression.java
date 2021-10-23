package com.wemirr.platform.tools.domain.enums;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.db.mybatis.DictionaryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum Expression implements DictionaryEnum<String> {

    /**
     * eq
     */
    EQ(StringPool.EQUALS),
    NE("<>"),
    GT(StringPool.RIGHT_CHEV),
    GE(">="),
    LT(StringPool.LEFT_CHEV),
    LE("<="),
    IS_NULL("IS NULL"),
    LIKE("LIKE"),
    IS_NOT_NULL("IS NOT NULL"),
    ;

    public String value;

    @JsonCreator
    public static Expression of(String name) {
        for (Expression expression : values()) {
            if (StringUtils.equalsIgnoreCase(expression.name(), name)) {
                return expression;
            }
        }
        return null;
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(Expression val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }


    @Override
    public String getDesc() {
        return value;
    }
}

