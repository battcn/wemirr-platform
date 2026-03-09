package com.wemirr.framework.ai.core.provider.embedding;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.model.ModelConfig;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;

/**
 * Qwen 向量模型工厂
 *
 * @author Levin
 * @since 2025/10/12
 */
public class QwenEmbeddingModelFactory implements EmbeddingModelFactory {

    @Override
    public boolean supports(ModelConfig config) {
        return AiProvider.QWEN.getValue().equalsIgnoreCase(config.getProviderCode())
                && AiProvider.QWEN.supportsModel(config.getName());
    }

    @Override
    public EmbeddingModel createModel(ModelConfig config) {
        return QwenEmbeddingModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getName())
                .build();
    }

    @Override
    public AiProvider getProviderType() {
        return AiProvider.QWEN;
    }
}
