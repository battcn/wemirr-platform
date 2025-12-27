package com.wemirr.framework.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI 模型类型枚举
 *
 * @author Levin
 * @since 2025/10/09
 */
@Getter
@AllArgsConstructor
public enum ModelType implements DictEnum<String> {

    TEXT("TEXT", "文本模型"),
    EMBEDDING("EMBEDDING", "向量模型"),
    IMAGE("IMAGE", "图像模型"),
    AUDIO("AUDIO", "音频模型"),
    RERANK("RERANK", "重排序模型");

    @EnumValue
    @JsonValue
    private final String value;
    private final String label;

    public static ModelType of(String code) {
        if (code == null || code.isBlank()) return null;
        for (ModelType type : values()) {
            if (type.value.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
