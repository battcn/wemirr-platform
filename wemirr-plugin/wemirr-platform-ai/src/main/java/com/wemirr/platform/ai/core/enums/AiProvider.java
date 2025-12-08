package com.wemirr.platform.ai.core.enums;

import lombok.Getter;

/**
 * AI模型提供商类型枚举
 */
@Getter
public enum AiProvider {

    DEEP_SEEK("deepseek", "DeepSeek", "深度求索", true,
            new ModelCapability[]{
                    new ModelCapability("deepseek-chat-r1", true,false, ModelType.TEXT),
                    new ModelCapability("deepseek-chat-r3", true,false,ModelType.TEXT)
            },
            ModelType.TEXT, ModelType.EMBEDDING
    ),

    OPEN_AI("openai", "OpenAI", "开放人工智能", true,
            new ModelCapability[]{
                    new ModelCapability("gpt-3.5-turbo", false,false, ModelType.TEXT),
                    new ModelCapability("gpt-4", true,false, ModelType.TEXT),
                    new ModelCapability("gpt-4o", true,false, ModelType.TEXT)
            },
            ModelType.TEXT, ModelType.EMBEDDING
    ),

    QWEN("qwen", "Qwen", "通义千问", true,
            new ModelCapability[]{
                    new ModelCapability("qwen-turbo", false,true, ModelType.TEXT),
                    new ModelCapability("qwen-plus", true,true, ModelType.TEXT),
                    new ModelCapability("qwen-max", true,true, ModelType.TEXT),
                    new ModelCapability("text-embedding-v2", false,false, ModelType.EMBEDDING)
            },
            ModelType.TEXT, ModelType.EMBEDDING
    ),

    ERNIE("ernie", "ERNIE", "文心一言", true,
            new ModelCapability[]{
                    new ModelCapability("ernie-3.5", false,false, ModelType.TEXT),
                    new ModelCapability("ernie-4.5", true,false, ModelType.TEXT)
            },
            ModelType.TEXT
    );

    private final String code;
    private final String enName;
    private final String cnName;
    private final boolean enabled;
    private final ModelCapability[] modelCapabilities;
    private final ModelType[] supportedTypes;

    AiProvider(String code, String enName, String cnName, boolean enabled,
               ModelCapability[] modelCapabilities, ModelType... supportedTypes) {
        this.code = code;
        this.enName = enName;
        this.cnName = cnName;
        this.enabled = enabled;
        this.modelCapabilities = modelCapabilities != null ? modelCapabilities.clone() : new ModelCapability[0];
        this.supportedTypes = supportedTypes != null ? supportedTypes.clone() : new ModelType[0];
    }

    // 根据模型名判断是否支持深度思考
    public boolean supportsDeepThinking(String modelName) {
        if (modelName == null){
            return false;
        }
        for (ModelCapability cap : modelCapabilities) {
            // 支持精确匹配或前缀匹配（如 qwen-max-latest）
            if (modelName.equals(cap.getModelName()) || modelName.startsWith(cap.getModelName() + "-")) {
                return cap.isSupportsDeepThinking();
            }
        }
        // 未知模型默认不支持
        return false;
    }

    // 根据模型名判断是否支持联网搜索
    public boolean supportsWebSearch(String modelName) {
        if (modelName == null){
            return false;
        }
        for (ModelCapability cap : modelCapabilities) {
            // 支持精确匹配或前缀匹配（如 qwen-max-latest）
            if (modelName.equals(cap.getModelName()) || modelName.startsWith(cap.getModelName() + "-")) {
                return cap.isSupportsWebSearch();
            }
        }
        // 未知模型默认不支持
        return false;
    }

    public boolean supportsModel(String modelName) {
        if (modelName == null){
            return false;
        }
        for (ModelCapability cap : modelCapabilities) {
            if (modelName.equals(cap.getModelName()) || modelName.startsWith(cap.getModelName() + "-")) {
                return true;
            }
        }
        return false;
    }

    public boolean supportsType(ModelType type) {
        if (type == null){
            return false;
        }
        for (ModelType t : supportedTypes) {
            if (t == type){
                return true;
            }
        }
        return false;
    }

    public static AiProvider fromCode(String code) {
        if (code == null || code.isBlank()){
            return null;
        }
        for (AiProvider provider : values()) {
            if (provider.code.equalsIgnoreCase(code)) {
                return provider;
            }
        }
        return null;
    }

    @Getter
    public static class ModelCapability {
        private final String modelName;
        private final boolean supportsDeepThinking;
        private final boolean supportsWebSearch;
        private final ModelType type;

        public ModelCapability(String modelName, boolean supportsDeepThinking,
                               boolean supportsWebSearch,
                               ModelType type) {
            this.modelName = modelName;
            this.supportsDeepThinking = supportsDeepThinking;
            this.supportsWebSearch = supportsWebSearch;
            this.type = type;
        }
    }
}