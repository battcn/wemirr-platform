package com.wemirr.framework.ai.core.provider.embedding;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.model.ModelConfig;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;

/**
 * OpenAI 向量模型工厂
 *
 * @author Levin
 * @since 2025/10/12
 */
public class OpenAiEmbeddingModelFactory implements EmbeddingModelFactory {

    @Override
    public boolean supports(ModelConfig config) {
        return AiProvider.OPEN_AI.getValue().equalsIgnoreCase(config.getProviderCode());
    }

    @Override
    public EmbeddingModel createModel(ModelConfig config) {
        OpenAiEmbeddingModel.OpenAiEmbeddingModelBuilder builder = OpenAiEmbeddingModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getName());

        if (config.getBaseUrl() != null && !config.getBaseUrl().isBlank()) {
            builder.baseUrl(config.getBaseUrl());
        }

        return builder.build();
    }

    @Override
    public AiProvider getProviderType() {
        return AiProvider.OPEN_AI;
    }
}
