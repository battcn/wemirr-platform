package com.wemirr.framework.ai.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * 模型参数枚举
 *
 * @author Levin
 * @since 2025/10/11
 */
@Getter
@AllArgsConstructor
public enum ModelParam {

    // 文本模型参数
    MAX_TOKENS("max_tokens", ModelType.TEXT, Integer.class, "最大Token数"),
    TEMPERATURE("temperature", ModelType.TEXT, Double.class, "温度"),
    TOP_P("top_p", ModelType.TEXT, Double.class, "Top-P"),
    FREQUENCY_PENALTY("frequency_penalty", ModelType.TEXT, Double.class, "频率惩罚"),
    PRESENCE_PENALTY("presence_penalty", ModelType.TEXT, Double.class, "存在惩罚"),
    STOP("stop", ModelType.TEXT, String[].class, "停止词"),

    // 向量模型参数
    DIMENSIONS("dimensions", ModelType.EMBEDDING, Integer.class, "向量维度"),

    // 图像模型参数
    IMAGE_SIZE("image_size", ModelType.IMAGE, String.class, "图像尺寸"),
    IMAGE_QUALITY("image_quality", ModelType.IMAGE, String.class, "图像质量"),
    IMAGE_STYLE("image_style", ModelType.IMAGE, String.class, "图像风格"),

    // 音频模型参数
    RESPONSE_FORMAT("response_format", ModelType.AUDIO, String.class, "响应格式"),
    LANGUAGE("language", ModelType.AUDIO, String.class, "语言");

    private final String key;
    private final ModelType modelType;
    private final Class<?> valueType;
    private final String description;

    @SuppressWarnings("unchecked")
    public <T> T getValueFrom(Map<String, Object> variables) {
        if (variables == null) return null;
        Object value = variables.get(key);
        if (value == null) return null;

        if (value instanceof String str) {
            try {
                if (valueType == Integer.class) return (T) Integer.valueOf(str);
                if (valueType == Double.class) return (T) Double.valueOf(str);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        String.format("参数 '%s' 值无效：无法将 '%s' 转换为 %s", key, str, valueType.getSimpleName()));
            }
        }

        if (!valueType.isInstance(value)) {
            throw new IllegalArgumentException(
                    String.format("参数 '%s' 类型错误：期望 %s，实际 %s", key, valueType.getSimpleName(), value.getClass().getSimpleName()));
        }
        return (T) value;
    }

    public <T> T getValueFrom(Map<String, Object> variables, T defaultValue) {
        T value = getValueFrom(variables);
        return value != null ? value : defaultValue;
    }

    public boolean appliesTo(ModelType type) {
        return this.modelType == type;
    }
}
