package com.wemirr.platform.ai.core.provider.scoring;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.model.cohere.CohereScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

/**
 * Cohere 重排序模型提供者
 * <p>
 * Cohere 提供高质量的多语言重排序模型
 *
 * @author xJh
 * @since 2025/12/18
 */
@Component
public class CohereScoringModelProvider implements ScoringModelProvider {

    private static final String DEFAULT_MODEL = "rerank-multilingual-v3.0";

    @Override
    public boolean supports(ModelConfig config) {
        String provider = config.getProvider();
        return "cohere".equalsIgnoreCase(provider);
    }

    @Override
    public ScoringModel createModel(ModelConfig config) {
        String modelName = config.getModelName() != null ? config.getModelName() : DEFAULT_MODEL;

        CohereScoringModel.CohereScoringModelBuilder builder = CohereScoringModel.builder()
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

        // 支持自定义 baseUrl（用于代理或私有化部署）
        if (config.getBaseUrl() != null && !config.getBaseUrl().isBlank()) {
            builder.baseUrl(config.getBaseUrl());
        }

        return builder.build();
    }

    @Override
    public String providerId() {
        return "cohere";
    }
}
