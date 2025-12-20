package com.wemirr.platform.ai.core.provider.graph.neo4j;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import com.wemirr.platform.ai.core.provider.graph.GraphStore;
import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.rag.content.retriever.neo4j.Neo4jGraph;
import dev.langchain4j.model.embedding.EmbeddingModel;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Neo4j 图存储实现
 * <p>
 * 实现 GraphStore 接口，提供 Neo4j 特定的存储操作：
 * - 知识库隔离（通过 Label 前缀）
 * - 图谱文档写入
 * - 索引管理
 * - 统计查询
 *
 * @author xJh
 * @since 2025/12/11
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "rag", name = "graph-enabled", havingValue = "true")
public class Neo4jGraphStore implements GraphStore {

    @Getter
    private final Driver driver;

    @Getter
    private final Neo4jGraph neo4jGraph;

    @Getter
    private final VectorStoreProperties.Neo4jConfig config;

    public Neo4jGraphStore(VectorStoreProperties properties) {
        this.config = properties.getNeo4j();

        this.driver = GraphDatabase.driver(
                config.getUri(),
                AuthTokens.basic(config.getUsername(), config.getPassword())
        );

        this.neo4jGraph = Neo4jGraph.builder()
                .driver(driver)
                .build();

        log.info("Neo4j 图存储初始化成功: uri={}", config.getUri());
        verifyConnection();
    }

    /**
     * 验证 Neo4j 连接
     */
    private void verifyConnection() {
        try (Session session = driver.session()) {
            session.run("RETURN 1").consume();
            log.info("Neo4j 连接验证成功");
        } catch (Exception e) {
            log.error("Neo4j 连接失败", e);
            throw new RuntimeException("Neo4j 连接失败", e);
        }
    }


    @Override
    public void addGraphDocuments(String knowledgeBaseId, List<GraphDocument> graphDocuments, boolean includeSource) {
        // 不带向量模型的版本（不生成向量嵌入）
        addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource, null);
    }

    /**
     * 添加图谱文档（带向量模型）
     * <p>
     * 通过传入的 EmbeddingModel 为节点生成向量嵌入
     *
     * @param knowledgeBaseId 知识库ID
     * @param graphDocuments  图谱文档列表
     * @param includeSource   是否包含源文档
     * @param embeddingModel  向量模型（可为 null，null 时不生成向量）
     */
    public void addGraphDocuments(String knowledgeBaseId, List<GraphDocument> graphDocuments,
                                  boolean includeSource, EmbeddingModel embeddingModel) {
        Neo4jGraphWriter writer = Neo4jGraphWriter.builder()
                .driver(driver)
                .label(getKnowledgeBaseLabel(knowledgeBaseId))
                .idProperty(config.getIdProperty())
                .textProperty(config.getTextProperty())
                .embeddingModel(embeddingModel)
                .build();
        writer.addGraphDocuments(graphDocuments, includeSource);
        log.info("图谱文档已添加: knowledgeBaseId={}, label={}, documents={}, withEmbedding={}",
                knowledgeBaseId, getKnowledgeBaseLabel(knowledgeBaseId), graphDocuments.size(), embeddingModel != null);
    }

    @Override
    public void addGraphDocuments(List<GraphDocument> graphDocuments, boolean includeSource) {
        Neo4jGraphWriter writer = Neo4jGraphWriter.builder()
                .driver(driver)
                .idProperty(config.getIdProperty())
                .textProperty(config.getTextProperty())
                .build();
        writer.addGraphDocuments(graphDocuments, includeSource);
    }

    /**
     * 确保知识库的向量索引存在
     * <p>
     * 用于语义向量检索，支持基于 embedding 属性的相似度搜索
     *
     * @param knowledgeBaseId 知识库ID
     */
    @Override
    public void ensureVectorIndex(String knowledgeBaseId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        String indexName = "vector_index_" + knowledgeBaseId;
        int dimension = config.getEmbeddingDimension();
        String similarityFunction = config.getSimilarityFunction();

        try (Session session = driver.session()) {
            // 检查索引是否已存在
            String checkQuery = "SHOW INDEXES WHERE name = $indexName";
            Result result = session.run(checkQuery, Values.parameters("indexName", indexName));
            if (result.hasNext()) {
                var record = result.single();
                String state = record.get("state").asString();
                if ("ONLINE".equals(state)) {
                    log.debug("向量索引已存在且在线: {}", indexName);
                    return;
                }
                log.debug("向量索引存在但状态为: {}, 等待就绪...", state);
            } else {
                // 创建向量索引
                String createIndexCypher = String.format("""
                    CREATE VECTOR INDEX %s IF NOT EXISTS
                    FOR (n:`%s`)
                    ON (n.embedding)
                    OPTIONS {indexConfig: {
                      `vector.dimensions`: %d,
                      `vector.similarity_function`: '%s'
                    }}
                    """, indexName, kbLabel, dimension, similarityFunction);

                session.run(createIndexCypher).consume();
                log.info("向量索引创建成功: indexName={}, label={}, dimension={}, similarity={}",
                        indexName, kbLabel, dimension, similarityFunction);
            }

            // 等待索引变为 ONLINE 状态
            waitForIndexOnline(session, indexName);

        } catch (Exception e) {
            log.error("创建向量索引失败: knowledgeBaseId={}", knowledgeBaseId, e);
        }
    }

    /**
     * 等待索引变为 ONLINE 状态
     */
    private void waitForIndexOnline(Session session, String indexName) {
        int maxRetries = 30;
        int retryInterval = 100; // ms

        for (int i = 0; i < maxRetries; i++) {
            try {
                String checkQuery = "SHOW INDEXES WHERE name = $indexName";
                Result result = session.run(checkQuery, Values.parameters("indexName", indexName));
                if (result.hasNext()) {
                    String state = result.single().get("state").asString();
                    if ("ONLINE".equals(state)) {
                        log.debug("全文索引已就绪: {}", indexName);
                        return;
                    }
                    log.trace("等待索引就绪: {} (当前状态: {})", indexName, state);
                }
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("等待索引就绪被中断: {}", indexName);
                return;
            }
        }
        log.warn("等待索引就绪超时: {}", indexName);
    }

    @Override
    public void deleteKnowledgeBase(String knowledgeBaseId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        try (Session session = driver.session()) {
            String cypher = String.format("MATCH (n:`%s`) DETACH DELETE n", kbLabel);
            var result = session.run(cypher);
            var summary = result.consume();
            log.info("知识库已删除: knowledgeBaseId={}, nodesDeleted={}",
                    knowledgeBaseId, summary.counters().nodesDeleted());
        }
    }

    @Override
    public void deleteDocument(String knowledgeBaseId, String documentId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                // 删除仅被此文档引用的实体
                String deleteOrphanEntities = String.format("""
                    MATCH (d:Document:`%s` {id: $docId})-[:MENTIONS]->(e)
                    WHERE NOT EXISTS {
                        MATCH (other:Document)-[:MENTIONS]->(e)
                        WHERE other <> d
                    }
                    DETACH DELETE e
                    """, kbLabel);
                tx.run(deleteOrphanEntities, Values.parameters("docId", documentId));

                // 删除文档节点
                String deleteDoc = String.format(
                        "MATCH (d:Document:`%s` {id: $docId}) DETACH DELETE d", kbLabel);
                tx.run(deleteDoc, Values.parameters("docId", documentId));
                return null;
            });
            log.info("文档已删除: knowledgeBaseId={}, documentId={}", knowledgeBaseId, documentId);
        }
    }

    @Override
    public Map<String, Object> getStatistics(String knowledgeBaseId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        Map<String, Object> stats = new HashMap<>();

        try (Session session = driver.session()) {
            // 节点数量
            String nodeCountQuery = String.format("MATCH (n:`%s`) RETURN count(n) as count", kbLabel);
            long nodeCount = session.run(nodeCountQuery).single().get("count").asLong();
            stats.put("nodeCount", nodeCount);

            // 文档数量
            String docCountQuery = String.format("MATCH (d:Document:`%s`) RETURN count(d) as count", kbLabel);
            long docCount = session.run(docCountQuery).single().get("count").asLong();
            stats.put("documentCount", docCount);

            // 关系数量
            String relCountQuery = String.format("MATCH (:`%s`)-[r]->() RETURN count(r) as count", kbLabel);
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

    @Override
    public String getSchema(String knowledgeBaseId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        StringBuilder schema = new StringBuilder();

        try (Session session = driver.session()) {
            // 获取节点类型和属性
            String nodeQuery = """
                MATCH (n:`%s`)
                WITH labels(n) AS nodeLabels, keys(n) AS props
                UNWIND nodeLabels AS label
                WITH label, props
                WHERE label <> '%s' AND label <> 'Document'
                RETURN DISTINCT label AS nodeType, collect(DISTINCT props) AS properties
                LIMIT 50
                """.formatted(kbLabel, kbLabel);

            var nodeResult = session.run(nodeQuery);
            List<Map<String, Object>> nodeTypes = new ArrayList<>();
            while (nodeResult.hasNext()) {
                var record = nodeResult.next();
                Map<String, Object> nodeInfo = new HashMap<>();
                nodeInfo.put("type", record.get("nodeType").asString());
                List<Object> propsList = record.get("properties").asList();
                List<String> allProps = propsList.stream()
                        .filter(p -> p instanceof List)
                        .flatMap(p -> ((List<?>) p).stream())
                        .map(Object::toString)
                        .distinct()
                        .collect(Collectors.toList());
                nodeInfo.put("properties", allProps);
                nodeTypes.add(nodeInfo);
            }

            // 获取关系类型
            String relQuery = """
                MATCH (n:`%s`)-[r]->(m:`%s`)
                RETURN DISTINCT type(r) AS relType,
                       labels(n) AS sourceLabels,
                       labels(m) AS targetLabels
                LIMIT 50
                """.formatted(kbLabel, kbLabel);

            var relResult = session.run(relQuery);
            List<Map<String, Object>> relTypes = new ArrayList<>();
            while (relResult.hasNext()) {
                var record = relResult.next();
                Map<String, Object> relInfo = new HashMap<>();
                relInfo.put("type", record.get("relType").asString());

                List<String> sourceLabels = record.get("sourceLabels").asList(v -> v.asString());
                List<String> targetLabels = record.get("targetLabels").asList(v -> v.asString());
                sourceLabels = sourceLabels.stream()
                        .filter(l -> !l.equals(kbLabel) && !l.equals("Document"))
                        .collect(Collectors.toList());
                targetLabels = targetLabels.stream()
                        .filter(l -> !l.equals(kbLabel) && !l.equals("Document"))
                        .collect(Collectors.toList());

                relInfo.put("source", sourceLabels.isEmpty() ? "Entity" : sourceLabels.get(0));
                relInfo.put("target", targetLabels.isEmpty() ? "Entity" : targetLabels.get(0));
                relTypes.add(relInfo);
            }

            // 构建 Schema 描述
            schema.append("Node types in this knowledge base:\n");
            if (nodeTypes.isEmpty()) {
                schema.append("  (No nodes found)\n");
            } else {
                for (Map<String, Object> node : nodeTypes) {
                    schema.append("  - ").append(node.get("type"));
                    @SuppressWarnings("unchecked")
                    List<String> props = (List<String>) node.get("properties");
                    if (props != null && !props.isEmpty()) {
                        schema.append(" {").append(String.join(", ", props)).append("}");
                    }
                    schema.append("\n");
                }
            }

            schema.append("\nRelationship types in this knowledge base:\n");
            if (relTypes.isEmpty()) {
                schema.append("  (No relationships found)\n");
            } else {
                for (Map<String, Object> rel : relTypes) {
                    schema.append("  - (").append(rel.get("source")).append(")-[:`")
                            .append(rel.get("type")).append("`]->(").append(rel.get("target")).append(")\n");
                }
            }

            schema.append("\nIMPORTANT: All nodes have the label `").append(kbLabel)
                    .append("` which MUST be included in queries.");

        } catch (Exception e) {
            log.error("获取 Schema 失败: {}", knowledgeBaseId, e);
            schema.append("Schema unavailable. Use generic queries with label `").append(kbLabel).append("`.");
        }

        return schema.toString();
    }

    @Override
    public String getLabelPrefix() {
        return config.getLabelPrefix();
    }

    @Override
    public String getKnowledgeBaseLabel(String knowledgeBaseId) {
        return config.getLabelPrefix() + knowledgeBaseId;
    }

    @Override
    public boolean isConnected() {
        try (Session session = driver.session()) {
            session.run("RETURN 1").consume();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @PreDestroy
    public void close() {
        if (driver != null) {
            driver.close();
            log.info("Neo4j 连接已关闭");
        }
    }

    @Override
    public GraphData getGraphData(String knowledgeBaseId, int limit) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        try (Session session = driver.session()) {
            // 查询节点（排除 Document 类型，限制数量）
            String nodeQuery = String.format("""
                MATCH (n:`%s`)
                WHERE NOT n:Document
                RETURN elementId(n) AS nodeId, n.id AS id, labels(n) AS labels, properties(n) AS props
                LIMIT %d
                """, kbLabel, limit);

            var nodeResult = session.run(nodeQuery);
            while (nodeResult.hasNext()) {
                var record = nodeResult.next();
                Map<String, Object> node = new HashMap<>();
                node.put("nodeId", record.get("nodeId").asString());
                node.put("id", record.get("id").asString());
                node.put("labels", record.get("labels").asList(v -> v.asString()));
                node.put("properties", record.get("props").asMap());
                // 设置显示名称
                Map<String, Object> props = record.get("props").asMap();
                node.put("name", props.getOrDefault("id", props.getOrDefault("name", "Unknown")));
                nodes.add(node);
            }

            // 查询关系（基于已查询的节点）
            if (!nodes.isEmpty()) {
                String edgeQuery = String.format("""
                    MATCH (n:`%s`)-[r]->(m:`%s`)
                    WHERE NOT n:Document AND NOT m:Document
                    RETURN elementId(r) AS edgeId, elementId(n) AS sourceId, elementId(m) AS targetId,
                           n.id AS source, m.id AS target, type(r) AS type, properties(r) AS props
                    LIMIT %d
                    """, kbLabel, kbLabel, limit * 2);

                var edgeResult = session.run(edgeQuery);
                while (edgeResult.hasNext()) {
                    var record = edgeResult.next();
                    Map<String, Object> edge = new HashMap<>();
                    edge.put("edgeId", record.get("edgeId").asString());
                    edge.put("sourceId", record.get("sourceId").asString());
                    edge.put("targetId", record.get("targetId").asString());
                    edge.put("source", record.get("source").asString());
                    edge.put("target", record.get("target").asString());
                    edge.put("type", record.get("type").asString());
                    edge.put("properties", record.get("props").asMap());
                    // 设置显示标签
                    edge.put("label", record.get("type").asString());
                    edges.add(edge);
                }
            }

            log.debug("获取图谱可视化数据: kbId={}, nodes={}, edges={}", knowledgeBaseId, nodes.size(), edges.size());

        } catch (Exception e) {
            log.error("获取图谱可视化数据失败: kbId={}", knowledgeBaseId, e);
        }

        return new GraphData(nodes, edges);
    }

    // ==================== Neo4j 特有方法 ====================

    /**
     * 获取 Neo4j 配置
     */
    public VectorStoreProperties.Neo4jConfig getConfig() {
        return config;
    }
}
