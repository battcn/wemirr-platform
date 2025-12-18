package com.wemirr.platform.ai.core.provider.scoring;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.scoring.ScoringModel;

/**
 * 重排序模型提供者接口
 * <p>
 * 定义重排序模型的通用抽象，支持多种重排序服务（Jina、Cohere、OpenAI 等）
 *
 * @author xJh
 * @since 2025/12/18
 */
public interface ScoringModelProvider {

    /**
     * 是否支持该配置
     *
     * @param config 模型配置
     * @return 是否支持
     */
    boolean supports(ModelConfig config);

    /**
     * 创建重排序模型实例
     *
     * @param config 模型配置
     * @return ScoringModel 实例
     */
    ScoringModel createModel(ModelConfig config);

    /**
     * 获取提供商标识
     *
     * @return 提供商 ID
     */
    String providerId();
}
