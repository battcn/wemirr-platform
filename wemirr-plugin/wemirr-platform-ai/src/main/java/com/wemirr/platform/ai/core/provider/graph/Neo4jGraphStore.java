package com.wemirr.platform.ai.core.provider.graph;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.rag.content.retriever.neo4j.Neo4jGraph;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Neo4j图谱存储
 *
 * @author xJh
 * @since 2025/12/11
 **/
@Slf4j
@Component
@ConditionalOnProperty(prefix = "rag", name = "graph-enabled", havingValue = "true")
public class Neo4jGraphStore {

    private final Driver driver;
    private final Neo4jGraph neo4jGraph;
    private final VectorStoreProperties.Neo4jConfig config;

    /**
     * 缓存每个知识库的 CustomKnowledgeGraphWriter
     */
    private final Map<String, CustomKnowledgeGraphWriter> writerCache = new ConcurrentHashMap<>();

    public Neo4jGraphStore(VectorStoreProperties properties) {
        this.config = properties.getNeo4j();

        this.driver = GraphDatabase.driver(
                config.getUri(),
                AuthTokens.basic(config.getUsername(), config.getPassword())
        );

        this.neo4jGraph = Neo4jGraph.builder()
                .driver(driver)
                .build();

        log.info("Neo4j图谱存储初始化成功: uri={}", config.getUri());

        // 验证连接
        try (Session session = driver.session()) {
            session.run("RETURN 1").consume();
            log.info("Neo4j连接验证成功");
        } catch (Exception e) {
            log.error("Neo4j连接失败", e);
            throw new RuntimeException("Neo4j连接失败", e);
        }
    }

    /**
     * 获取指定知识库的 Label（带前缀）
     */
    private String getKnowledgeBaseLabel(String knowledgeBaseId) {
        return config.getLabelPrefix() + knowledgeBaseId;
    }

    /**
     * 获取或创建指定知识库的 Writer
     * 使用自定义实现修复关系类型参数化问题
     */
    private CustomKnowledgeGraphWriter getWriter(String knowledgeBaseId) {
        return writerCache.computeIfAbsent(knowledgeBaseId, kbId ->
                CustomKnowledgeGraphWriter.builder()
                        .driver(driver)
                        .label(getKnowledgeBaseLabel(kbId))
                        .idProperty(config.getIdProperty())
                        .textProperty(config.getTextProperty())
                        .build()
        );
    }

    /**
     * 向指定知识库添加图谱文档
     *
     * @param knowledgeBaseId 知识库ID
     * @param graphDocuments  图谱文档列表
     * @param includeSource   是否包含源文档
     */
    public void addGraphDocuments(String knowledgeBaseId, List<GraphDocument> graphDocuments, boolean includeSource) {
        CustomKnowledgeGraphWriter writer = getWriter(knowledgeBaseId);
        writer.addGraphDocuments(graphDocuments, includeSource);
        log.info("图谱文档已添加到知识库: knowledgeBaseId={}, label={}, documents={}",
                knowledgeBaseId, getKnowledgeBaseLabel(knowledgeBaseId), graphDocuments.size());
    }

    public void addGraphDocuments(List<GraphDocument> graphDocuments, boolean includeSource) {
        CustomKnowledgeGraphWriter writer = CustomKnowledgeGraphWriter.builder()
                .driver(driver)
                .idProperty(config.getIdProperty())
                .textProperty(config.getTextProperty())
                .build();
        writer.addGraphDocuments(graphDocuments, includeSource);
    }

    /**
     * 获取 Neo4jGraph 实例（用于构建 Retriever，查询整个数据库）
     */
    public Neo4jGraph getNeo4jGraph() {
        return neo4jGraph;
    }

    /**
     * 获取 Driver 实例（用于自定义查询）
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * 获取指定知识库的 Label 前缀
     */
    public String getLabelPrefix() {
        return config.getLabelPrefix();
    }

    /**
     * 获取指定知识库的完整 Label
     *
     * @param knowledgeBaseId 知识库ID
     * @return 完整的 Label（如 KB_xxx）
     */
    public String getKnowledgeBaseLabelPublic(String knowledgeBaseId) {
        return getKnowledgeBaseLabel(knowledgeBaseId);
    }

    /**
     * 获取指定知识库的 Schema（仅包含该知识库的节点和关系）
     *
     * @param knowledgeBaseId 知识库ID
     * @return 知识库专属的 Schema 描述
     */
    public String getKnowledgeBaseSchema(String knowledgeBaseId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        StringBuilder schema = new StringBuilder();

        try (Session session = driver.session()) {
            // 1. 获取该知识库的节点类型和属性
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
                // Flatten properties list
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

            // 2. 获取该知识库的关系类型
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
                // 过滤掉 KB 标签
                sourceLabels = sourceLabels.stream().filter(l -> !l.equals(kbLabel) && !l.equals("Document")).collect(Collectors.toList());
                targetLabels = targetLabels.stream().filter(l -> !l.equals(kbLabel) && !l.equals("Document")).collect(Collectors.toList());

                relInfo.put("source", sourceLabels.isEmpty() ? "Entity" : sourceLabels.get(0));
                relInfo.put("target", targetLabels.isEmpty() ? "Entity" : targetLabels.get(0));
                relTypes.add(relInfo);
            }

            // 3. 构建 Schema 描述
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

            schema.append("\nIMPORTANT: All nodes have the label `").append(kbLabel).append("` which MUST be included in queries.");

        } catch (Exception e) {
            log.error("Failed to get knowledge base schema: {}", knowledgeBaseId, e);
            schema.append("Schema unavailable. Use generic queries with label `").append(kbLabel).append("`.");
        }

        return schema.toString();
    }

    /**
     * 生成按知识库隔离的 Cypher 查询 Prompt（使用知识库专属 Schema）
     *
     * @param knowledgeBaseId 知识库ID
     * @return 包含知识库过滤条件的 Prompt
     */
    public String buildKnowledgeBasePrompt(String knowledgeBaseId) {
        String kbLabel = getKnowledgeBaseLabel(knowledgeBaseId);
        String kbSchema = getKnowledgeBaseSchema(knowledgeBaseId);

        return """
            Task: Generate a Cypher statement to query a graph database.
            
            CRITICAL RULES - YOU MUST FOLLOW:
            1. Output ONLY the Cypher query, nothing else.
            2. Do NOT include any explanations, comments, or natural language text.
            3. Do NOT say "I cannot", "I don't know", or ask for clarification.
            4. If you cannot answer, return a simple query: MATCH (n:`%s`) RETURN n LIMIT 5
            5. The output must start with MATCH, RETURN, CALL, or WITH - never with natural language.
            
            QUERY CONSTRAINTS:
            1. You MUST only query nodes with the label `%s`.
            2. Wrap relationship types with special characters in backticks: -[:`关系名称`]->
            3. Always include the knowledge base label `%s` in MATCH patterns.
            4. Use CONTAINS for fuzzy text matching on Chinese text.
            
            Schema:
            %s
            
            Examples:
            User asks: 刘备和谁结拜？
            Cypher: MATCH (n:`%s`)-[r]->(m:`%s`) WHERE n.id CONTAINS '刘备' RETURN n.id, type(r), m.id LIMIT 10
            
            User asks: 诸葛亮是什么职位？
            Cypher: MATCH (n:`%s`) WHERE n.id CONTAINS '诸葛亮' RETURN n LIMIT 10
            
            Now generate a Cypher query for: {{question}}
            Cypher:
            """.formatted(kbLabel, kbLabel, kbLabel, kbSchema, kbLabel, kbLabel, kbLabel);
    }

}
