package com.wemirr.platform.ai.core.provider;

import com.wemirr.platform.ai.core.enums.ModelType;
import com.wemirr.platform.ai.domain.entity.ModelConfig;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ModelProvider<T> {

    String providerId();

    ModelType modelType();

    /**
     * 检查配置是否支持（编译期类型安全）
     */
    boolean supportsConfig(ModelConfig config);

    /**
     * 创建模型实例（返回泛型类型 T）
     */
    T createModel(ModelConfig config);
}
