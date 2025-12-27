package com.wemirr.framework.ai.core.provider.embedding;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.model.ModelConfig;
import dev.langchain4j.model.embedding.EmbeddingModel;

/**
 * 向量模型工厂接口
 *
 * @author Levin
 * @since 2025/10/12
 */
public interface EmbeddingModelFactory {

    /**
     * 是否支持该配置
     */
    boolean supports(ModelConfig config);

    /**
     * 创建向量模型
     */
    EmbeddingModel createModel(ModelConfig config);

    /**
     * 获取支持的提供商类型
     */
    AiProvider getProviderType();
}
