package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 知识条目类型
 */
public enum KnowledgeItemType {
    DOCUMENT("DOCUMENT"),
    QA_PAIR("QA_PAIR"),
    STRUCTURED("STRUCTURED"),
    TEXT_SNIPPET("TEXT_SNIPPET");

    @EnumValue
    private final String code;

    KnowledgeItemType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}


