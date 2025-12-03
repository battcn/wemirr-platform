package com.wemirr.platform.ai.core.provider.text;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.dashscope.tokenizers.Tokenization;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Component
public class TextModelCache {
    // 模型实例缓存（key: providerId:modelName:baseUrl）
    private final Map<String, ChatModel> modelCache = new ConcurrentHashMap<>();
    private final Map<String, StreamingChatModel> streamModelCache = new ConcurrentHashMap<>();
    private final Map<String, Tokenization> tokenizerCache = new ConcurrentHashMap<>();

    @Cacheable(value = "chatModels", key = "#config.cacheKey()")
    public ChatModel getModel(ModelConfig config) {
        return getProvider(config).createModel(config);
    }

    @Cacheable(value = "streamingChatModels", key = "#config.cacheKey()")
    public StreamingChatModel getStreamModel(ModelConfig config) {
        return getProvider(config).createStreamModel(config);
    }



    private TextModelProvider getProvider(ModelConfig config) {
        return SpringUtil.getBean(TextModelProviderRegistry.class).getProvider(config);
    }

    /**
     * 生成缓存 key（避免重复创建）
     */
    public static String generateCacheKey(ModelConfig config) {
        return String.format("%s:%s:%s",
                config.getProvider().toLowerCase(),
                config.getModelName(),
                config.getBaseUrl().replace(":", "_")
        );
    }
}
