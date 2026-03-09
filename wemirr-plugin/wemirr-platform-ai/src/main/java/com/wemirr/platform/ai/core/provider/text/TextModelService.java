package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.framework.ai.core.provider.text.TextModelProvider;
import com.wemirr.framework.ai.core.provider.text.TextModelProviderRegistry;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 文本模型服务（业务层）
 *
 * @author xJh
 * @since 2025/10/11
 */
@Service
@RequiredArgsConstructor
public class TextModelService {

    private final TextModelCache cache;
    private final TextModelProviderRegistry registry;

    public ChatModel model(ModelEntity config) {
        return cache.getModel(config);
    }

    public StreamingChatModel streamModel(ModelEntity config) {
        return cache.getStreamModel(config);
    }

    public TextModelProvider getProvider(ModelEntity config) {
        return registry.getProvider(config);
    }

    public boolean isProviderAvailable(String providerId) {
        return registry.getAvailableProviderIds().contains(providerId.toLowerCase());
    }
}
