package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Component;

/**
 * @author xJh
 * @since 2025/10/12
 **/
@Component
public class OpenAiEmbeddingModelProvider implements EmbeddingModelProvider {

    @Override
    public boolean supports(ModelEntity config) {
        // 1. 检查提供商是否为 OpenAI
        // 2. 检查模型是否支持
        return AiProvider.OPEN_AI.equals(AiProvider.fromCode(config.getProvider()))
                && AiProvider.OPEN_AI.supportsModel(config.getName());
    }

    @Override
    public EmbeddingModel createModel(ModelEntity config) {
        return OpenAiEmbeddingModel.builder()
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .modelName(config.getName())
                .build();
    }

    @Override
    public String providerId() {
        return AiProvider.OPEN_AI.getCode();
    }
}