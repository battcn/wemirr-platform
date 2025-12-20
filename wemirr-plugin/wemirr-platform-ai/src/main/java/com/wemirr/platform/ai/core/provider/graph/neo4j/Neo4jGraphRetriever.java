package com.wemirr.platform.ai.core.provider.graph.neo4j;

import com.wemirr.platform.ai.core.provider.graph.GraphRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Neo4j 图检索实现
 * <p>
 * 实现 GraphRetriever 接口，提供 Neo4j 特定的检索操作：
 * - 全文索引搜索
 * - 子图扩展（1-2 hop）
 * - Text2Cypher Prompt 构建
 *
 * @author xJh
 * @since 2025/12/18
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rag", name = "graph-enabled", havingValue = "true")
public class Neo4jGraphRetriever implements GraphRetriever {

    private final Neo4jGraphStore graphStore;

    @Override
    public List<String> searchByFulltext(String knowledgeBaseId, List<String> keywords,
                                         double scoreThreshold, int limit) {
        if (keywords == null || keywords.isEmpty()) {
            return Collections.emptyList();
        }

        String indexName = "fulltext_index_" + knowledgeBaseId;
        String kbLabel = graphStore.getKnowledgeBaseLabel(knowledgeBaseId);
        Set<String> results = new LinkedHashSet<>();

        try (Session session = graphStore.getDriver().session()) {
            for (String keyword : keywords) {
                if (keyword == null || keyword.isBlank()) {
                    continue;
                }

                String trimmed = keyword.trim();
                String luceneQuery = "*" + trimmed + "*";

                // 全文索引搜索
                String cypher = """
                    CALL db.index.fulltext.queryNodes($indexName, $query, {limit: $limit})
                    YIELD node, score
                    WHERE score > $threshold
                    RETURN node.id AS entityId, score
                    ORDER BY score DESC
                    """;

                List<Record> records = session.run(cypher, Values.parameters(
                        "indexName", indexName,
                        "query", luceneQuery,
                        "limit", limit,
                        "threshold", scoreThreshold
                )).list();

                for (Record record : records) {
                    String entityId = record.get("entityId").asString();
                    results.add(entityId);
                    log.debug("全文搜索匹配: keyword={}, entity={}, score={}",
                            keyword, entityId, record.get("score").asDouble());
                }

                // 如果全文索引未找到结果，使用 CONTAINS 回退（支持中文）
                if (results.isEmpty()) {
                    String fallbackCypher = """
                        MATCH (n:`%s`)
                        WHERE n.id CONTAINS $keyword AND NOT n:Document
                        RETURN n.id AS entityId
                        LIMIT $limit
                        """.formatted(kbLabel);

                    List<Record> fallbackRecords = session.run(fallbackCypher, Values.parameters(
                            "keyword", trimmed,
                            "limit", limit
                    )).list();

                    for (Record record : fallbackRecords) {
                        String entityId = record.get("entityId").asString();
                        results.add(entityId);
                        log.debug("CONTAINS 回退匹配: keyword={}, entity={}", keyword, entityId);
                    }
                }
            }
        } catch (Exception e) {
            log.error("全文搜索失败: knowledgeBaseId={}, keywords={}", knowledgeBaseId, keywords, e);
        }

        return new ArrayList<>(results);
    }

    @Override
    public List<String> expandSubgraph(String knowledgeBaseId, List<String> anchorEntities,
                                       int hopDepth, int maxTriples) {
        if (anchorEntities == null || anchorEntities.isEmpty()) {
            return Collections.emptyList();
        }

        String kbLabel = graphStore.getKnowledgeBaseLabel(knowledgeBaseId);
        String idProp = graphStore.getConfig().getIdProperty();
        Set<String> triples = new LinkedHashSet<>();

        try (Session session = graphStore.getDriver().session()) {
            String cypher = buildSubgraphCypher(kbLabel, idProp, hopDepth);
            List<Record> records = session.run(cypher, Values.parameters(
                    "entities", anchorEntities,
                    "limit", maxTriples
            )).list();

            for (Record record : records) {
                String head = safeGetString(record, "head");
                String relation = safeGetString(record, "relation");
                String tail = safeGetString(record, "tail");
                if (head != null && tail != null && relation != null) {
                    triples.add("(" + head + ") --[" + relation + "]--> (" + tail + ")");
                }
            }

            log.debug("子图扩展完成: anchorCount={}, triplesFound={}", anchorEntities.size(), triples.size());
        } catch (Exception e) {
            log.error("子图扩展失败: knowledgeBaseId={}, anchors={}", knowledgeBaseId, anchorEntities, e);
        }

        return new ArrayList<>(triples);
    }

    @Override
    public List<String> retrieveByKeywords(String knowledgeBaseId, List<String> keywords,
                                           double scoreThreshold, int searchLimit,
                                           int hopDepth, int maxTriples) {
        // 确保全文索引存在
        graphStore.ensureFulltextIndex(knowledgeBaseId);

        // 全文搜索找到锚点实体
        List<String> anchorEntities = searchByFulltext(knowledgeBaseId, keywords, scoreThreshold, searchLimit);
        if (anchorEntities.isEmpty()) {
            log.info("全文搜索未找到匹配实体: knowledgeBaseId={}, keywords={}", knowledgeBaseId, keywords);
            return Collections.emptyList();
        }

        // 扩展子图获取上下文
        return expandSubgraph(knowledgeBaseId, anchorEntities, hopDepth, maxTriples);
    }

    @Override
    public String buildQueryPrompt(String knowledgeBaseId) {
        String kbLabel = graphStore.getKnowledgeBaseLabel(knowledgeBaseId);
        String kbSchema = graphStore.getSchema(knowledgeBaseId);

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


    private String buildSubgraphCypher(String kbLabel, String idProp, int hopDepth) {
        if (hopDepth <= 1) {
            return """
                MATCH (anchor:`%s`)-[r]-(neighbor:`%s`)
                WHERE anchor.%s IN $entities AND NOT neighbor:Document
                RETURN anchor.%s AS head, type(r) AS relation, neighbor.%s AS tail
                LIMIT $limit
                """.formatted(kbLabel, kbLabel, idProp, idProp, idProp);
        }
        return """
            MATCH path = (anchor:`%s`)-[*1..2]-(neighbor:`%s`)
            WHERE anchor.%s IN $entities AND NOT neighbor:Document
            UNWIND relationships(path) AS r
            WITH startNode(r) AS h, r, endNode(r) AS t
            WHERE NOT h:Document AND NOT t:Document
            RETURN DISTINCT h.%s AS head, type(r) AS relation, t.%s AS tail
            LIMIT $limit
            """.formatted(kbLabel, kbLabel, idProp, idProp, idProp);
    }

    private String safeGetString(Record record, String key) {
        var value = record.get(key);
        return (value == null || value.isNull()) ? null : value.asString();
    }
}
