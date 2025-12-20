package com.wemirr.platform.ai.graph;

import com.wemirr.platform.ai.core.enums.AiProvider;
import com.wemirr.platform.ai.core.enums.ModelType;
import com.wemirr.platform.ai.core.provider.graph.*;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.data.document.graph.GraphEdge;
import dev.langchain4j.community.data.document.graph.GraphNode;
import dev.langchain4j.community.data.document.transformer.graph.LLMGraphTransformer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * GraphRAG 功能测试
 * <p>
 * 测试图谱存储、检索、关键词检索等功能
 *
 * @author xJh
 * @since 2025/12/11
 */
@SpringBootTest
@Slf4j
public class GraphTest01 {

    // ==================== 依赖注入 ====================

    @Autowired
    private TextModelService textModelService;

    @Autowired
    private GraphStore graphStore;

    @Autowired
    private GraphRetriever graphRetriever;

    @Autowired
    private GraphRagService graphRagService;

    // ==================== 测试配置 ====================

    private static final String TEST_KB_ID = "1996120908054491138";
    private ChatModel chatModel;


    @BeforeEach
    void setUp() {
        if (chatModel == null) {
            ModelConfig config = ModelConfig.builder()
                    .provider(AiProvider.QWEN.getCode())
                    .modelType(ModelType.TEXT)
                    .modelName("qwen-plus")
                    .apiKey(System.getenv("qwen_api_key"))
                    .build();
            chatModel = textModelService.model(config);
        }
    }

    // ==================== 图谱提取与存储测试 ====================

    @Test
    @DisplayName("测试图谱提取和存储")
    void testGraphExtraction() {
        LLMGraphTransformer graphTransformer = GraphRagTransformerFactory.create(chatModel);
        Document document = Document.from("奥巴马出生于美国夏威夷的檀香山。他是美国第44任总统，任期从2009年到2017年。");

        List<GraphDocument> graphDocuments = graphTransformer.transformAll(List.of(document));
        for (GraphDocument graphDocument : graphDocuments) {
            Set<GraphNode> nodes = graphDocument.nodes();
            Set<GraphEdge> relationships = graphDocument.relationships();

            nodes.forEach(node -> log.info("节点: {}", node));
            relationships.forEach(rel -> log.info("关系: {}", rel));
            log.info("图谱提取完成: nodes={}, relationships={}", nodes.size(), relationships.size());
        }

        graphStore.addGraphDocuments("test_extraction", graphDocuments, true);
        log.info("图谱存储完成");
    }

    // ==================== 完整链路测试 ====================

    @Test
    @DisplayName("完整链路测试：文档 → 图谱提取 → 存储 → 向量检索")
    void testFullPipeline() {
        String knowledgeBaseId = TEST_KB_ID;

        // 创建图谱提取器
        LLMGraphTransformer graphTransformer = GraphRagTransformerFactory.create(chatModel);

        // 准备文档
        List<Document> documents = List.of(
                Document.from("刘备，字玄德，是三国时期蜀汉的开国皇帝。他与关羽、张飞桃园结义，并请诸葛亮出山辅佐。"),
                Document.from("诸葛亮，字孔明，是三国时期蜀汉的丞相。他提出了著名的《隆中对》战略。")
        );

        // 处理文档（提取 + 存储 + 生成向量嵌入）
        GraphRagService.ProcessResult result = graphRagService.processDocumentsWithEmbedding(
                knowledgeBaseId, documents, graphTransformer, true, true);
        log.info("处理结果: documents={}, nodes={}, relationships={}",
                result.documentsProcessed(), result.nodesCreated(), result.relationshipsCreated());

        // 创建向量索引
        graphStore.ensureVectorIndex(knowledgeBaseId);

        // 查看统计信息
        Map<String, Object> stats = graphRagService.getStatistics(knowledgeBaseId);
        log.info("知识库统计: {}", stats);

        // 执行向量语义检索
        List<String> questions = List.of("刘备和谁结拜？", "诸葛亮提出了什么战略？");
        for (String question : questions) {
            log.info("问题: {}", question);
            List<String> triples = graphRagService.retrieveByVector(knowledgeBaseId, question);
            log.info("检索结果: {}", triples);
        }
    }

    @Test
    @DisplayName("测试知识库统计信息")
    void testStatistics() {
        Map<String, Object> stats = graphRagService.getStatistics(TEST_KB_ID);
        log.info("知识库统计信息:");
        log.info("  节点数量: {}", stats.get("nodeCount"));
        log.info("  文档数量: {}", stats.get("documentCount"));
        log.info("  关系数量: {}", stats.get("relationshipCount"));
        log.info("  实体类型分布: {}", stats.get("entityTypeDistribution"));
        log.info("  关系类型分布: {}", stats.get("relationshipTypeDistribution"));
    }

    @Test
    @DisplayName("测试获取知识库 Schema")
    void testGetSchema() {
        String schema = graphRagService.getSchema(TEST_KB_ID);
        log.info("知识库 Schema:\n{}", schema);
    }

    // ==================== GraphContentRetriever 集成测试 ====================

    @Test
    @DisplayName("测试 GraphContentRetriever 集成到 RAG 管道")
    void testGraphContentRetrieverIntegration() {
        // 创建 GraphContentRetriever
        ContentRetriever contentRetriever = GraphContentRetriever.builder()
                .graphRagService(graphRagService)
                .chatModel(chatModel)
                .knowledgeBaseId(TEST_KB_ID)
                .maxResults(10)
                .silentOnEmpty(true)
                .build();

        // 构建 RAG 管道
        DefaultRetrievalAugmentor augmentor = DefaultRetrievalAugmentor.builder()
                .queryRouter(new DefaultQueryRouter(contentRetriever))
                .build();

        // 测试检索
        String question = "诸葛亮提出了什么战略";
        log.info("问题: {}", question);

        List<Content> contents = contentRetriever.retrieve(new Query(question));
        log.info("检索结果数量: {}", contents.size());
        contents.forEach(c -> log.info("  - {}", c.textSegment().text()));
    }

    // ==================== 向量索引测试 ====================

    @Test
    @DisplayName("测试向量索引创建")
    void testEnsureVectorIndex() {
        graphStore.ensureVectorIndex(TEST_KB_ID);
        log.info("向量索引创建/验证完成: knowledgeBaseId={}", TEST_KB_ID);
    }

    // ==================== 向量语义检索测试 ====================

    @Test
    @DisplayName("测试向量语义搜索")
    void testSearchByVector() {
        String question = "什么是向量数据库？";

        // 确保向量索引存在
        graphStore.ensureVectorIndex(TEST_KB_ID);

        // 执行向量搜索
        List<String> results = graphRetriever.searchByVector(TEST_KB_ID, question, 0.5, 10);
        log.info("向量搜索结果: question={}, 找到 {} 个实体", question, results.size());
        results.forEach(entity -> log.info("  - {}", entity));
    }

    @Test
    @DisplayName("测试子图扩展")
    void testExpandSubgraph() {
        // 先通过向量搜索找到锚点实体
        graphStore.ensureVectorIndex(TEST_KB_ID);
        List<String> anchorEntities = graphRetriever.searchByVector(TEST_KB_ID, "Milvus向量数据库", 0.5, 5);

        if (anchorEntities.isEmpty()) {
            log.info("未找到锚点实体，跳过子图扩展测试");
            return;
        }

        // 1-hop 扩展
        List<String> triples1Hop = graphRetriever.expandSubgraph(TEST_KB_ID, anchorEntities, 1, 20);
        log.info("1-hop 扩展结果 ({} 个三元组):", triples1Hop.size());
        triples1Hop.forEach(triple -> log.info("  {}", triple));

        // 2-hop 扩展
        List<String> triples2Hop = graphRetriever.expandSubgraph(TEST_KB_ID, anchorEntities, 2, 30);
        log.info("2-hop 扩展结果 ({} 个三元组):", triples2Hop.size());
        triples2Hop.forEach(triple -> log.info("  {}", triple));
    }

    @Test
    @DisplayName("测试向量语义检索完整流程")
    void testRetrieveByVector() {
        List<String> questions = List.of(
                "Milvus 是谁开发的？",
                "向量数据库有哪些功能？"
        );

        for (String question : questions) {
            log.info("问题: {}", question);
            List<String> triples = graphRagService.retrieveByVector(TEST_KB_ID, question);
            if (triples.isEmpty()) {
                log.info("未找到相关三元组");
            } else {
                log.info("检索到 {} 个三元组:", triples.size());
                triples.forEach(triple -> log.info("  {}", triple));
            }
        }
    }

    @Test
    @DisplayName("测试向量检索并生成回答")
    void testRetrieveWithAnswerByVector() {
        String question = "Milvus 是什么？它有哪些特点？";
        log.info("问题: {}", question);

        String answer = graphRagService.retrieveWithAnswerByVector(TEST_KB_ID, question, chatModel);
        log.info("回答: {}", answer);
    }

    @Test
    @DisplayName("测试向量检索并返回 Content")
    void testRetrieveAsContentByVector() {
        String question = "向量数据库是什么？";
        log.info("问题: {}", question);

        List<Content> contents = graphRagService.retrieveAsContentByVector(TEST_KB_ID, question);
        log.info("返回 Content 数量: {}", contents.size());
        contents.forEach(c -> log.info("内容: {}", c.textSegment().text()));
    }

    // ==================== 混合检索测试（Hybrid Search） ====================

    @Test
    @DisplayName("测试实体精确匹配")
    void testSearchByEntityMatch() {
        List<String> entities = List.of("Milvus", "向量数据库");

        List<String> results = graphRetriever.searchByEntityMatch(TEST_KB_ID, entities, 5);
        log.info("实体精确匹配结果: entities={}, 找到 {} 个实体", entities, results.size());
        results.forEach(entity -> log.info("  - {}", entity));
    }

    @Test
    @DisplayName("测试混合检索（Hybrid Search）")
    void testRetrieveHybrid() {
        List<String> questions = List.of(
                "Milvus 是谁开发的？",
                "李雷和韩梅梅的关系是什么？",
                "向量数据库有哪些应用场景？"
        );

        for (String question : questions) {
            log.info("=== 问题: {} ===", question);
            List<String> triples = graphRagService.retrieveHybrid(TEST_KB_ID, question, chatModel);
            if (triples.isEmpty()) {
                log.info("未找到相关三元组");
            } else {
                log.info("检索到 {} 个三元组:", triples.size());
                triples.forEach(triple -> log.info("  {}", triple));
            }
        }
    }

    @Test
    @DisplayName("测试混合检索并生成回答")
    void testRetrieveWithAnswerHybrid() {
        String question = "Milvus 是什么？它有哪些特点？";
        log.info("问题: {}", question);

        String answer = graphRagService.retrieveWithAnswerHybrid(TEST_KB_ID, question, chatModel);
        log.info("回答: {}", answer);
    }

    @Test
    @DisplayName("测试混合检索 - 精确实体查询场景")
    void testRetrieveHybridWithExactEntity() {
        // 这个测试场景模拟用户查询一个精确的实体名称
        // 向量检索可能找不到，但实体精确匹配能找到
        String question = "告诉我关于 Zilliz 的信息";
        log.info("问题: {}", question);

        List<String> triples = graphRagService.retrieveHybrid(TEST_KB_ID, question, chatModel);
        log.info("混合检索结果 ({} 个三元组):", triples.size());
        triples.forEach(triple -> log.info("  {}", triple));
    }

    @Test
    @DisplayName("测试混合检索 ContentRetriever 集成")
    void testGraphContentRetrieverHybrid() {
        GraphContentRetriever contentRetriever = GraphContentRetriever.builder()
                .knowledgeBaseId(TEST_KB_ID)
                .graphRagService(graphRagService)
                .chatModel(chatModel)
                .useHybridSearch(true)  // 启用混合检索
                .build();

        String question = "关羽跟诸葛亮什么关系";
        log.info("问题: {}", question);

        List<Content> contents = contentRetriever.retrieve(new Query(question));
        log.info("混合检索结果数量: {}", contents.size());
        contents.forEach(c -> log.info("  - {}", c.textSegment().text()));
    }
}
