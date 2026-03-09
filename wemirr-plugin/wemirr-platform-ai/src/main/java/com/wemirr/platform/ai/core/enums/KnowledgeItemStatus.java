package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 知识条目处理状态枚举
 * <p>
 * 定义知识条目在处理流程中的各个状态。
 * </p>
 *
 * <h3>状态流转</h3>
 * <pre>
 * PENDING → PROCESSING → PROCESSED
 *              ↓
 *           FAILED
 * </pre>
 *
 * @author Levin
 * @since 2025/10/11
 */
@Getter
@AllArgsConstructor
public enum KnowledgeItemStatus implements DictEnum<String> {

    /**
     * 待处理
     * <p>
     * 知识条目已创建，等待向量化处理。
     * </p>
     */
    PENDING("PENDING", "待处理"),

    /**
     * 处理中
     * <p>
     * 知识条目正在进行向量化处理。
     * </p>
     */
    PROCESSING("PROCESSING", "处理中"),

    /**
     * 已处理
     * <p>
     * 知识条目已完成向量化，可用于检索。
     * </p>
     */
    PROCESSED("PROCESSED", "已处理"),

    /**
     * 处理失败
     * <p>
     * 知识条目向量化处理失败，需要重试或人工干预。
     * </p>
     */
    FAILED("FAILED", "处理失败");

    /**
     * 状态编码（用于数据库存储）
     */
    @EnumValue
    @JsonValue
    private final String value;

    /**
     * 状态标签
     */
    private final String label;

    /**
     * 根据编码获取状态
     *
     * @param code 状态编码
     * @return 对应的状态，未找到返回 null
     */
    @JsonCreator
    public static KnowledgeItemStatus of(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        for (KnowledgeItemStatus status : values()) {
            if (status.value.equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 是否为终态
     *
     * @return 是否为终态（PROCESSED 或 FAILED）
     */
    public boolean isTerminal() {
        return this == PROCESSED || this == FAILED;
    }

    /**
     * 是否可重试
     *
     * @return 是否可重试（FAILED 状态可重试）
     */
    public boolean isRetryable() {
        return this == FAILED;
    }
}
