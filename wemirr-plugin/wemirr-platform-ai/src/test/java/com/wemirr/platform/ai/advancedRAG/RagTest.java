package com.wemirr.platform.ai.advancedRAG;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.core.enums.ModelType;
import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.VectorStoreFactory;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.aggregator.DefaultContentAggregator;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.LanguageModelQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import dev.langchain4j.model.chat.ChatModel;
import java.util.*;


@SpringBootTest
@TestPropertySource(properties = {
        "rag.vector-store.type=MILVUS",
        "rag.vector-store.milvus.host=localhost",
        "rag.vector-store.milvus.port=19530",
        "rag.vector-store.milvus.collection-name=test_documents_1016",
        "rag.vector-store.milvus.dimension=1536"  // Qwen embedding model dimension
})
public class RagTest {

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
    ChatModel chatModel;

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
        variables.put("max_tokens", 1000);
        ModelConfig chatModelConfig = ModelConfig.builder()
                .provider(AiProvider.QWEN.getCode())
                .modelType(ModelType.TEXT)
                .modelName("qwen-plus")
                .apiKey(System.getenv("qwen_api_key"))
                .variables(variables)
                .build();
        chatModel = textModelService.model(chatModelConfig);

    }

    @Test
    public void testRag() {

        // 获取真实的聊天模型实例

        // 创建多种类型的文档片段
        TextSegment technicalDoc1 = TextSegment.from(
                "LangChain4j是一个用于构建Java LLM应用程序的框架。",
                Metadata.metadata("type", "technical").put("source", "docs")
        );
        TextSegment technicalDoc2 = TextSegment.from(
                "在LangChain4j中，可以使用AiServices.builder()来创建AI服务实例。",
                Metadata.metadata("type", "technical").put("source", "docs")
        );
        TextSegment faqDoc1 = TextSegment.from(
                "常见问题：如何安装LangChain4j？答：可以通过Maven依赖添加LangChain4j到项目中。",
                Metadata.metadata("type", "faq").put("lang", "zh")
        );
        TextSegment generalDoc1 = TextSegment.from(
                "通义千问是由通义实验室研发的超大规模语言模型，能够回答问题、创作文字等。",
                Metadata.metadata("type", "general").put("domain", "llm")
        );

        // 嵌入并存储文档片段
//        embeddingStore.add(embeddingModel.embed(technicalDoc1).content(), technicalDoc1);
//        embeddingStore.add(embeddingModel.embed(technicalDoc2).content(), technicalDoc2);
//        embeddingStore.add(embeddingModel.embed(generalDoc1).content(), generalDoc1);
//        embeddingStore.add(embeddingModel.embed(faqDoc1).content(), faqDoc1);

        // 创建基础的内容检索器
        ContentRetriever embeddingStoreContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(5)
                .minScore(0.01)
                .build();

        //用户提问
        String userQuery = "LangChain4j是什么？";

        Query query = Query.from(userQuery,
                dev.langchain4j.rag.query.Metadata.from(
                        dev.langchain4j.data.message.UserMessage.from(userQuery),
                        1L,
                        new ArrayList<>()
                )
        );

        // Query 转换器。也可以什么换用 ExpandingQueryTransformer 进行 Query 扩展
        QueryTransformer  queryTransformer = new CompressingQueryTransformer(chatModel);
        QueryRouter queryRouter = LanguageModelQueryRouter.builder()
                .chatModel(chatModel)
                .retrieverToDescription(Map.of(
                        embeddingStoreContentRetriever, "Embedding Database"
                ))
                .build();
        Collection<Query> transform = queryTransformer.transform(query);

        // query router
        Map<Query, Collection<List<Content>>> query2Contents = new HashMap<>();
        for (Query curQuery : transform) {
            Collection<ContentRetriever> contentRetrievers = queryRouter.route(curQuery);
            List<List<Content>> contents = new ArrayList<>();
            for (ContentRetriever contentRetriever : contentRetrievers) {
                contents.add(contentRetriever.retrieve(curQuery));
            }
            query2Contents.put(curQuery, contents);
        }

        // content 聚合器，如果有 ReRanking 模型，也可以选择使用 ReRankingContentAggregator 对检索结果进行排序
        DefaultContentAggregator contentAggregator = new DefaultContentAggregator();
        List<Content> aggregateContents = contentAggregator.aggregate(query2Contents);
        // content 注入器，可以自定义注入内容的模型
        DefaultContentInjector contentInjector = new DefaultContentInjector();
        // content injector
        ChatMessage userMessage = contentInjector.inject(
                aggregateContents,
                dev.langchain4j.data.message.UserMessage.from(userQuery)
        );


        // 构建消息列表
        List<ChatMessage> messages = new ArrayList<>();
//        messages.add(systemMessage);
        messages.add(userMessage);


        DefaultRetrievalAugmentor build = DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector)
//                .executor(executorService) 多线程处理
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                    .chatModel(chatModel)
                    .retrievalAugmentor(build)  //
                    .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                    .build();
        var systemPrompt = "你是一个知识渊博的助手，请通俗易懂地回答我的问题。";
        Long memoryId = 1L; // 假设的记忆ID

        String assistantResponse = assistant.chatRole(systemPrompt, userQuery);

        System.out.println("通过AI服务接口的回复: " + assistantResponse);
    }

    /**
     * 定义AI助手接口
     */
    interface Assistant {

        @dev.langchain4j.service.SystemMessage("{{systemMessage}}")
        String chatRole( @V("systemMessage") String systemMessage, @UserMessage String userMessage);
    }

    /**
     * 多路召回 + 查询扩展 + RRF 融合 + 重排序
     */
    public RetrievalAugmentor buildAdvancedRetrievalAugmentor() {
        // Query 转换器。也可以什么换用 ExpandingQueryTransformer 进行 Query 扩展
        QueryTransformer  queryTransformer = new CompressingQueryTransformer(chatModel);
        // web 搜索引擎，此处选择的是 tavily 搜索引擎
        final var tavilyWebSearchEngine = TavilyWebSearchEngine.builder()
                .apiKey("")
                .build();
        WebSearchContentRetriever webSearchContentRetriever = WebSearchContentRetriever.builder()
                .maxResults(10)
                .webSearchEngine(tavilyWebSearchEngine)
                .build();
        // 内嵌数据库检索器，此处选择的是 milvus 数据库
        EmbeddingStoreContentRetriever embeddingStoreContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(10)
                .build();
        //  Query 路由，让大模型决定选择哪一个检索器或者哪几个检索器。retrieverToDescription 属性的 key 为检索器，value 为检索器的描述
        QueryRouter queryRouter = LanguageModelQueryRouter.builder()
                .chatModel(chatModel)
                .retrieverToDescription(Map.of(
                        webSearchContentRetriever, "Web Search",
                        embeddingStoreContentRetriever, "Embedding Database"
                ))
                .build();
        // content 聚合器，如果有 ReRanking 模型，也可以选择使用 ReRankingContentAggregator 对检索结果进行排序
        DefaultContentAggregator contentAggregator = new DefaultContentAggregator();
        // content 注入器，可以自定义注入内容的模型
        DefaultContentInjector contentInjector = new DefaultContentInjector();

        // 检索增强器，简单理解为把上面的几个组件联系到一起协作处理 Query，最终返回增强后的 ChatMessage
        return DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector)
//                .executor(executorService) 多线程处理
                .build();
    }
}