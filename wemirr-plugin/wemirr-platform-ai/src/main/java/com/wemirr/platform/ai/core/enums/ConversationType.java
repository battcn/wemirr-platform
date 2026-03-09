package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 对话类型枚举
 * <p>
 * 定义平台支持的各种对话模式，用于区分不同的对话场景和处理逻辑。
 * </p>
 *
 * <h3>对话类型说明</h3>
 * <ul>
 *   <li><b>NORMAL_TEXT</b>：普通文本对话，无特殊增强</li>
 *   <li><b>GENERAL_AGENT</b>：通用智能体对话，支持工具调用</li>
 *   <li><b>PLATFORM_AGENT</b>：平台智能体，集成平台特定功能</li>
 *   <li><b>KNOWLEDGE_BASE</b>：知识库对话，基于 RAG 检索增强</li>
 *   <li><b>IMAGE_GENERATION</b>：图片生成对话</li>
 * </ul>
 *
 * @author Levin
 * @since 2025/10/11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "对话类型枚举")
public enum ConversationType implements DictEnum<Integer> {

    /**
     * 普通文本对话
     * <p>
     * 基础的文本对话模式，不带任何增强功能。
     * </p>
     */
    NORMAL_TEXT(1, "普通文本对话"),

    /**
     * 通用智能体对话
     * <p>
     * 支持工具调用的智能体对话模式。
     * </p>
     */
    GENERAL_AGENT(2, "通用智能体对话"),

    /**
     * 平台智能体
     * <p>
     * 集成平台特定功能的智能体，如菜单查询、用户管理等。
     * </p>
     */
    PLATFORM_AGENT(3, "平台智能体"),

    /**
     * 知识库对话
     * <p>
     * 基于 RAG（检索增强生成）的知识库问答模式。
     * </p>
     */
    KNOWLEDGE_BASE(4, "知识库对话"),

    /**
     * 图片生成
     * <p>
     * 根据文本描述生成图片的对话模式。
     * </p>
     */
    IMAGE_GENERATION(5, "图片生成");

    /**
     * 类型编码（用于数据库存储）
     */
    @EnumValue
    @JsonValue
    private Integer value;

    /**
     * 类型描述
     */
    @Schema(description = "类型描述")
    private String label;

    /**
     * 根据编码获取对话类型
     *
     * @param value 类型编码
     * @return 对应的对话类型，未找到返回 null
     */
    @JsonCreator
    public static ConversationType of(Integer value) {
        if (value == null) {
            return null;
        }
        for (ConversationType type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
