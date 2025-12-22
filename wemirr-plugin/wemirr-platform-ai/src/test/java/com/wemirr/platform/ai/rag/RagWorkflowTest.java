package com.wemirr.platform.ai.rag;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.core.enums.ModelType;
import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.VectorStoreFactory;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiao1
 * @since 2025-12
 */
@SpringBootTest
@TestPropertySource(properties = {
        "rag.vector-store.type=MILVUS",
        "rag.vector-store.milvus.host=localhost",
        "rag.vector-store.milvus.port=19530",
        "rag.vector-store.milvus.collection-name=test_documents",
        "rag.vector-store.milvus.dimension=1536"  // Qwen embedding model dimension
})
class RagWorkflowTest {

    @Autowired
    private VectorStoreFactory vectorStoreFactory;

    @Autowired
    private VectorStoreProperties vectorStoreProperties;

    @Autowired
    private EmbeddingModelService embeddingModelService;

    @Autowired
    private TextModelService textModelService;

    private EmbeddingStore<TextSegment> embeddingStore;
    private EmbeddingModel embeddingModel;

    @BeforeEach
    void setUp() {
        // 使用工厂创建向量存储
        embeddingStore = vectorStoreFactory.create();
        
        // 创建真实的嵌入模型配置
        ModelConfig embeddingModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.EMBEDDING)
                .modelName("text-embedding-v2")
                .apiKey("")
                .build();
        
        // 获取真实的嵌入模型实例
        embeddingModel = embeddingModelService.getModel(embeddingModelConfig);
    }

    @Test
    void testRagWorkflow() {
        // 创建聊天模型配置
        Map<String, Object> variables = new HashMap<>();
        variables.put("temperature", 0.7);
        variables.put("max_tokens", 500);

        ModelConfig chatModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.TEXT)
                .modelName("qwen-plus")
                .apiKey("")
                .variables(variables)
                .build();

        // 获取真实的聊天模型实例
        ChatModel chatModel = textModelService.model(chatModelConfig);


        // 创建文档片段
        TextSegment segment = TextSegment.from("LangChain4j是一个用于构建Java LLM应用程序的框架。它提供了工具和抽象来简化与大型语言模型的集成。");

        // 嵌入并存储文档片段
        Embedding embedding = embeddingModel.embed(segment).content();
        embeddingStore.add(embedding, segment);

        // 验证嵌入存储
//        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
//                .queryEmbedding(embeddingModel.embed("什么是LangChain4j?").content())
//                .maxResults(2)
//                .build();
//
//        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
//        List<EmbeddingMatch<TextSegment>> matches = searchResult.matches();
//        assertFalse(matches.isEmpty(), "应该找到匹配的文档片段");
        String userQuery = "什么是LangChain4j??";
        Embedding queryEmbedding = embeddingModel.embed(userQuery).content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(2)  // top 2
                // .minScore(0.5) // 可选：设置最小相似度阈值
                .build();

        //------ 搜索 ---------- 等价于ContentRetriever
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        //  获取结果列表
        List<EmbeddingMatch<TextSegment>> matches = searchResult.matches();
        // 输出结果
        System.out.println("\n 搜索结果:");
        for (int i = 0; i < matches.size(); i++) {
            EmbeddingMatch<TextSegment> match = matches.get(i);
            TextSegment textSegment = match.embedded();
            double score = match.score(); // Cosine Similarity

            System.out.printf("  %d. 文本: \"%s\" | 相似度得分: %.4f\n", i + 1, textSegment.text(), score);
        }

        // 创建内容检索器
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2)
                .minScore(0.1)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        // 测试问答
        String question = "什么是LangChain4j?";
        AiMessage answer = assistant.answer(question);
        
        assertNotNull(answer.text(), "应该返回回答");
        assertFalse(answer.text().isEmpty(), "回答不应该为空");
        System.out.println("问题: " + question);
        System.out.println("回答: " + answer.text());
    }

    @Test
    void testModelConfigCreation() {
        // 测试创建文本模型配置
        Map<String, Object> variables = new HashMap<>();
        variables.put("temperature", 0.7);
        variables.put("max_tokens", 2000);

        ModelConfig textModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.TEXT)
                .modelName("qwen-plus")
                .apiKey("test-api-key")
                .variables(variables)
                .build();

        assertNotNull(textModelConfig);
        assertEquals("qwen", textModelConfig.getProvider());
        assertEquals(ModelType.TEXT, textModelConfig.getModelType());
        assertEquals("qwen-plus", textModelConfig.getModelName());

        // 测试创建嵌入模型配置
        ModelConfig embeddingModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.EMBEDDING)
                .modelName("text-embedding-v2")
                .apiKey("test-api-key")
                .build();

        assertNotNull(embeddingModelConfig);
        assertEquals("qwen", embeddingModelConfig.getProvider());
        assertEquals(ModelType.EMBEDDING, embeddingModelConfig.getModelType());
        assertEquals("text-embedding-v2", embeddingModelConfig.getModelName());
    }

    /**
     * 定义AI助手接口
     */
    interface Assistant {
        AiMessage answer(@UserMessage String userMessage);
    }
}