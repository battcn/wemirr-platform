package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.core.enums.ModelParam;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.listener.CustomizeChatModelListener;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Component
@RequiredArgsConstructor
public class QwenTextModelProvider implements TextModelProvider {

    private final CustomizeChatModelListener customizeChatModelListener;

    @Override
    public boolean supports(ModelConfig config) {
        // 1. 检查提供商是否为 QWEN
        // 2. 检查模型是否支持
        return AiProvider.QWEN.equals(AiProvider.fromCode(config.getProvider()))
                && AiProvider.QWEN.supportsModel(config.getModelName());
    }

    @Override
    public ChatModel createModel(ModelConfig config) {
        QwenChatModel.QwenChatModelBuilder builder  = QwenChatModel.builder()
//                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .listeners(List.of(customizeChatModelListener))
                .modelName(config.getModelName());
        Map<String, Object> vars = config.getVariables();
        Integer maxTokens = ModelParam.MAX_TOKENS.getValueFrom(vars);
        if (maxTokens != null) {
            builder.maxTokens(maxTokens);
        }
        if (AiProvider.QWEN.supportsWebSearch(config.getModelName())&& config.getEnableWebSearch()!= null && config.getEnableWebSearch()){
            builder.enableSearch(true);
        }
        return builder.build();
    }

    @Override
    public StreamingChatModel createStreamModel(ModelConfig config) {
        QwenStreamingChatModel.QwenStreamingChatModelBuilder builder = QwenStreamingChatModel.builder()
//                .baseUrl(config.getBaseUrl())
                .modelName(config.getModelName())
                .apiKey(config.getApiKey());
        Map<String, Object> vars = config.getVariables();
        Integer maxTokens = ModelParam.MAX_TOKENS.getValueFrom(vars);
        if (maxTokens != null) {
            builder.maxTokens(maxTokens);
        }
        if (AiProvider.QWEN.supportsWebSearch(config.getModelName())&& config.getEnableWebSearch()){
            builder.enableSearch(true);
        }
        return builder.build();
    }


    @Override
    public String providerId() {
        return AiProvider.QWEN.getCode();
    }

}
