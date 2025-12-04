package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import org.springframework.stereotype.Service;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Service
public class TextModelService {

    private final TextModelCache cache;
    private final TextModelProviderRegistry registry;

    public TextModelService(TextModelCache cache, TextModelProviderRegistry registry) {
        this.cache = cache;
        this.registry = registry;
    }

    public ChatModel model(ModelConfig config) {
        return cache.getModel(config);
    }

    public StreamingChatModel streamModel(ModelConfig config) {
        return cache.getStreamModel(config);
    }

    public TextModelProvider getProvider(ModelConfig config) {
        return registry.getProvider(config);
    }

    public boolean isProviderAvailable(String providerId) {
        return registry.getAvailableProviderIds().contains(providerId.toLowerCase());
    }
}
