package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import org.springframework.stereotype.Component;

/**
 * @author xJh
 * @since 2025/10/12
 **/
@Component
public class QwenEmbeddingModelProvider implements EmbeddingModelProvider {

    @Override
    public boolean supports(ModelConfig config) {
        // 1. 检查提供商是否为 QWEN
        // 2. 检查模型是否支持
        return AiProvider.QWEN.equals(AiProvider.fromCode(config.getProvider()))
                && AiProvider.QWEN.supportsModel(config.getModelName());
    }

    @Override
    public EmbeddingModel createModel(ModelConfig config) {
        return QwenEmbeddingModel.builder()
                .apiKey(config.getApiKey())
                //text-embedding-v2 默认模型
                .modelName(config.getModelName())
                .build();
    }

    @Override
    public String providerId() {
        return AiProvider.QWEN.getCode();
    }
}