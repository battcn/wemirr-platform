package com.wemirr.framework.ai.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单模型配置实现
 * <p>
 * 用于独立使用 AI Starter 或单元测试场景
 * </p>
 *
 * @author Levin
 * @since 2025/12/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleModelConfig implements ModelConfig {

    private String name;
    private String apiKey;
    private String baseUrl;
    private String providerCode;
    private String modelType;

    @Builder.Default
    private Map<String, Object> variables = new HashMap<>();

    @Builder.Default
    private Boolean returnThinking = false;

    @Builder.Default
    private Boolean enableWebSearch = false;
}
