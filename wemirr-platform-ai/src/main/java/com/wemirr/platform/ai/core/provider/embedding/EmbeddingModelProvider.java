package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.embedding.EmbeddingModel;

/**
 * @author xJh
 * @since 2025/10/12
 **/
public interface EmbeddingModelProvider {

    /**
     * 是否支持该配置
     */
    boolean supports(ModelConfig config);

    /**
     * 创建向量模型
     */
    EmbeddingModel createModel(ModelConfig config);

    /**
     * 获取提供商标识（使用枚举 code）
     */
    String providerId();
}