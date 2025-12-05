package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

/**
 * @author xJh
 * @since 2025/10/12
 **/
@Service
public class EmbeddingModelService {

    private final EmbeddingModelProviderRegistry registry;

    public EmbeddingModelService(EmbeddingModelProviderRegistry registry) {
        this.registry = registry;
    }

    /**
     * 根据配置获取 EmbeddingModel 实例
     *
     * @param config 模型配置
     * @return EmbeddingModel 实例
     */
    public EmbeddingModel getModel(ModelConfig config) {
        EmbeddingModelProvider provider = registry.getProvider(config);
        if (provider == null) {
            throw new IllegalArgumentException("未找到支持的向量模型提供商: " + config.getProvider());
        }
        return provider.createModel(config);
    }
}