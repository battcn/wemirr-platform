package com.wemirr.framework.ai.core.provider.text;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.model.ModelConfig;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;

/**
 * 通义千问文本模型提供者
 *
 * @author Levin
 * @since 2025/10/11
 */
public class QwenTextModelProvider extends AbstractTextModelProvider {

    @Override
    protected AiProvider getAiProvider() {
        return AiProvider.QWEN;
    }

    @Override
    public ChatModel createModel(ModelConfig config) {
        logModelCreation(config, false);

        QwenChatModel.QwenChatModelBuilder builder = QwenChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getName());

        Integer maxTokens = extractMaxTokens(config);
        if (maxTokens != null) {
            builder.maxTokens(maxTokens);
        }

        if (isWebSearchEnabled(config)) {
            builder.enableSearch(true);
        }

        return builder.build();
    }

    @Override
    public StreamingChatModel createStreamingModel(ModelConfig config) {
        logModelCreation(config, true);

        QwenStreamingChatModel.QwenStreamingChatModelBuilder builder = QwenStreamingChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getName());

        Integer maxTokens = extractMaxTokens(config);
        if (maxTokens != null) {
            builder.maxTokens(maxTokens);
        }

        if (isWebSearchEnabled(config)) {
            builder.enableSearch(true);
        }

        return builder.build();
    }
}
