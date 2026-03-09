package com.wemirr.framework.ai.core.provider.text;

import com.wemirr.framework.ai.core.constant.AiConstants;
import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.exception.ProviderNotFoundException;
import com.wemirr.framework.ai.core.model.ModelConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文本模型提供者注册中心
 *
 * @author Levin
 * @since 2025/10/11
 */
@Slf4j
@RequiredArgsConstructor
public class TextModelProviderRegistry {

    private final Map<String, TextModelProvider> providerMap = new HashMap<>();
    private final List<TextModelProvider> providers;

    @PostConstruct
    public void init() {
        log.info("开始注册文本模型提供者，总数: {}", providers.size());

        for (TextModelProvider provider : providers) {
            String providerId = provider.providerId();
            if (providerMap.containsKey(providerId)) {
                throw new IllegalArgumentException(String.format(AiConstants.ERROR_DUPLICATE_PROVIDER, providerId));
            }
            providerMap.put(providerId, provider);
            log.info("注册文本模型提供者成功: {}", providerId);
        }

        log.info("文本模型提供者注册完成，已注册: {}", providerMap.keySet());
    }

    public TextModelProvider getProvider(ModelConfig config) {
        if (config.getProviderCode() == null) {
            throw new IllegalArgumentException("模型提供商不能为空");
        }
        if (config.getName() == null) {
            throw new IllegalArgumentException("模型名称不能为空");
        }

        for (TextModelProvider provider : providers) {
            if (provider.supports(config)) {
                return provider;
            }
        }

        return findDefaultProvider();
    }

    private TextModelProvider findDefaultProvider() {
        return providers.stream()
                .filter(p -> {
                    AiProvider aiProvider = AiProvider.of(p.providerId());
                    return aiProvider != null && aiProvider.isEnabled();
                })
                .findFirst()
                .orElseThrow(() -> new ProviderNotFoundException("default"));
    }

    public List<String> getAvailableProviderIds() {
        return providers.stream()
                .map(TextModelProvider::providerId)
                .filter(id -> {
                    AiProvider provider = AiProvider.of(id);
                    return provider != null && provider.isEnabled();
                })
                .collect(Collectors.toList());
    }
}
