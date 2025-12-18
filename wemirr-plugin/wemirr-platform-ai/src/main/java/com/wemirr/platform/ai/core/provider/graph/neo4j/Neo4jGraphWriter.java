package com.wemirr.platform.ai.core.provider.graph.neo4j;

import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.data.document.graph.GraphEdge;
import dev.langchain4j.community.data.document.graph.GraphNode;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Neo4j 图谱写入器
 * <p>
 * 负责将 GraphDocument 写入 Neo4j 数据库，包括：
 * - 节点创建（带知识库隔离标签）
 * - 关系创建（正确处理中文关系类型）
 * - 源文档节点创建
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
     * 批量创建节点
     */
    private void createNodes(Session session, Set<GraphNode> nodes) {
        for (GraphNode node : nodes) {
            String nodeType = sanitizeLabel(node.type());
            String escapedNodeType = escapeLabel(nodeType);
            String escapedLabel = label != null ? escapeLabel(label) : null;

            String cypher;
            if (escapedLabel != null && !escapedLabel.isEmpty()) {
                cypher = String.format("""
                    MERGE (n:%s:%s {%s: $id})
                    ON CREATE SET n.created = timestamp()
                    ON MATCH SET n.updated = timestamp()
                    """,
                    escapedLabel, escapedNodeType, idProperty
                );
            } else {
                cypher = String.format("""
                    MERGE (n:%s {%s: $id})
                    ON CREATE SET n.created = timestamp()
                    ON MATCH SET n.updated = timestamp()
                    """,
                    escapedNodeType, idProperty
                );
            }

            session.run(cypher, Values.parameters("id", node.id()));
            log.debug("Created node: {} ({})", node.id(), nodeType);
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
