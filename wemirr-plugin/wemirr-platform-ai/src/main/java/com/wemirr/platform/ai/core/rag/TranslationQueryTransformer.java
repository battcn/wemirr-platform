package com.wemirr.platform.ai.core.rag;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.transformer.QueryTransformer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static dev.langchain4j.internal.Utils.getOrDefault;
import static dev.langchain4j.internal.ValidationUtils.ensureNotNull;
import static java.util.Collections.singletonList;

/**
 * 将用户查询翻译为目标语言（如中文），以便在单语知识库（如中文）中进行检索。
 * 使用 LLM 进行高质量翻译，并保留原始上下文。
 *
 * @author xJh
 * @since 2025/11/3
 **/
public class TranslationQueryTransformer implements QueryTransformer {

    public static final PromptTemplate DEFAULT_PROMPT_TEMPLATE = PromptTemplate.from(
            """
                    You are a translation assistant. Your task is to translate the user's query into Chinese for information retrieval from a Chinese knowledge base.
                    
                    Important rules:
                    1. Translate the user's query accurately into Chinese
                    2. Preserve all technical terms, proper nouns, and key concepts
                    3. Maintain the original intent and meaning
                    4. Keep the query concise and suitable for search
                    5. If the query is already in Chinese, return it as-is with minor improvements if needed
                    6. Only return the translated query, nothing else!
                    
                    User query: {{query}}
                    
                    Translated Chinese query:"""
    );

    protected final PromptTemplate promptTemplate;
    protected final ChatModel chatLanguageModel;

    public TranslationQueryTransformer(ChatModel chatLanguageModel) {
        this(chatLanguageModel, DEFAULT_PROMPT_TEMPLATE);
    }

    public TranslationQueryTransformer(ChatModel chatLanguageModel, PromptTemplate promptTemplate) {
        this.chatLanguageModel = ensureNotNull(chatLanguageModel, "chatLanguageModel");
        this.promptTemplate = getOrDefault(promptTemplate, DEFAULT_PROMPT_TEMPLATE);
    }

    public static TranslationQueryTransformerBuilder builder() {
        return new TranslationQueryTransformerBuilder();
    }

    @Override
    public Collection<Query> transform(Query query) {
        String originalQuery = query.text();

        // 如果查询很短或者是特定语言，可以添加简单检测逻辑
        if (isLikelyChinese(originalQuery)) {
            // 已经是中文，直接返回或轻微优化
            return singletonList(query);
        }

        Prompt prompt = createPrompt(query);
        String translatedQueryText = chatLanguageModel.chat(prompt.text());

        // 清理可能的额外文本
        translatedQueryText = cleanTranslatedText(translatedQueryText);

        Query translatedQuery = query.metadata() == null
                ? Query.from(translatedQueryText)
                : Query.from(translatedQueryText, query.metadata());
        return singletonList(translatedQuery);
    }

    protected Prompt createPrompt(Query query) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("query", query.text());
        return promptTemplate.apply(variables);
    }

    private boolean isLikelyChinese(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        // 简单的中文检测：检查是否包含中文字符
        return text.chars().anyMatch(ch ->
                Character.UnicodeScript.of(ch) == Character.UnicodeScript.HAN);
    }

    private String cleanTranslatedText(String text) {
        if (text == null) {
            return "";
        }

        // 移除可能的提示词前缀
        return text.replaceAll("^(翻译后的查询|Translated query|中文查询):\\s*", "")
                .trim();
    }

    public static class TranslationQueryTransformerBuilder {
        private ChatModel chatLanguageModel;
        private PromptTemplate promptTemplate;

        TranslationQueryTransformerBuilder() {
        }

        public TranslationQueryTransformerBuilder chatLanguageModel(ChatModel chatLanguageModel) {
            this.chatLanguageModel = chatLanguageModel;
            return this;
        }

        public TranslationQueryTransformerBuilder promptTemplate(PromptTemplate promptTemplate) {
            this.promptTemplate = promptTemplate;
            return this;
        }

        public TranslationQueryTransformer build() {
            return new TranslationQueryTransformer(this.chatLanguageModel, this.promptTemplate);
        }
    }
}
