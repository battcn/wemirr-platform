package com.wemirr.platform.ai.core.provider.scoring;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 重排序模型提供者注册中心
 * <p>
 * 管理所有重排序模型提供者的注册和查找
 *
 * @author xJh
 * @since 2025/12/18
 */
@Slf4j
@Component
public class ScoringModelProviderRegistry {

    private final Map<String, ScoringModelProvider> providerMap = new HashMap<>();
    private final List<ScoringModelProvider> providers;

    public ScoringModelProviderRegistry(List<ScoringModelProvider> providers) {
        this.providers = providers;
    }

    @PostConstruct
    public void init() {
        for (ScoringModelProvider provider : providers) {
            if (providerMap.containsKey(provider.providerId())) {
                throw new IllegalArgumentException(
                        String.format("重复注册重排序模型提供商: %s", provider.providerId())
                );
            }
            providerMap.put(provider.providerId(), provider);
            log.info("注册重排序模型提供者: {}", provider.providerId());
        }
        log.info("重排序模型提供者注册完成，共 {} 个", providers.size());
    }

    /**
     * 根据配置查找匹配的提供者
     *
     * @param config 模型配置
     * @return 匹配的提供者
     */
    public ScoringModelProvider getProvider(ModelConfig config) {
        if (config.getProvider() == null) {
            throw new IllegalArgumentException("模型提供商不能为空");
        }
        if (config.getModelName() == null) {
            throw new IllegalArgumentException("模型名称不能为空");
        }

        for (ScoringModelProvider provider : providers) {
            if (provider.supports(config)) {
                return provider;
            }
        }

        throw new IllegalArgumentException(
                String.format("未找到支持的重排序模型提供商: provider=%s, model=%s",
                        config.getProvider(), config.getModelName())
        );
    }

    /**
     * 获取所有可用的提供商 ID
     *
     * @return 提供商 ID 列表
     */
    public List<String> getAvailableProviderIds() {
        return providers.stream()
                .map(ScoringModelProvider::providerId)
                .filter(id -> {
                    try {
                        return AiProvider.fromCode(id).isEnabled();
                    } catch (Exception e) {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * 检查是否有可用的重排序模型提供者
     *
     * @return 是否可用
     */
    public boolean hasAvailableProviders() {
        return !providers.isEmpty();
    }
}
