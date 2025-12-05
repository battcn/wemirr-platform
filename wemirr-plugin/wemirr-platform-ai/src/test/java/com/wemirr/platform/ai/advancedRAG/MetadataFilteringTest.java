package com.wemirr.platform.ai.advancedRAG;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.core.enums.ModelType;
import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.VectorStoreFactory;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.filter.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(properties = {
        "rag.vector-store.type=MILVUS",
        "rag.vector-store.milvus.host=localhost",
        "rag.vector-store.milvus.port=19530",
        "rag.vector-store.milvus.collection-name=test_documents_metadata",
        "rag.vector-store.milvus.dimension=1536"  // Qwen embedding model dimension
})
class MetadataFilteringTest {

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
    private ChatModel chatModel;

    @BeforeEach
    void setUp() {
        // 使用工厂创建向量存储
        embeddingStore = vectorStoreFactory.create();

        // 创建真实的嵌入模型配置
        ModelConfig embeddingModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.EMBEDDING)
                .modelName("text-embedding-v2")
                .apiKey(System.getenv("qwen_api_key"))
                .build();

        // 获取真实的嵌入模型实例
        embeddingModel = embeddingModelService.getModel(embeddingModelConfig);

        // 创建聊天模型配置
        Map<String, Object> variables = new HashMap<>();
        variables.put("temperature", 0.7);
        variables.put("max_tokens", 500);

        ModelConfig chatModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.TEXT)
                .modelName("qwen-plus")
                .apiKey(System.getenv("qwen_api_key"))
                .variables(variables)
                .build();

        // 获取真实的聊天模型实例
        chatModel = textModelService.model(chatModelConfig);
    }

    @Test
    void testStaticMetadataFilter() {
        // 创建带元数据的文档片段
        TextSegment dogsSegment = TextSegment.from(
                "狗是人类最忠诚的朋友，它们聪明、活泼，适合家庭饲养。",
                Metadata.metadata("animal", "dog").put("category", "pet")
        );
        
        TextSegment catsSegment = TextSegment.from(
                "猫是独立的宠物，它们优雅、安静，适合城市生活。",
                Metadata.metadata("animal", "cat").put("category", "pet")
        );
        
        TextSegment birdsSegment = TextSegment.from(
                "鸟类是多样化的动物，有些可以模仿人类语言，如鹦鹉。",
                Metadata.metadata("animal", "bird").put("category", "pet")
        );

        // 嵌入并存储文档片段
        embeddingStore.add(embeddingModel.embed(dogsSegment).content(), dogsSegment);
        embeddingStore.add(embeddingModel.embed(catsSegment).content(), catsSegment);
        embeddingStore.add(embeddingModel.embed(birdsSegment).content(), birdsSegment);

        // 创建静态过滤器，只检索关于狗的信息
        Filter onlyDogs = metadataKey("animal").isEqualTo("dog");

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .filter(onlyDogs) // 静态过滤器
                .maxResults(2)
                .minScore(0.1)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        // 测试查询
        String answer = assistant.answer("哪种动物最忠诚？").text();
        
        assertNotNull(answer);
        assertFalse(answer.isEmpty());
        assertTrue(answer.toLowerCase().contains("狗") || answer.toLowerCase().contains("dog"));
        System.out.println("静态过滤测试结果: " + answer);
    }

    @Test
    void testDynamicMetadataFilter() {
        // 创建属于不同用户的数据
        TextSegment user1Info = TextSegment.from(
                "用户1的偏好：喜欢蓝色，对科技产品感兴趣，居住在北京。",
                Metadata.metadata("userId", "user1").put("preference", "technology")
        );
        
        TextSegment user2Info = TextSegment.from(
                "用户2的偏好：喜欢红色，对时尚感兴趣，居住在上海。",
                Metadata.metadata("userId", "user2").put("preference", "fashion")
        );
        
        TextSegment user3Info = TextSegment.from(
                "用户3的偏好：喜欢绿色，对旅游感兴趣，居住在广州。",
                Metadata.metadata("userId", "user3").put("preference", "travel")
        );

        // 嵌入并存储文档片段
        embeddingStore.add(embeddingModel.embed(user1Info).content(), user1Info);
        embeddingStore.add(embeddingModel.embed(user2Info).content(), user2Info);
        embeddingStore.add(embeddingModel.embed(user3Info).content(), user3Info);

        // 动态过滤器，根据当前用户ID过滤
        Function<Query, Filter> filterByUserId = 
                (query) -> metadataKey("userId").isEqualTo(query.metadata().chatMemoryId().toString());

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .dynamicFilter(filterByUserId) // 动态过滤器
                .maxResults(2)
                .minScore(0.1)
                .build();

        PersonalizedAssistant personalizedAssistant = AiServices.builder(PersonalizedAssistant.class)
                .chatModel(chatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        // 测试用户1的查询
        String answer1 = personalizedAssistant.chat("user1", "我应该关注什么领域？").text();
        assertNotNull(answer1);
        assertFalse(answer1.isEmpty());
        assertTrue(answer1.contains("科技") || answer1.toLowerCase().contains("technology"));
        System.out.println("用户1查询结果: " + answer1);

        // 测试用户2的查询
        String answer2 = personalizedAssistant.chat("user2", "什么颜色适合我？").text();
        assertNotNull(answer2);
        assertFalse(answer2.isEmpty());
        assertTrue(answer2.contains("红色") || answer2.toLowerCase().contains("red"));
        System.out.println("用户2查询结果: " + answer2);
    }

    /**
     * 定义AI助手接口
     */
    interface Assistant {
        AiMessage answer(@UserMessage String userMessage);
    }

    /**
     * 定义个性化AI助手接口
     */
    interface PersonalizedAssistant {
        AiMessage chat(@MemoryId String userId, @UserMessage String userMessage);
    }
}