package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Component;

/**
 * @author xJh
 * @since 2025/10/12
 **/
@Component
public class DeepSeekEmbeddingModelProvider implements EmbeddingModelProvider {

    @Override
    public boolean supports(ModelConfig config) {
        // 1. 检查提供商是否为 DeepSeek
        // 2. 检查模型是否支持
        return AiProvider.DEEP_SEEK.equals(AiProvider.fromCode(config.getProvider()))
                && AiProvider.DEEP_SEEK.supportsModel(config.getModelName());
    }

    @Override
    public EmbeddingModel createModel(ModelConfig config) {
        return OpenAiEmbeddingModel.builder()
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .modelName(config.getModelName())
                .build();
    }

    @Override
    public String providerId() {
        return AiProvider.DEEP_SEEK.getCode();
    }
}