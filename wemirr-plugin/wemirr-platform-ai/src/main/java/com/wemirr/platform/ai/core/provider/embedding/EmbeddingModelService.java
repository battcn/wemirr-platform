package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.framework.ai.core.provider.embedding.EmbeddingModelFactory;
import com.wemirr.framework.ai.core.provider.embedding.EmbeddingModelRegistry;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 向量模型服务（业务层）
 *
 * @author xJh
 * @since 2025/10/12
 */
@Service
@RequiredArgsConstructor
public class EmbeddingModelService {

    private final EmbeddingModelRegistry registry;

    /**
     * 根据配置获取 EmbeddingModel 实例
     *
     * @param config 模型配置
     * @return EmbeddingModel 实例
     */
    public EmbeddingModel getModel(ModelEntity config) {
        EmbeddingModelFactory factory = registry.getFactory(config);
        if (factory == null) {
            throw new IllegalArgumentException("未找到支持的向量模型提供商: " + config.getProviderCode());
        }
        return factory.createModel(config);
    }
}
