package com.wemirr.framework.ai.core.provider.text;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.model.ModelConfig;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

/**
 * DeepSeek 文本模型提供者
 *
 * @author Levin
 * @since 2025/10/11
 */
public class DeepSeekTextModelProvider extends AbstractTextModelProvider {

    @Override
    protected AiProvider getAiProvider() {
        return AiProvider.DEEP_SEEK;
    }

    @Override
    public ChatModel createModel(ModelConfig config) {
        logModelCreation(config, false);

        OpenAiChatModel.OpenAiChatModelBuilder builder = OpenAiChatModel.builder()
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .modelName(config.getName());

        applyCommonParams(builder, config);

        if (isDeepThinkingEnabled(config)) {
            builder.returnThinking(true);
        }

        return builder.build();
    }

    @Override
    public StreamingChatModel createStreamingModel(ModelConfig config) {
        logModelCreation(config, true);

        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder = OpenAiStreamingChatModel.builder()
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .modelName(config.getName());

        applyStreamingParams(builder, config);

        if (isDeepThinkingEnabled(config)) {
            builder.returnThinking(true);
        }

        return builder.build();
    }

    private void applyCommonParams(OpenAiChatModel.OpenAiChatModelBuilder builder, ModelConfig config) {
        Integer maxTokens = extractMaxTokens(config);
        Double temperature = extractTemperature(config);
        Double topP = extractTopP(config);
        Double freqPenalty = extractFrequencyPenalty(config);
        Double presPenalty = extractPresencePenalty(config);

        if (maxTokens != null) builder.maxTokens(maxTokens);
        if (temperature != null) builder.temperature(temperature);
        if (topP != null) builder.topP(topP);
        if (freqPenalty != null) builder.frequencyPenalty(freqPenalty);
        if (presPenalty != null) builder.presencePenalty(presPenalty);
    }

    private void applyStreamingParams(OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder, ModelConfig config) {
        Integer maxTokens = extractMaxTokens(config);
        Double temperature = extractTemperature(config);
        Double topP = extractTopP(config);
        Double freqPenalty = extractFrequencyPenalty(config);
        Double presPenalty = extractPresencePenalty(config);

        if (maxTokens != null) builder.maxTokens(maxTokens);
        if (temperature != null) builder.temperature(temperature);
        if (topP != null) builder.topP(topP);
        if (freqPenalty != null) builder.frequencyPenalty(freqPenalty);
        if (presPenalty != null) builder.presencePenalty(presPenalty);
    }
}
