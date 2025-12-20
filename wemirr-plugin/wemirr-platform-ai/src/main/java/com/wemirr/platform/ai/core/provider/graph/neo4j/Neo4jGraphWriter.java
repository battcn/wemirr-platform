package com.wemirr.platform.ai.core.provider.graph.neo4j;

import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.data.document.graph.GraphEdge;
import dev.langchain4j.community.data.document.graph.GraphNode;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Neo4j 图谱写入器
 * <p>
 * 负责将 GraphDocument 写入 Neo4j 数据库，包括：
 * - 节点创建（带知识库隔离标签）
 * - 关系创建（正确处理中文关系类型）
 * - 源文档节点创建
 * - 向量嵌入生成与存储（用于语义检索）
 *
 * @author xJh
 * @since 2025/12/17
 */
@Slf4j
@Builder
public class Neo4jGraphWriter {

    private final Driver driver;
    private final String label;
    private final String idProperty;
    private final String textProperty;
    
    /**
     * 向量模型（可选），用于生成节点的语义向量
     */
    private final EmbeddingModel embeddingModel;

    private static final Pattern SPECIAL_CHARS = Pattern.compile("[^a-zA-Z0-9_]");

    /**
     * 添加图谱文档到 Neo4j
     *
     * @param graphDocuments 图谱文档列表
     * @param includeSource  是否包含源文档
     */
    public void addGraphDocuments(List<GraphDocument> graphDocuments, boolean includeSource) {
        try (Session session = driver.session()) {
            for (GraphDocument doc : graphDocuments) {
                createNodes(session, doc.nodes());
                createRelationships(session, doc.relationships());
                if (includeSource && doc.source() != null) {
                    createSourceDocument(session, doc);
                }
            }
        }
    }

    /**
     * 批量创建节点（支持属性和向量写入）
     * <p>
     * 改进：
     * 1. 从节点 properties 中提取 description
     * 2. 构建语义文本 (type + name + description)
     * 3. 生成向量嵌入并存入 Neo4j
     */
    private void createNodes(Session session, Set<GraphNode> nodes) {
        for (GraphNode node : nodes) {
            String nodeType = sanitizeLabel(node.type());
            String escapedNodeType = escapeLabel(nodeType);
            String escapedLabel = label != null ? escapeLabel(label) : null;

            // 从 properties 中获取描述信息
            Map<String, String> props = node.properties();
            String description = props != null ? props.getOrDefault("description", "") : "";
            
            // 构建用于向量化的语义文本
            String textToEmbed = buildTextForEmbedding(nodeType, node.id(), description);
            
            // 生成向量嵌入（如果 EmbeddingModel 可用）
            List<Float> embedding = generateEmbedding(textToEmbed);

            String cypher;
            if (escapedLabel != null && !escapedLabel.isEmpty()) {
                if (embedding != null && !embedding.isEmpty()) {
                    // 有向量的情况：存储 id, description, embedding
                    cypher = String.format("""
                        MERGE (n:%s:%s {%s: $id})
                        ON CREATE SET n.description = $description, n.embedding = $embedding, n.created = timestamp()
                        ON MATCH SET n.description = $description, n.embedding = $embedding, n.updated = timestamp()
                        """,
                        escapedLabel, escapedNodeType, idProperty
                    );
                    session.run(cypher, Values.parameters(
                        "id", node.id(),
                        "description", description,
                        "embedding", embedding
                    ));
                } else {
                    // 无向量的情况：只存储 id 和 description
                    cypher = String.format("""
                        MERGE (n:%s:%s {%s: $id})
                        ON CREATE SET n.description = $description, n.created = timestamp()
                        ON MATCH SET n.description = $description, n.updated = timestamp()
                        """,
                        escapedLabel, escapedNodeType, idProperty
                    );
                    session.run(cypher, Values.parameters(
                        "id", node.id(),
                        "description", description
                    ));
                }
            } else {
                if (embedding != null && !embedding.isEmpty()) {
                    cypher = String.format("""
                        MERGE (n:%s {%s: $id})
                        ON CREATE SET n.description = $description, n.embedding = $embedding, n.created = timestamp()
                        ON MATCH SET n.description = $description, n.embedding = $embedding, n.updated = timestamp()
                        """,
                        escapedNodeType, idProperty
                    );
                    session.run(cypher, Values.parameters(
                        "id", node.id(),
                        "description", description,
                        "embedding", embedding
                    ));
                } else {
                    cypher = String.format("""
                        MERGE (n:%s {%s: $id})
                        ON CREATE SET n.description = $description, n.created = timestamp()
                        ON MATCH SET n.description = $description, n.updated = timestamp()
                        """,
                        escapedNodeType, idProperty
                    );
                    session.run(cypher, Values.parameters(
                        "id", node.id(),
                        "description", description
                    ));
                }
            }

            log.debug("Created node: {} ({}) with embedding={}", node.id(), nodeType, embedding != null);
        }
    }
    
    /**
     * 构建用于向量化的语义文本
     * 格式: "类型: 名称\n描述"
     */
    private String buildTextForEmbedding(String type, String name, String description) {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(": ").append(name);
        if (description != null && !description.isBlank()) {
            sb.append("\n").append(description);
        }
        return sb.toString();
    }
    
    /**
     * 生成向量嵌入
     * 
     * @param text 待向量化的文本
     * @return 向量列表，如果 EmbeddingModel 不可用则返回 null
     */
    private List<Float> generateEmbedding(String text) {
        if (embeddingModel == null || text == null || text.isBlank()) {
            return null;
        }
        try {
            Response<Embedding> response = embeddingModel.embed(text);
            return response.content().vectorAsList();
        } catch (Exception e) {
            log.warn("生成向量嵌入失败: text={}, error={}", text.substring(0, Math.min(50, text.length())), e.getMessage());
            return null;
        }
    }

    /**
     * 逐个创建关系，每个关系使用其真实类型
     * <p>
     */
    private void createRelationships(Session session, Set<GraphEdge> relationships) {
        for (GraphEdge edge : relationships) {
            String sourceId = edge.sourceNode().id();
            String targetId = edge.targetNode().id();
            String relType = edge.type();
            String escapedRelType = escapeLabel(relType);

            String cypher = String.format("""
                MATCH (source {%s: $sourceId})
                MATCH (target {%s: $targetId})
                MERGE (source)-[r:%s]->(target)
                ON CREATE SET r.created = timestamp()
                ON MATCH SET r.updated = timestamp()
                """,
                idProperty, idProperty, escapedRelType
            );

            session.run(cypher, Values.parameters(
                "sourceId", sourceId,
                "targetId", targetId
            ));

            log.debug("Created relationship: ({}) -[{}]-> ({})", sourceId, relType, targetId);
        }
    }

    /**
     * 创建源文档节点并关联
     */
    private void createSourceDocument(Session session, GraphDocument doc) {
        String docText = doc.source().text();
        String docId = "doc_" + docText.hashCode();

        String escapedLabel = label != null ? escapeLabel(label) : null;

        String createDocCypher;
        if (escapedLabel != null && !escapedLabel.isEmpty()) {
            createDocCypher = String.format("""
                MERGE (d:Document:%s {%s: $docId})
                SET d.%s = $text
                """,
                escapedLabel, idProperty, textProperty
            );
        } else {
            createDocCypher = String.format("""
                MERGE (d:Document {%s: $docId})
                SET d.%s = $text
                """,
                idProperty, textProperty
            );
        }
        session.run(createDocCypher, Values.parameters("docId", docId, "text", docText));

        // 关联文档到所有实体节点
        for (GraphNode node : doc.nodes()) {
            String linkCypher = String.format("""
                MATCH (d:Document {%s: $docId})
                MATCH (n {%s: $nodeId})
                MERGE (d)-[:MENTIONS]->(n)
                """, idProperty, idProperty
            );
            session.run(linkCypher, Values.parameters("docId", docId, "nodeId", node.id()));
        }

        log.debug("Created source document: {}", docId);
    }

    /**
     * 转义标签/关系类型（处理中文、空格等特殊字符）
     */
    private String escapeLabel(String label) {
        if (label == null || label.isEmpty()) {
            return "UNKNOWN";
        }
        if (SPECIAL_CHARS.matcher(label).find()) {
            return "`" + label.replace("`", "``") + "`";
        }
        return label;
    }

    /**
     * 清理标签名称
     */
    private String sanitizeLabel(String label) {
        if (label == null || label.isEmpty()) {
            return "UNKNOWN";
        }
        return label.trim();
    }
}
