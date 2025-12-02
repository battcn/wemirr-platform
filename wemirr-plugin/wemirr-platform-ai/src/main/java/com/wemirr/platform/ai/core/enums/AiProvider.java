package com.wemirr.platform.ai.core.enums;

import lombok.Getter;

/**
 * AI模型提供商类型枚举
 */
@Getter
public enum AiProvider {

    /**
     * DeepSeek
     */
    DEEP_SEEK("deepseek", "DeepSeek", "深度求索", true, new String[]{"deepseek-chat-r1", "deepseek-chat-r3"}, ModelType.TEXT, ModelType.EMBEDDING),

    /**
     * OpenAI
     */
    OPEN_AI("openai", "OpenAI", "开放人工智能", true, new String[]{"gpt-3.5-turbo", "gpt-4", "gpt-4o"}, ModelType.TEXT, ModelType.EMBEDDING),

    /**
     * 通义千问（Qwen）
     */
    QWEN("qwen", "Qwen", "通义千问", true, new String[]{"qwen-max", "qwen-plus", "qwen-turbo","text-embedding-v2"}, ModelType.TEXT, ModelType.EMBEDDING),

    /**
     * 百度文心一言
     */
    ERNIE("ernie", "ERNIE", "文心一言", true, new String[]{"ernie-4.5", "ernie-3.5"}, ModelType.TEXT);

    /**
     * 英文标识（用于配置、API、缓存 key）
     */
    private final String code;

    /**
     * 英文显示名
     */
    private final String enName;

    /**
     * 中文显示名
     */
    private final String cnName;

    /**
     * 是否启用（可用于动态开关）
     */
    private final boolean enabled;

    /**
     * 支持的模型列表（可选，用于校验或前端展示）
     */
    private final String[] supportedModels;

    /**
     * 支持的模型类型
     */
    private final ModelType[] supportedTypes;




    AiProvider(String code, String enName, String cnName, boolean enabled, String[] supportedModels, ModelType... supportedTypes) {
        this.code = code;
        this.enName = enName;
        this.cnName = cnName;
        this.enabled = enabled;
        this.supportedModels = supportedModels != null ? supportedModels.clone() : new String[0];
        this.supportedTypes = supportedTypes != null ? supportedTypes.clone() : new ModelType[0];
    }

    /**
     * 根据 code 查找枚举（忽略大小写）
     */
    public static AiProvider fromCode(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        for (AiProvider provider : values()) {
            if (provider.code.equalsIgnoreCase(code)) {
                return provider;
            }
        }
        return null;
    }

    /**
     * 判断是否支持某个模型名（简单前缀或精确匹配）
     */
    public boolean supportsModel(String modelName) {
        if (modelName == null) {
            return false;
        }
        for (String model : supportedModels) {
            if (modelName.equals(model) || modelName.startsWith(model + "-")) {
                return true;
            }
        }
        return false;
    }

    public boolean supportsType(ModelType type) {
        if (type == null) {
            return false;
        }
        for (ModelType t : supportedTypes) {
            if (t == type) {
                return true;
            }
        }
        return false;
    }
}