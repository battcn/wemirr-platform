package com.wemirr.platform.tms.domain.enums;

import com.wemirr.framework.redis.plus.sequence.Sequence;
import lombok.RequiredArgsConstructor;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public enum TmsSequence implements Sequence {

    /**
     * 规费编号
     */
    EXPENSE_NO("tenant:tms:expense", "TEN"),
    ACCIDENT_NO("tenant:tms:accident", "TAN"),

    PURCHASE_NO("tenant:tms:purchase", "PPN"),
    ORDER_NO("tenant:tms:order", "TON"),


    ;

    public final String key;
    public final String prefix;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String prefix() {
        return prefix;
    }
}
