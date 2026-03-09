package com.wemirr.framework.ai.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * AI 配置属性
 *
 * @author Levin
 * @since 2025/12/27
 */
@Data
@ConfigurationProperties(prefix = "ai")
public class AiProperties {

    /**
     * 是否启用 AI 功能
     */
    private boolean enabled = true;

    /**
     * 提供商配置
     */
    private ProviderConfig provider = new ProviderConfig();

    @Data
    public static class ProviderConfig {
        private ProviderItem deepseek = new ProviderItem();
        private ProviderItem qwen = new ProviderItem();
        private ProviderItem openai = new ProviderItem();
    }

    @Data
    public static class ProviderItem {
        private boolean enabled = true;
    }
}
