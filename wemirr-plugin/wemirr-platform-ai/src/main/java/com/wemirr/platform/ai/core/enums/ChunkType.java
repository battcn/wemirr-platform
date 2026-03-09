package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 知识分片类型枚举
 * <p>
 * 定义知识库中文档分片的类型，用于区分不同的内容结构。
 * </p>
 *
 * <h3>分片类型说明</h3>
 * <ul>
 *   <li><b>TEXT</b>：普通文本分片，来自文档的连续文本段落</li>
 *   <li><b>QUESTION</b>：问题分片，QA 对中的问题部分</li>
 *   <li><b>ANSWER</b>：答案分片，QA 对中的答案部分</li>
 *   <li><b>FULL_QA</b>：完整 QA 对，包含问题和答案</li>
 * </ul>
 *
 * @author Levin
 * @since 2025/10/11
 */
@Getter
@AllArgsConstructor
public enum ChunkType implements DictEnum<String> {

    /**
     * 普通文本分片
     */
    TEXT("TEXT", "文本分片"),

    /**
     * 问题分片
     */
    QUESTION("QUESTION", "问题分片"),

    /**
     * 答案分片
     */
    ANSWER("ANSWER", "答案分片"),

    /**
     * 完整 QA 对
     */
    FULL_QA("FULL_QA", "完整QA对");

    /**
     * 类型编码（用于数据库存储）
     */
    @EnumValue
    @JsonValue
    private final String value;

    /**
     * 类型标签
     */
    private final String label;

    /**
     * 根据编码获取分片类型
     *
     * @param code 类型编码
     * @return 对应的分片类型，未找到返回 null
     */
    @JsonCreator
    public static ChunkType of(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        for (ChunkType type : values()) {
            if (type.value.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
