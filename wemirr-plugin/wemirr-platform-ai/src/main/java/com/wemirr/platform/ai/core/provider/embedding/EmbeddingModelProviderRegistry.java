package com.wemirr.platform.ai.core.provider.embedding;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xJh
 * @since 2025/10/12
 **/
@Component
public class EmbeddingModelProviderRegistry {

    private final Map<String, EmbeddingModelProvider> providerMap = new HashMap<>();
    private final List<EmbeddingModelProvider> providers;

    public EmbeddingModelProviderRegistry(List<EmbeddingModelProvider> providers) {
        this.providers = providers;
    }

    @PostConstruct
    public void init() {
        // 1. 注册所有提供者
        // 2. 验证重复注册
        for (EmbeddingModelProvider provider : providers) {
            if (providerMap.containsKey(provider.providerId())) {
                throw new IllegalArgumentException(
                        String.format("重复注册模型提供商: %s", provider.providerId())
                );
            }
            providerMap.put(provider.providerId(), provider);
        }
    }

    /**
     * 根据配置查找匹配的提供者
     */
    public EmbeddingModelProvider getProvider(ModelConfig config) {
        // 1. 检查配置是否合法
        if (config.getProvider() == null) {
            throw new IllegalArgumentException("模型提供商不能为空");
        }
        if (config.getModelName() == null) {
            throw new IllegalArgumentException("模型名称不能为空");
        }

        // 2. 尝试匹配提供商
        for (EmbeddingModelProvider provider : providers) {
            if (provider.supports(config)) {
                return provider;
            }
        }

        // 3. 如果没找到，返回可用的默认提供商 todo 删除默认提供商，没有就报错
        return findDefaultProvider();
    }

    /**
     * 获取默认提供商（优先启用的）
     */
    private EmbeddingModelProvider findDefaultProvider() {
        return providers.stream()
                .filter(p -> AiProvider.fromCode(p.providerId()).isEnabled())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("没有可用的向量模型提供商"));
    }

    /**
     * 获取所有启用的提供商 ID
     */
    public List<String> getAvailableProviderIds() {
        return providers.stream()
                .map(EmbeddingModelProvider::providerId)
                .filter(id -> AiProvider.fromCode(id).isEnabled())
                .collect(Collectors.toList());
    }
}