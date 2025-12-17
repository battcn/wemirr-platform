package com.wemirr.platform.ai.core.provider.graph;

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
 * 自定义 KnowledgeGraphWriter
 *
 * @author xJh
 * @since 2025/12/17
 */
@Slf4j
@Builder
public class CustomKnowledgeGraphWriter {

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
                // 1. 创建节点
                createNodes(session, doc.nodes());

                // 2. 创建关系（每个关系单独执行，使用动态类型）
                createRelationships(session, doc.relationships());

                // 3. 可选：创建源文档节点
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
     * 关键修复：关系类型直接拼接到 Cypher 中，而非参数化
     */
    private void createRelationships(Session session, Set<GraphEdge> relationships) {
        for (GraphEdge edge : relationships) {
            String sourceId = edge.sourceNode().id();
            String targetId = edge.targetNode().id();
            String relType = edge.type();

            // 关系类型必须转义（处理中文、空格等特殊字符）
            String escapedRelType = escapeLabel(relType);

            // 动态构建 Cypher，关系类型直接嵌入
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

        // 创建 Document 节点
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

        // 关联文档到所有实体节点（Document -> Entity，符合知识图谱最佳实践）
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
        // 如果包含特殊字符，使用反引号包裹
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
