package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 分片类型
 */
public enum ChunkType {
    TEXT("TEXT"),
    QUESTION("QUESTION"),
    ANSWER("ANSWER"),
    FULL_QA("FULL_QA");

    @EnumValue
    private final String code;

    ChunkType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}


