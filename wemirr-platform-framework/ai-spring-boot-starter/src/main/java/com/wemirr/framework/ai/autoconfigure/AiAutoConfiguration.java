package com.wemirr.framework.ai.autoconfigure;

import com.wemirr.framework.ai.core.provider.embedding.EmbeddingModelFactory;
import com.wemirr.framework.ai.core.provider.embedding.EmbeddingModelRegistry;
import com.wemirr.framework.ai.core.provider.embedding.OpenAiEmbeddingModelFactory;
import com.wemirr.framework.ai.core.provider.embedding.QwenEmbeddingModelFactory;
import com.wemirr.framework.ai.core.provider.text.*;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * AI 自动配置类
 *
 * @author Levin
 * @since 2025/12/27
 */
@AutoConfiguration
@ConditionalOnClass(ChatModel.class)
@ConditionalOnProperty(prefix = "ai", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AiProperties.class)
public class AiAutoConfiguration {

    // ==================== 文本模型提供者 ====================

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ai.provider.deepseek", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "dev.langchain4j.model.openai.OpenAiChatModel")
    public DeepSeekTextModelProvider deepSeekTextModelProvider() {
        return new DeepSeekTextModelProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ai.provider.qwen", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "dev.langchain4j.community.model.dashscope.QwenChatModel")
    public QwenTextModelProvider qwenTextModelProvider() {
        return new QwenTextModelProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ai.provider.openai", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "dev.langchain4j.model.openai.OpenAiChatModel")
    public OpenAiTextModelProvider openAiTextModelProvider() {
        return new OpenAiTextModelProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    public TextModelProviderRegistry textModelProviderRegistry(List<TextModelProvider> providers) {
        return new TextModelProviderRegistry(providers);
    }

    // ==================== 向量模型提供者 ====================

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ai.provider.qwen", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "dev.langchain4j.community.model.dashscope.QwenEmbeddingModel")
    public QwenEmbeddingModelFactory qwenEmbeddingModelFactory() {
        return new QwenEmbeddingModelFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ai.provider.openai", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(name = "dev.langchain4j.model.openai.OpenAiEmbeddingModel")
    public OpenAiEmbeddingModelFactory openAiEmbeddingModelFactory() {
        return new OpenAiEmbeddingModelFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public EmbeddingModelRegistry embeddingModelRegistry(List<EmbeddingModelFactory> factories) {
        return new EmbeddingModelRegistry(factories);
    }
}
