package com.wemirr.framework.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AI 模型提供商枚举
 *
 * @author Levin
 * @since 2025/10/09
 */
@Getter
@AllArgsConstructor
public enum AiProvider implements DictEnum<String> {

    DEEP_SEEK("deepseek", "DeepSeek", "深度求索", true,
            new ModelCapability[]{
                    new ModelCapability("deepseek-chat-r1", true, false, ModelType.TEXT),
                    new ModelCapability("deepseek-chat-r3", true, false, ModelType.TEXT)
            },
            new ModelType[]{ModelType.TEXT, ModelType.EMBEDDING}
    ),

    OPEN_AI("openai", "OpenAI", "开放人工智能", true,
            new ModelCapability[]{
                    new ModelCapability("gpt-3.5-turbo", false, false, ModelType.TEXT),
                    new ModelCapability("gpt-4", true, false, ModelType.TEXT),
                    new ModelCapability("gpt-4o", true, false, ModelType.TEXT)
            },
            new ModelType[]{ModelType.TEXT, ModelType.EMBEDDING}
    ),

    QWEN("qwen", "Qwen", "通义千问", true,
            new ModelCapability[]{
                    new ModelCapability("qwen-turbo", false, true, ModelType.TEXT),
                    new ModelCapability("qwen-plus", true, true, ModelType.TEXT),
                    new ModelCapability("qwen-max", true, true, ModelType.TEXT),
                    new ModelCapability("text-embedding-v2", false, false, ModelType.EMBEDDING)
            },
            new ModelType[]{ModelType.TEXT, ModelType.EMBEDDING}
    ),

    ERNIE("ernie", "ERNIE", "文心一言", true,
            new ModelCapability[]{
                    new ModelCapability("ernie-3.5", false, false, ModelType.TEXT),
                    new ModelCapability("ernie-4.5", true, false, ModelType.TEXT)
            },
            new ModelType[]{ModelType.TEXT}
    );

    @EnumValue
    @JsonValue
    private final String value;
    private final String enName;
    private final String label;
    private final boolean enabled;
    private final ModelCapability[] modelCapabilities;
    private final ModelType[] supportedTypes;

    public boolean supportsDeepThinking(String modelName) {
        return findCapability(modelName).map(ModelCapability::supportsDeepThinking).orElse(false);
    }

    public boolean supportsWebSearch(String modelName) {
        return findCapability(modelName).map(ModelCapability::supportsWebSearch).orElse(false);
    }

    public boolean supportsModel(String modelName) {
        return findCapability(modelName).isPresent();
    }

    public boolean supportsType(ModelType type) {
        return type != null && Arrays.asList(supportedTypes).contains(type);
    }

    public List<String> getModelsByType(ModelType type) {
        if (type == null) return Collections.emptyList();
        return Arrays.stream(modelCapabilities)
                .filter(cap -> cap.type() == type)
                .map(ModelCapability::modelName)
                .collect(Collectors.toList());
    }

    public static AiProvider of(String code) {
        if (code == null || code.isBlank()) return null;
        for (AiProvider provider : values()) {
            if (provider.value.equalsIgnoreCase(code)) {
                return provider;
            }
        }
        return null;
    }

    public static List<AiProvider> getEnabledProviders() {
        return Arrays.stream(values()).filter(AiProvider::isEnabled).collect(Collectors.toList());
    }

    private Optional<ModelCapability> findCapability(String modelName) {
        if (modelName == null) return Optional.empty();
        return Arrays.stream(modelCapabilities)
                .filter(cap -> modelName.equals(cap.modelName()) || modelName.startsWith(cap.modelName() + "-"))
                .findFirst();
    }

    public record ModelCapability(
            String modelName,
            boolean supportsDeepThinking,
            boolean supportsWebSearch,
            ModelType type
    ) {}
}
