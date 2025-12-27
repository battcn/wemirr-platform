package com.wemirr.framework.ai.core.provider.text;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.ai.core.enums.ModelParam;
import com.wemirr.framework.ai.core.model.ModelConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 文本模型提供者抽象基类
 *
 * @author Levin
 * @since 2025/10/11
 */
@Slf4j
public abstract class AbstractTextModelProvider implements TextModelProvider {

    protected abstract AiProvider getAiProvider();

    @Override
    public String getProviderId() {
        return getAiProvider().getValue();
    }

    @Override
    public boolean supports(ModelConfig config) {
        AiProvider provider = getAiProvider();
        return provider.getValue().equalsIgnoreCase(config.getProviderCode())
                && provider.supportsModel(config.getName());
    }

    // ==================== 参数提取辅助方法 ====================

    protected Integer extractMaxTokens(ModelConfig config) {
        return extractParam(config, ModelParam.MAX_TOKENS);
    }

    protected Double extractTemperature(ModelConfig config) {
        return extractParam(config, ModelParam.TEMPERATURE);
    }

    protected Double extractTopP(ModelConfig config) {
        return extractParam(config, ModelParam.TOP_P);
    }

    protected Double extractFrequencyPenalty(ModelConfig config) {
        return extractParam(config, ModelParam.FREQUENCY_PENALTY);
    }

    protected Double extractPresencePenalty(ModelConfig config) {
        return extractParam(config, ModelParam.PRESENCE_PENALTY);
    }

    protected boolean isDeepThinkingEnabled(ModelConfig config) {
        return getAiProvider().supportsDeepThinking(config.getName())
                && Boolean.TRUE.equals(config.getReturnThinking());
    }

    protected boolean isWebSearchEnabled(ModelConfig config) {
        return getAiProvider().supportsWebSearch(config.getName())
                && Boolean.TRUE.equals(config.getEnableWebSearch());
    }

    protected <T> T extractParam(ModelConfig config, ModelParam param) {
        Map<String, Object> vars = config.getVariables();
        return vars == null ? null : param.getValueFrom(vars);
    }

    protected void logModelCreation(ModelConfig config, boolean isStreaming) {
        log.debug("创建{}模型: provider={}, model={}, streaming={}",
                getAiProvider().getLabel(), getProviderId(), config.getName(), isStreaming);
    }
}
