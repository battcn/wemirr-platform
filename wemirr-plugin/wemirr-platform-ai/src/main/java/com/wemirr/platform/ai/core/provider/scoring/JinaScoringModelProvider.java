package com.wemirr.platform.ai.core.provider.scoring;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.jina.JinaScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

/**
 * Jina 重排序模型提供者
 *
 * @author xJh
 * @since 2025/12/18
 */
@Component
public class JinaScoringModelProvider implements ScoringModelProvider {

    private static final String DEFAULT_MODEL = "jina-reranker-v2-base-multilingual";

    @Override
    public boolean supports(ModelConfig config) {
        String provider = config.getProvider();
        return "jina".equalsIgnoreCase(provider) || "jina-ai".equalsIgnoreCase(provider);
    }

    @Override
    public ScoringModel createModel(ModelConfig config) {
        String modelName = config.getModelName() != null ? config.getModelName() : DEFAULT_MODEL;

        JinaScoringModel.JinaScoringModelBuilder builder = JinaScoringModel.builder()
                .apiKey(config.getApiKey())
                .modelName(modelName);

        // 从 variables 读取额外配置
        Map<String, Object> variables = config.getVariables();
        if (variables != null) {
            if (variables.containsKey("timeout")) {
                builder.timeout(Duration.ofSeconds(((Number) variables.get("timeout")).longValue()));
            }
            if (variables.containsKey("maxRetries")) {
                builder.maxRetries(((Number) variables.get("maxRetries")).intValue());
            }
        }

        return builder.build();
    }

    @Override
    public String providerId() {
        return "jina";
    }
}
