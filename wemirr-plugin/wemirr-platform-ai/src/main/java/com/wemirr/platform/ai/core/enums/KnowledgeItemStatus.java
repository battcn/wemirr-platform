package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 知识条目处理状态
 */
public enum KnowledgeItemStatus {
    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
    PROCESSED("PROCESSED"),
    FAILED("FAILED");

    @EnumValue
    private final String code;

    KnowledgeItemStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}


