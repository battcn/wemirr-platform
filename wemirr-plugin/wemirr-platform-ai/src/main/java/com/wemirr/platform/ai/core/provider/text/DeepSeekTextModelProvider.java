package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.core.enums.ModelParam;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Component
public class DeepSeekTextModelProvider implements TextModelProvider {

    @Override
    public boolean supports(ModelConfig config) {
        // 1. 检查提供商是否为 DeepSeek
        // 2. 检查模型是否支持
        return AiProvider.DEEP_SEEK.equals(AiProvider.fromCode(config.getProvider()))
                && AiProvider.DEEP_SEEK.supportsModel(config.getModelName());
    }

    @Override
    public ChatModel createModel(ModelConfig config) {
        OpenAiChatModel.OpenAiChatModelBuilder builder  = OpenAiChatModel.builder()
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .modelName(config.getModelName());
        Map<String, Object> vars = config.getVariables();
        Integer maxTokens = ModelParam.MAX_TOKENS.getValueFrom(vars);
        Double temperature = ModelParam.TEMPERATURE.getValueFrom(vars);
        Double topP = ModelParam.TOP_P.getValueFrom(vars);
        Double freqPenalty = ModelParam.FREQUENCY_PENALTY.getValueFrom(vars);
        Double presPenalty = ModelParam.PRESENCE_PENALTY.getValueFrom(vars);
        if (maxTokens != null) {
            builder.maxTokens(maxTokens);
        }
        if (temperature != null) {
            builder.temperature(temperature);
        }
        if (topP != null) {
            builder.topP(topP);
        }
        if (freqPenalty != null) {
            builder.frequencyPenalty(freqPenalty);
        }
        if (presPenalty != null) {
            builder.presencePenalty(presPenalty);
        }
        if (AiProvider.DEEP_SEEK.supportsDeepThinking(config.getModelName())&& config.getReturnThinking()){
            builder.returnThinking(true);
        }
        return builder.build();
    }

    @Override
    public StreamingChatModel createStreamModel(ModelConfig config) {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder  = OpenAiStreamingChatModel.builder()
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .modelName(config.getModelName());
        Map<String, Object> vars = config.getVariables();
        Integer maxTokens = ModelParam.MAX_TOKENS.getValueFrom(vars);
        Double temperature = ModelParam.TEMPERATURE.getValueFrom(vars);
        Double topP = ModelParam.TOP_P.getValueFrom(vars);
        Double freqPenalty = ModelParam.FREQUENCY_PENALTY.getValueFrom(vars);
        Double presPenalty = ModelParam.PRESENCE_PENALTY.getValueFrom(vars);
        if (maxTokens != null) {
            builder.maxTokens(maxTokens);
        }
        if (temperature != null) {
            builder.temperature(temperature);
        }
        if (topP != null) {
            builder.topP(topP);
        }
        if (freqPenalty != null) {
            builder.frequencyPenalty(freqPenalty);
        }
        if (presPenalty != null) {
            builder.presencePenalty(presPenalty);
        }
        if (AiProvider.DEEP_SEEK.supportsDeepThinking(config.getModelName())&& config.getReturnThinking()){
            builder.returnThinking(true);
        }
        return builder.build();
    }



    @Override
    public String providerId() {
        return AiProvider.DEEP_SEEK.getCode();
    }

}
