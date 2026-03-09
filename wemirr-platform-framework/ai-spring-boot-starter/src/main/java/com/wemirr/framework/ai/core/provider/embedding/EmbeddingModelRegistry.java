package com.wemirr.framework.ai.core.provider.embedding;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.exception.ProviderNotFoundException;
import com.wemirr.framework.ai.core.model.ModelConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 向量模型提供者注册中心
 *
 * @author Levin
 * @since 2025/10/12
 */
@Slf4j
@RequiredArgsConstructor
public class EmbeddingModelRegistry {

    private final Map<AiProvider, EmbeddingModelFactory> providerMap = new HashMap<>();
    private final List<EmbeddingModelFactory> factories;

    @PostConstruct
    public void init() {
        for (EmbeddingModelFactory factory : factories) {
            if (providerMap.containsKey(factory.getProviderType())) {
                throw new IllegalArgumentException("重复注册向量模型提供商: " + factory.getProviderType());
            }
            providerMap.put(factory.getProviderType(), factory);
            log.info("注册向量模型提供者: {}", factory.getProviderType());
        }
    }

    public EmbeddingModelFactory getFactory(ModelConfig config) {
        if (config.getProviderCode() == null) {
            throw new IllegalArgumentException("模型提供商不能为空");
        }
        if (config.getName() == null) {
            throw new IllegalArgumentException("模型名称不能为空");
        }

        for (EmbeddingModelFactory factory : factories) {
            if (factory.supports(config)) {
                return factory;
            }
        }

        return findDefaultFactory();
    }

    private EmbeddingModelFactory findDefaultFactory() {
        return factories.stream()
                .filter(f -> f.getProviderType().isEnabled())
                .findFirst()
                .orElseThrow(() -> new ProviderNotFoundException("embedding"));
    }
}
