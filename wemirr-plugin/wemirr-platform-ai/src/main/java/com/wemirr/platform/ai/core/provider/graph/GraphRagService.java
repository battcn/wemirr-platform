package com.wemirr.platform.ai.core.provider.graph;

import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.data.document.transformer.graph.LLMGraphTransformer;
import dev.langchain4j.community.rag.content.retriever.neo4j.Neo4jText2CypherRetriever;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.query.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GraphRAG 服务层
 * <p>
 * 整合文档处理、图谱存储、检索的完整链路：
 * <ul>
 *   <li>文档 → 图谱提取（使用 Langchain4j LLMGraphTransformer）</li>
 *   <li>图谱存储（使用 CustomKnowledgeGraphWriter，修复关系类型bug）</li>
 *   <li>图谱检索（使用 Langchain4j Neo4jText2CypherRetriever）</li>
 *   <li>知识库隔离（按 knowledgeBaseId 分隔数据）</li>
 * </ul>
 *
 * @author xJh
 * @since 2025/12/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rag", name = "graph-enabled", havingValue = "true")
public class GraphRagService {

    private final Neo4jGraphStore neo4jGraphStore;


    /**
     * 处理文档：提取图谱并存储到指定知识库
     *
     * @param knowledgeBaseId  知识库ID
     * @param documents        文档列表
     * @param graphTransformer 图谱提取器（由调用方提供，支持不同模型配置）
     * @param includeSource    是否存储源文档
     * @return 处理结果统计
     */
    public ProcessResult processDocuments(String knowledgeBaseId,
                                          List<Document> documents,
                                          LLMGraphTransformer graphTransformer,
                                          boolean includeSource) {
        int totalNodes = 0;
        int totalRelationships = 0;

        for (Document document : documents) {
            try {
                // 1. 提取图谱
                List<GraphDocument> graphDocuments = graphTransformer.transformAll(List.of(document));

                // 2. 存储到 Neo4j
                neo4jGraphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);

                // 3. 统计
                for (GraphDocument gd : graphDocuments) {
                    totalNodes += gd.nodes().size();
                    totalRelationships += gd.relationships().size();
                }

                log.debug("Document processed: nodes={}, relationships={}",
                        graphDocuments.stream().mapToInt(gd -> gd.nodes().size()).sum(),
                        graphDocuments.stream().mapToInt(gd -> gd.relationships().size()).sum());

            } catch (Exception e) {
                log.error("Failed to process document: {}", document.text().substring(0, Math.min(100, document.text().length())), e);
            }
        }

        log.info("Documents processed: knowledgeBaseId={}, documents={}, nodes={}, relationships={}",
                knowledgeBaseId, documents.size(), totalNodes, totalRelationships);

        return new ProcessResult(documents.size(), totalNodes, totalRelationships);
    }

    /**
     * 直接存储已提取的图谱文档
     */
    public void storeGraphDocuments(String knowledgeBaseId,
                                    List<GraphDocument> graphDocuments,
                                    boolean includeSource) {
        neo4jGraphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);
    }


    /**
     * 创建指定知识库的 Text2Cypher 检索器
     * <p>
     * 使用 Langchain4j 原生 Neo4jText2CypherRetriever，注入知识库隔离的 Prompt
     *
     * @param knowledgeBaseId 知识库ID
     * @param chatModel       用于生成 Cypher 的 ChatModel
     * @return 检索器实例
     */
    public Neo4jText2CypherRetriever createRetriever(String knowledgeBaseId, ChatModel chatModel) {
        return createRetriever(knowledgeBaseId, chatModel, 1);
    }

    /**
     * 创建指定知识库的 Text2Cypher 检索器（可配置重试次数）
     *
     * @param knowledgeBaseId 知识库ID
     * @param chatModel       用于生成 Cypher 的 ChatModel
     * @param maxRetries      最大重试次数
     * @return 检索器实例
     */
    public Neo4jText2CypherRetriever createRetriever(String knowledgeBaseId, ChatModel chatModel, int maxRetries) {
        String kbPrompt = neo4jGraphStore.buildKnowledgeBasePrompt(knowledgeBaseId);
        log.debug("Knowledge base prompt for {}: {}", knowledgeBaseId, kbPrompt);

        return Neo4jText2CypherRetriever.builder()
                .graph(neo4jGraphStore.getNeo4jGraph())
                .chatModel(chatModel)
                .maxRetries(maxRetries)
                .promptTemplate(PromptTemplate.from(kbPrompt))
                .build();
    }

    /**
     * 直接执行检索
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       ChatModel
     * @return 检索结果，如果无数据则返回空列表
     */
    public List<Content> retrieve(String knowledgeBaseId, String question, ChatModel chatModel) {
        return retrieve(knowledgeBaseId, question, chatModel, false);
    }

    /**
     * 直接执行检索（可选抛出异常）
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       ChatModel
     * @param throwOnEmpty    空结果时是否抛出异常
     * @return 检索结果
     */
    public List<Content> retrieve(String knowledgeBaseId, String question, ChatModel chatModel, boolean throwOnEmpty) {
        Neo4jText2CypherRetriever retriever = createRetriever(knowledgeBaseId, chatModel);
        try {
            List<Content> results = retriever.retrieve(new Query(question));
            log.debug("Retrieved {} results for question '{}' in kb '{}'", 
                    results.size(), question, knowledgeBaseId);
            return results;
        } catch (RuntimeException e) {
            // 处理 Neo4jText2CypherRetriever 空结果异常
            if (e.getMessage() != null && e.getMessage().contains("query result is empty")) {
                log.info("No results found for question '{}' in knowledge base '{}'", question, knowledgeBaseId);
                if (throwOnEmpty) {
                    throw new EmptyResultException("No data found in knowledge base: " + knowledgeBaseId, e);
                }
                return Collections.emptyList();
            }
            throw e;
        }
    }

    /**
     * 检索并生成 LLM 回答
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       ChatModel
     * @return LLM 生成的回答，如果无数据则返回提示信息
     */
    public String retrieveWithAnswer(String knowledgeBaseId, String question, ChatModel chatModel) {
        Neo4jText2CypherRetriever retriever = createRetriever(knowledgeBaseId, chatModel);
        try {
            return retriever.fromLLM(new Query(question));
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("query result is empty")) {
                log.info("No results found for question '{}' in knowledge base '{}'", question, knowledgeBaseId);
                return "抱歉，在当前知识库中未找到相关信息。";
            }
            throw e;
        }
    }

    /**
     * 空结果异常
     */
    public static class EmptyResultException extends RuntimeException {
        public EmptyResultException(String message, Throwable cause) {
            super(message, cause);
        }
    }



    /**
     * 删除指定知识库的所有数据
     *
     * @param knowledgeBaseId 知识库ID
     * @param confirmed       确认删除（防止误操作）
     */
    public void deleteKnowledgeBase(String knowledgeBaseId, boolean confirmed) {
        if (!confirmed) {
            log.warn("Delete operation not confirmed, skipping");
            return;
        }

        String kbLabel = neo4jGraphStore.getKnowledgeBaseLabelPublic(knowledgeBaseId);
        try (Session session = neo4jGraphStore.getDriver().session()) {
            // 删除该知识库的所有节点和关系
            String cypher = String.format("MATCH (n:`%s`) DETACH DELETE n", kbLabel);
            var result = session.run(cypher);
            var summary = result.consume();
            log.info("Knowledge base deleted: knowledgeBaseId={}, nodesDeleted={}",
                    knowledgeBaseId, summary.counters().nodesDeleted());
        }
    }


    public void deleteDocument(String knowledgeBaseId, String documentId) {
        String kbLabel = neo4jGraphStore.getKnowledgeBaseLabelPublic(knowledgeBaseId);
        try (Session session = neo4jGraphStore.getDriver().session()) {
            session.executeWrite(tx -> {
                // 1. 删除仅被此文档引用的实体
                String deleteOrphanEntities = String.format("""
                    MATCH (d:Document:`%s` {id: $docId})-[:MENTIONS]->(e)
                    WHERE NOT EXISTS {
                        MATCH (other:Document)-[:MENTIONS]->(e)
                        WHERE other <> d
                    }
                    DETACH DELETE e
                    """, kbLabel);
                tx.run(deleteOrphanEntities, Values.parameters("docId", documentId));

                // 2. 删除文档节点
                String deleteDoc = String.format(
                        "MATCH (d:Document:`%s` {id: $docId}) DETACH DELETE d", kbLabel);
                tx.run(deleteDoc, Values.parameters("docId", documentId));
                return null;
            });
            log.info("Document deleted: knowledgeBaseId={}, documentId={}", knowledgeBaseId, documentId);
        }
    }

    /**
     * 获取知识库统计信息
     *
     * @param knowledgeBaseId 知识库ID
     * @return 统计信息
     */
    public Map<String, Object> getStatistics(String knowledgeBaseId) {
        String kbLabel = neo4jGraphStore.getKnowledgeBaseLabelPublic(knowledgeBaseId);
        Map<String, Object> stats = new HashMap<>();

        try (Session session = neo4jGraphStore.getDriver().session()) {
            // 节点数量
            String nodeCountQuery = String.format(
                    "MATCH (n:`%s`) RETURN count(n) as count", kbLabel);
            long nodeCount = session.run(nodeCountQuery).single().get("count").asLong();
            stats.put("nodeCount", nodeCount);

            // 文档数量
            String docCountQuery = String.format(
                    "MATCH (d:Document:`%s`) RETURN count(d) as count", kbLabel);
            long docCount = session.run(docCountQuery).single().get("count").asLong();
            stats.put("documentCount", docCount);

            // 关系数量
            String relCountQuery = String.format(
                    "MATCH (:`%s`)-[r]->() RETURN count(r) as count", kbLabel);
            long relCount = session.run(relCountQuery).single().get("count").asLong();
            stats.put("relationshipCount", relCount);

            // 实体类型分布
            String typeDistQuery = String.format("""
                MATCH (n:`%s`)
                WHERE NOT n:Document
                RETURN labels(n) as types, count(n) as count
                ORDER BY count DESC LIMIT 10
                """, kbLabel);
            List<Map<String, Object>> typeDist = session.run(typeDistQuery).list(record -> {
                Map<String, Object> item = new HashMap<>();
                item.put("types", record.get("types").asList());
                item.put("count", record.get("count").asLong());
                return item;
            });
            stats.put("entityTypeDistribution", typeDist);

            // 关系类型分布
            String relTypeDistQuery = String.format("""
                MATCH (:`%s`)-[r]->()
                RETURN type(r) as type, count(r) as count
                ORDER BY count DESC LIMIT 10
                """, kbLabel);
            List<Map<String, Object>> relTypeDist = session.run(relTypeDistQuery).list(record -> {
                Map<String, Object> item = new HashMap<>();
                item.put("type", record.get("type").asString());
                item.put("count", record.get("count").asLong());
                return item;
            });
            stats.put("relationshipTypeDistribution", relTypeDist);

            stats.put("knowledgeBaseId", knowledgeBaseId);
            stats.put("label", kbLabel);
        }

        return stats;
    }



    /**
     * 获取底层 Driver（用于执行自定义 Cypher）
     */
    public Driver getDriver() {
        return neo4jGraphStore.getDriver();
    }

    /**
     * 处理结果记录
     */
    public record ProcessResult(int documentsProcessed, int nodesCreated, int relationshipsCreated) {
    }
}
