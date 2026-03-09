package com.wemirr.platform.ai.core.provider.text;

import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.ai.core.provider.text.TextModelProvider;
import com.wemirr.framework.ai.core.provider.text.TextModelProviderRegistry;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 文本模型缓存
 *
 * @author xJh
 * @since 2025/10/11
 */
@Component
public class TextModelCache {

    @Cacheable(value = "chatModels", key = "#config.cacheKey()")
    public ChatModel getModel(ModelEntity config) {
        return getProvider(config).createModel(config);
    }

    @Cacheable(value = "streamingChatModels", key = "#config.cacheKey()")
    public StreamingChatModel getStreamModel(ModelEntity config) {
        return getProvider(config).createStreamingModel(config);
    }

    private TextModelProvider getProvider(ModelEntity config) {
        return SpringUtil.getBean(TextModelProviderRegistry.class).getProvider(config);
    }

    /**
     * 生成缓存 key
     */
    public static String generateCacheKey(ModelEntity config) {
        return String.format("%s:%s:%s",
                config.getProviderCode(),
                config.getName(),
                config.getBaseUrl() != null ? config.getBaseUrl().replace(":", "_") : "default"
        );
    }
}
