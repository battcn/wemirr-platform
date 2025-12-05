package com.wemirr.platform.ai.core.enums;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public enum ModelParam {

    // 文本模型参数
    MAX_TOKENS("max_tokens", ModelType.TEXT, Integer.class),
    TEMPERATURE("temperature", ModelType.TEXT, Double.class),
    TOP_P("top_p", ModelType.TEXT, Double.class),
    FREQUENCY_PENALTY("frequency_penalty", ModelType.TEXT, Double.class),
    PRESENCE_PENALTY("presence_penalty", ModelType.TEXT, Double.class),
    STOP("stop", ModelType.TEXT, String[].class),

    // 向量模型参数
    DIMENSIONS("dimensions", ModelType.EMBEDDING, Integer.class),

    // 图像模型参数
    IMAGE_SIZE("image_size", ModelType.IMAGE, String.class),
    IMAGE_QUALITY("image_quality", ModelType.IMAGE, String.class),
    IMAGE_STYLE("image_style", ModelType.IMAGE, String.class),

    // 音频模型参数
    RESPONSE_FORMAT("response_format", ModelType.AUDIO, String.class),
    LANGUAGE("language", ModelType.AUDIO, String.class);

    private final String key;
    private final ModelType modelType;
    private final Class<?> valueType;

    ModelParam(String key, ModelType modelType, Class<?> valueType) {
        this.key = key;
        this.modelType = modelType;
        this.valueType = valueType;
    }

    public String getKey() {
        return key;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    /**
     * 从 variables Map 中安全获取值
     */
    @SuppressWarnings("unchecked")
    public <T> T getValueFrom(Map<String, Object> variables) {
        if (variables == null) {
            return null;
        }
        Object value = variables.get(key);
        if (value == null) {
            return null;
        }

        // 允许字符串自动转基础类型（常见于 YAML/JSON 配置）
        if (value instanceof String str) {
            try {
                if (valueType == Integer.class) {
                    return (T) Integer.valueOf(str);
                } else if (valueType == Double.class) {
                    return (T) Double.valueOf(str);
                }
                // 其他类型保持原样（String 本身没问题）
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Invalid value for param '" + key + "': cannot parse '" + str + "' as " + valueType.getSimpleName());
            }
        }

        if (!valueType.isInstance(value)) {
            throw new IllegalArgumentException(
                    "Param '" + key + "' expects " + valueType.getSimpleName() +
                            ", but got " + value.getClass().getSimpleName());
        }
        return (T) value;
    }

    /**
     * 判断该参数是否适用于指定模型类型
     */
    public boolean appliesTo(ModelType type) {
        return this.modelType == type;
    }
}
