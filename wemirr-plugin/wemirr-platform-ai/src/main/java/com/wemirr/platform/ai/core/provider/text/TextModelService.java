package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
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

    public ChatLanguageModel model(ModelConfig config) {
        return cache.getModel(config);
    }

    public StreamingChatLanguageModel streamModel(ModelConfig config) {
        return cache.getStreamModel(config);
    }

    public Tokenizer tokenizer(ModelConfig config) {
        return cache.getTokenizer(config);
    }

    public TextModelProvider getProvider(ModelConfig config) {
        return registry.getProvider(config);
    }

    public boolean isProviderAvailable(String providerId) {
        return registry.getAvailableProviderIds().contains(providerId.toLowerCase());
    }
}
