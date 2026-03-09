package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 知识条目类型枚举
 * <p>
 * 定义知识库中支持的条目类型，不同类型有不同的处理逻辑。
 * </p>
 *
 * <h3>类型说明</h3>
 * <ul>
 *   <li><b>DOCUMENT</b>：文档类型，如 PDF、Word、Markdown 等</li>
 *   <li><b>QA_PAIR</b>：问答对，包含问题和答案</li>
 *   <li><b>STRUCTURED</b>：结构化数据，如表格、JSON 等</li>
 *   <li><b>TEXT_SNIPPET</b>：文本片段，手动输入的短文本</li>
 * </ul>
 *
 * @author Levin
 * @since 2025/10/11
 */
@Getter
@AllArgsConstructor
public enum KnowledgeItemType implements DictEnum<String> {

    /**
     * 文档类型
     * <p>
     * 支持 PDF、Word、Markdown、TXT 等文档格式。
     * </p>
     */
    DOCUMENT("DOCUMENT", "文档"),

    /**
     * 问答对
     * <p>
     * 包含问题和答案的结构化知识。
     * </p>
     */
    QA_PAIR("QA_PAIR", "问答对"),

    /**
     * 结构化数据
     * <p>
     * 表格、JSON 等结构化数据。
     * </p>
     */
    STRUCTURED("STRUCTURED", "结构化数据"),

    /**
     * 文本片段
     * <p>
     * 手动输入的短文本内容。
     * </p>
     */
    TEXT_SNIPPET("TEXT_SNIPPET", "文本片段");

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
     * 根据编码获取类型
     *
     * @param code 类型编码
     * @return 对应的类型，未找到返回 null
     */
    @JsonCreator
    public static KnowledgeItemType of(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        for (KnowledgeItemType type : values()) {
            if (type.value.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 是否需要文件解析
     *
     * @return 是否需要文件解析
     */
    public boolean requiresFileParsing() {
        return this == DOCUMENT;
    }
}
