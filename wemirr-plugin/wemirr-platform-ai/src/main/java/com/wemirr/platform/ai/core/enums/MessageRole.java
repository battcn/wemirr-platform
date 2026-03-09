package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息角色枚举
 * <p>
 * 定义对话中消息的发送者角色，用于区分不同来源的消息。
 * </p>
 *
 * @author Levin
 * @since 2025/10/11
 */
@Getter
@AllArgsConstructor
public enum MessageRole implements DictEnum<String> {

    /**
     * 系统消息
     * <p>
     * 用于设置 AI 的行为和角色定义。
     * </p>
     */
    SYSTEM("SYSTEM", "系统"),

    /**
     * 用户消息
     * <p>
     * 用户发送的消息。
     * </p>
     */
    USER("USER", "用户"),

    /**
     * AI 助手消息
     * <p>
     * AI 生成的回复消息。
     * </p>
     */
    ASSISTANT("ASSISTANT", "助手"),

    /**
     * 工具消息
     * <p>
     * 工具调用的结果消息。
     * </p>
     */
    TOOL("TOOL", "工具");

    /**
     * 角色编码（用于数据库存储）
     */
    @EnumValue
    @JsonValue
    private final String value;

    /**
     * 角色标签
     */
    private final String label;

    /**
     * 根据编码获取角色
     *
     * @param code 角色编码
     * @return 对应的角色，未找到返回 null
     */
    @JsonCreator
    public static MessageRole of(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        for (MessageRole role : values()) {
            if (role.value.equalsIgnoreCase(code)) {
                return role;
            }
        }
        return null;
    }
}
