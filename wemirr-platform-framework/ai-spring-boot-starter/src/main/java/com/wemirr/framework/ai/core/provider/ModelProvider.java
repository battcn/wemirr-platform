package com.wemirr.framework.ai.core.provider;

import com.wemirr.framework.ai.core.enums.ModelType;
import com.wemirr.framework.ai.core.model.ModelConfig;

/**
 * 模型提供者统一接口
 * <p>
 * 采用策略模式定义模型创建的统一契约
 * </p>
 *
 * @param <T> 模型实例类型
 * @author Levin
 * @since 2025/10/11
 */
public interface ModelProvider<T> {

    /**
     * 获取提供商唯一标识
     *
     * @return 提供商标识（如 "qwen"、"openai"、"deepseek"）
     */
    String getProviderId();

    /**
     * 获取支持的模型类型
     *
     * @return 模型类型枚举
     */
    ModelType getModelType();

    /**
     * 检查是否支持指定的模型配置
     *
     * @param config 模型配置
     * @return 是否支持
     */
    boolean supports(ModelConfig config);

    /**
     * 创建模型实例
     *
     * @param config 模型配置
     * @return 模型实例
     */
    T createModel(ModelConfig config);

    /**
     * 获取提供商 ID（别名方法）
     */
    default String providerId() {
        return getProviderId();
    }
}
