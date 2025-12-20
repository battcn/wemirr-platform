package com.wemirr.platform.ai.core.provider.graph.neo4j;

import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.graph.GraphRetriever;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.ModelConfigService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
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
 * - 向量语义搜索（语义路）
 * - 实体精确匹配（精确路）
 * - 子图扩展（1-2 hop）
 * - 混合检索（Hybrid Search）
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
    private final KnowledgeBaseService knowledgeBaseService;
    private final ModelConfigService modelConfigService;
    private final EmbeddingModelService embeddingModelService;

    // ==================== 向量语义搜索实现 ====================

    @Override
    public List<String> searchByVector(String knowledgeBaseId, String question,
                                       double scoreThreshold, int limit) {
        if (question == null || question.isBlank()) {
            return Collections.emptyList();
        }

        // 通过知识库配置获取向量模型
        EmbeddingModel embeddingModel = getEmbeddingModelByKbId(knowledgeBaseId);
        if (embeddingModel == null) {
            log.warn("知识库 {} 未配置向量模型，无法进行向量检索", knowledgeBaseId);
            return Collections.emptyList();
        }

        String indexName = "vector_index_" + knowledgeBaseId;
        Set<String> results = new LinkedHashSet<>();

        try {
            // 1. 将用户问题转化为向量
            Response<Embedding> embeddingResponse = embeddingModel.embed(question);
            List<Float> queryVector = embeddingResponse.content().vectorAsList();

            // 2. 使用向量索引查询最近邻节点
            try (Session session = graphStore.getDriver().session()) {
                String cypher = """
                    CALL db.index.vector.queryNodes($indexName, $limit, $queryVector)
                    YIELD node, score
                    WHERE score > $threshold
                    RETURN node.id AS entityId, node.description AS description, score
                    ORDER BY score DESC
                    """;

                List<Record> records = session.run(cypher, Values.parameters(
                        "indexName", indexName,
                        "limit", limit,
                        "queryVector", queryVector,
                        "threshold", scoreThreshold
                )).list();

                for (Record record : records) {
                    String entityId = safeGetString(record, "entityId");
                    if (entityId != null) {
                        results.add(entityId);
                        log.debug("向量检索匹配: entity={}, score={}, desc={}",
                                entityId, record.get("score").asDouble(),
                                safeGetString(record, "description"));
                    }
                }

                log.info("向量检索完成: question={}, resultsCount={}", 
                        question.substring(0, Math.min(50, question.length())), results.size());
            }
        } catch (Exception e) {
            log.error("向量检索失败: knowledgeBaseId={}, question={}", knowledgeBaseId, question, e);
        }

        return new ArrayList<>(results);
    }

    // ==================== 实体精确匹配实现 ====================

    @Override
    public List<String> searchByEntityMatch(String knowledgeBaseId, List<String> entities, int limit) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        String kbLabel = graphStore.getKnowledgeBaseLabel(knowledgeBaseId);
        Set<String> results = new LinkedHashSet<>();

        try (Session session = graphStore.getDriver().session()) {
            for (String entity : entities) {
                if (entity == null || entity.isBlank()) {
                    continue;
                }

                String trimmed = entity.trim();

                // 优先精确匹配，再模糊匹配
                String cypher = """
                    MATCH (n:`%s`)
                    WHERE (n.id = $entity OR toLower(n.id) CONTAINS toLower($entity)) 
                      AND NOT n:Document
                    RETURN n.id AS entityId
                    LIMIT $limit
                    """.formatted(kbLabel);

                List<Record> records = session.run(cypher, Values.parameters(
                        "entity", trimmed,
                        "limit", limit
                )).list();

                for (Record record : records) {
                    String entityId = safeGetString(record, "entityId");
                    if (entityId != null) {
                        results.add(entityId);
                        log.debug("实体精确匹配: searchEntity={}, matchedEntity={}", entity, entityId);
                    }
                }
            }

            log.info("实体精确匹配完成: entities={}, resultsCount={}", entities, results.size());
        } catch (Exception e) {
            log.error("实体精确匹配失败: knowledgeBaseId={}, entities={}", knowledgeBaseId, entities, e);
        }

        return new ArrayList<>(results);
    }

    // ==================== 向量语义检索 ====================

    @Override
    public List<String> retrieveByVector(String knowledgeBaseId, String question,
                                         double scoreThreshold, int searchLimit,
                                         int hopDepth, int maxTriples) {
        // 确保向量索引存在
        graphStore.ensureVectorIndex(knowledgeBaseId);

        // 向量搜索找到锚点实体
        List<String> anchorEntities = searchByVector(knowledgeBaseId, question, scoreThreshold, searchLimit);
        if (anchorEntities.isEmpty()) {
            log.info("向量检索未找到匹配实体: knowledgeBaseId={}, question={}", 
                    knowledgeBaseId, question.substring(0, Math.min(50, question.length())));
            return Collections.emptyList();
        }

        // 扩展子图获取上下文
        return expandSubgraph(knowledgeBaseId, anchorEntities, hopDepth, maxTriples);
    }

    // ==================== 子图扩展实现 ====================

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

    /**
     * 通过知识库ID获取向量模型
     * <p>
     * 流程：knowledgeBaseId -> KnowledgeBase -> embeddingModelId -> ModelConfig -> EmbeddingModel
     *
     * @param knowledgeBaseId 知识库ID（字符串形式）
     * @return EmbeddingModel 实例，如果未配置则返回 null
     */
    private EmbeddingModel getEmbeddingModelByKbId(String knowledgeBaseId) {
        try {
            Long kbId = Long.parseLong(knowledgeBaseId);
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
            if (knowledgeBase == null) {
                log.warn("知识库不存在: {}", knowledgeBaseId);
                return null;
            }
            
            Long embeddingModelId = knowledgeBase.getEmbeddingModelId();
            if (embeddingModelId == null) {
                log.warn("知识库 {} 未配置向量模型ID", knowledgeBaseId);
                return null;
            }
            
            ModelConfig modelConfig = modelConfigService.getById(embeddingModelId);
            if (modelConfig == null) {
                log.warn("向量模型配置不存在: modelId={}", embeddingModelId);
                return null;
            }
            
            return embeddingModelService.getModel(modelConfig);
        } catch (NumberFormatException e) {
            log.error("知识库ID格式错误: {}", knowledgeBaseId);
            return null;
        } catch (Exception e) {
            log.error("获取向量模型失败: knowledgeBaseId={}", knowledgeBaseId, e);
            return null;
        }
    }
}
