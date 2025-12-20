package com.wemirr.platform.ai.core.provider.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.graph.neo4j.Neo4jGraphStore;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.ModelConfigService;
import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.data.document.transformer.graph.LLMGraphTransformer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * GraphRAG 服务层
 * <p>
 * 整合文档处理、图谱存储、检索的完整链路：
 * <ul>
 *   <li>文档 → 图谱提取（使用 Langchain4j LLMGraphTransformer）</li>
 *   <li>图谱存储（通过 GraphStore 接口）</li>
 *   <li>图谱检索（通过 GraphRetriever 接口）</li>
 *   <li>知识库隔离（按 knowledgeBaseId 分隔数据）</li>
 * </ul>
 * <p>
 * 本服务依赖抽象接口，支持不同图数据库实现（Neo4j、JanusGraph 等）
 *
 * @author xJh
 * @since 2025/12/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rag", name = "graph-enabled", havingValue = "true")
public class GraphRagService {

    private final GraphStore graphStore;
    private final GraphRetriever graphRetriever;
    private final KnowledgeBaseService knowledgeBaseService;
    private final ModelConfigService modelConfigService;
    private final EmbeddingModelService embeddingModelService;


    /**
     * 处理文档：提取图谱并存储到指定知识库（不生成向量嵌入）
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
        return processDocumentsWithEmbedding(knowledgeBaseId, documents, graphTransformer, includeSource, false);
    }

    /**
     * 处理文档：提取图谱并存储到指定知识库（自动获取向量模型生成嵌入）
     * <p>
     * 通过知识库配置自动获取向量模型，为节点生成向量嵌入
     *
     * @param knowledgeBaseId  知识库ID
     * @param documents        文档列表
     * @param graphTransformer 图谱提取器
     * @param includeSource    是否存储源文档
     * @param withEmbedding    是否生成向量嵌入
     * @return 处理结果统计
     */
    public ProcessResult processDocumentsWithEmbedding(String knowledgeBaseId,
                                                       List<Document> documents,
                                                       LLMGraphTransformer graphTransformer,
                                                       boolean includeSource,
                                                       boolean withEmbedding) {
        int totalNodes = 0;
        int totalRelationships = 0;

        // 获取向量模型（如果需要）
        EmbeddingModel embeddingModel = null;
        if (withEmbedding) {
            embeddingModel = getEmbeddingModelByKbId(knowledgeBaseId);
            if (embeddingModel == null) {
                log.warn("知识库 {} 未配置向量模型，将不生成向量嵌入", knowledgeBaseId);
            }
        }

        for (Document document : documents) {
            try {
                // 1. 提取图谱 TODO 添加实体描述description
                List<GraphDocument> graphDocuments = graphTransformer.transformAll(List.of(document));

                // 2. 存储到图数据库（带向量模型）
                if (graphStore instanceof Neo4jGraphStore neo4jStore) {
                    neo4jStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource, embeddingModel);
                } else {
                    graphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);
                }

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

        log.info("Documents processed: knowledgeBaseId={}, documents={}, nodes={}, relationships={}, withEmbedding={}",
                knowledgeBaseId, documents.size(), totalNodes, totalRelationships, embeddingModel != null);

        return new ProcessResult(documents.size(), totalNodes, totalRelationships);
    }

    /**
     * 直接存储已提取的图谱文档（不生成向量嵌入）
     */
    public void storeGraphDocuments(String knowledgeBaseId,
                                    List<GraphDocument> graphDocuments,
                                    boolean includeSource) {
        graphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);
    }

    /**
     * 直接存储已提取的图谱文档（自动获取向量模型生成嵌入）
     */
    public void storeGraphDocumentsWithEmbedding(String knowledgeBaseId,
                                                 List<GraphDocument> graphDocuments,
                                                 boolean includeSource) {
        EmbeddingModel embeddingModel = getEmbeddingModelByKbId(knowledgeBaseId);
        if (graphStore instanceof Neo4jGraphStore neo4jStore) {
            neo4jStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource, embeddingModel);
        } else {
            graphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);
        }
    }



    /**
     * 删除指定知识库的所有数据
     *
     * @param knowledgeBaseId 知识库ID
     */
    public void deleteKnowledgeBase(String knowledgeBaseId) {
        graphStore.deleteKnowledgeBase(knowledgeBaseId);
        log.info("知识库已删除: knowledgeBaseId={}", knowledgeBaseId);
    }

    /**
     * 删除指定知识库中的文档
     *
     * @param knowledgeBaseId 知识库ID
     * @param documentId      文档ID
     */
    public void deleteDocument(String knowledgeBaseId, String documentId) {
        graphStore.deleteDocument(knowledgeBaseId, documentId);
    }

    /**
     * 获取知识库统计信息
     *
     * @param knowledgeBaseId 知识库ID
     * @return 统计信息
     */
    public Map<String, Object> getStatistics(String knowledgeBaseId) {
        return graphStore.getStatistics(knowledgeBaseId);
    }

    /**
     * 获取知识库 Schema
     *
     * @param knowledgeBaseId 知识库ID
     * @return Schema 描述
     */
    public String getSchema(String knowledgeBaseId) {
        return graphStore.getSchema(knowledgeBaseId);
    }

    /**
     * 处理结果记录
     */
    public record ProcessResult(int documentsProcessed, int nodesCreated, int relationshipsCreated) {
    }

    // ==================== 向量语义检索 ====================

    /**
     * 基于向量的图谱语义检索
     * <p>
     * 流程：用户问题 -> 向量化 -> 向量相似度搜索 -> 子图扩展
     * 相比关键词检索，能更好地处理语义查询（如"做Windows的公司"能匹配到"Microsoft"）
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @return 三元组上下文列表
     */
    public List<String> retrieveByVector(String knowledgeBaseId, String question) {
        return graphRetriever.retrieveByVector(knowledgeBaseId, question);
    }

    /**
     * 基于向量的图谱语义检索（可配置参数）
     */
    public List<String> retrieveByVector(String knowledgeBaseId, String question,
                                         double scoreThreshold, int searchLimit,
                                         int hopDepth, int maxTriples) {
        return graphRetriever.retrieveByVector(knowledgeBaseId, question,
                scoreThreshold, searchLimit, hopDepth, maxTriples);
    }

    /**
     * 基于向量检索并返回 Content 对象（推荐）
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @return Content 列表
     */
    public List<Content> retrieveAsContentByVector(String knowledgeBaseId, String question) {
        List<String> triples = retrieveByVector(knowledgeBaseId, question);
        return graphRetriever.toContents(triples);
    }

    /**
     * 基于向量检索并生成 LLM 回答
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       用于生成回答的 ChatModel
     * @return LLM 生成的回答
     */
    public String retrieveWithAnswerByVector(String knowledgeBaseId, String question, ChatModel chatModel) {
        List<String> triples = retrieveByVector(knowledgeBaseId, question);
        return generateAnswer(triples, question, chatModel);
    }

    // ==================== 混合检索（Hybrid Search，推荐） ====================

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Pattern JSON_ARRAY_PATTERN = Pattern.compile("\\[.*?]", Pattern.DOTALL);
    private static final ExecutorService HYBRID_EXECUTOR = Executors.newFixedThreadPool(2);

    private static final String ENTITY_EXTRACTION_PROMPT = """
        你是一个知识图谱查询助手。请从用户的问题中提取核心实体名称（人名、组织名、地名、专有名词、产品名、概念等）。
        
        规则：
        1. 只提取具体的实体名称，忽略通用词汇（如"是什么"、"有哪些"、"怎么样"等）
        2. 保留实体的原始形式（中文/英文）
        3. 提取最重要的 3-5 个实体
        4. 对于人名，提取完整姓名
        
        问题: %s
        
        仅返回一个 JSON 字符串列表，不要包含 Markdown 格式或任何解释。
        示例：["李雷", "韩梅梅", "Microsoft"]
        """;

    /**
     * 混合检索（Hybrid Search）- 推荐使用
     * <p>
     * 并行执行两路检索，融合结果：
     * <ul>
     *   <li>路数 A（语义路）：用户问题 -> Embedding -> 向量索引 -> Top K 节点</li>
     *   <li>路数 B（精确路）：用户问题 -> LLM 提取实体 -> 精确匹配 -> Top K 节点</li>
     * </ul>
     * 取并集作为锚点实体，再进行子图扩展
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       用于实体提取的 ChatModel
     * @return 三元组上下文列表
     */
    public List<String> retrieveHybrid(String knowledgeBaseId, String question, ChatModel chatModel) {
        return retrieveHybrid(knowledgeBaseId, question, chatModel, 0.6, 5, 5, 1, 30);
    }

    /**
     * 混合检索（可配置参数）
     *
     * @param knowledgeBaseId     知识库ID
     * @param question            用户问题
     * @param chatModel           用于实体提取的 ChatModel
     * @param vectorScoreThreshold 向量相似度阈值
     * @param vectorLimit         向量检索结果数
     * @param entityMatchLimit    实体精确匹配结果数
     * @param hopDepth            子图扩展深度
     * @param maxTriples          最大三元组数
     * @return 三元组上下文列表
     */
    public List<String> retrieveHybrid(String knowledgeBaseId, String question, ChatModel chatModel,
                                       double vectorScoreThreshold, int vectorLimit, int entityMatchLimit,
                                       int hopDepth, int maxTriples) {
        // 确保向量索引存在
        graphStore.ensureVectorIndex(knowledgeBaseId);

        // 并行执行两路检索
        CompletableFuture<List<String>> vectorFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return graphRetriever.searchByVector(knowledgeBaseId, question, vectorScoreThreshold, vectorLimit);
            } catch (Exception e) {
                log.warn("向量检索异常: {}", e.getMessage());
                return Collections.<String>emptyList();
            }
        }, HYBRID_EXECUTOR);

        CompletableFuture<List<String>> entityFuture = CompletableFuture.supplyAsync(() -> {
            try {
                List<String> entities = extractEntities(question, chatModel);
                if (entities.isEmpty()) {
                    return Collections.<String>emptyList();
                }
                return graphRetriever.searchByEntityMatch(knowledgeBaseId, entities, entityMatchLimit);
            } catch (Exception e) {
                log.warn("实体精确匹配异常: {}", e.getMessage());
                return Collections.<String>emptyList();
            }
        }, HYBRID_EXECUTOR);

        // 等待两路结果并融合
        try {
            List<String> vectorResults = vectorFuture.get();
            List<String> entityResults = entityFuture.get();

            // 融合结果（去重，保持顺序）
            Set<String> mergedAnchors = new LinkedHashSet<>();
            mergedAnchors.addAll(vectorResults);
            mergedAnchors.addAll(entityResults);

            log.info("混合检索锚点融合: vectorCount={}, entityCount={}, mergedCount={}",
                    vectorResults.size(), entityResults.size(), mergedAnchors.size());

            if (mergedAnchors.isEmpty()) {
                log.info("混合检索未找到任何锚点实体: question={}",
                        question.substring(0, Math.min(50, question.length())));
                return Collections.emptyList();
            }

            // 子图扩展
            return graphRetriever.expandSubgraph(knowledgeBaseId, new ArrayList<>(mergedAnchors), hopDepth, maxTriples);

        } catch (Exception e) {
            log.error("混合检索执行失败: knowledgeBaseId={}", knowledgeBaseId, e);
            return Collections.emptyList();
        }
    }

    /**
     * 混合检索并返回 Content 对象（推荐）
     */
    public List<Content> retrieveAsContentHybrid(String knowledgeBaseId, String question, ChatModel chatModel) {
        List<String> triples = retrieveHybrid(knowledgeBaseId, question, chatModel);
        return graphRetriever.toContents(triples);
    }

    /**
     * 混合检索并生成 LLM 回答（推荐）
     */
    public String retrieveWithAnswerHybrid(String knowledgeBaseId, String question, ChatModel chatModel) {
        List<String> triples = retrieveHybrid(knowledgeBaseId, question, chatModel);
        return generateAnswer(triples, question, chatModel);
    }

    // ==================== 内部方法 ====================

    /**
     * 使用 LLM 从问题中提取实体
     */
    private List<String> extractEntities(String question, ChatModel chatModel) {
        try {
            String prompt = String.format(ENTITY_EXTRACTION_PROMPT, question);
            ChatResponse response = chatModel.chat(UserMessage.from(prompt));
            String jsonResponse = response.aiMessage().text().trim();
            log.debug("LLM 实体提取原始响应: {}", jsonResponse);

            List<String> entities = parseJsonArray(jsonResponse);
            if (!entities.isEmpty()) {
                log.info("提取到实体: entities={}", entities);
            }
            return entities;
        } catch (Exception e) {
            log.error("实体提取失败: question={}", question, e);
            return Collections.emptyList();
        }
    }

    /**
     * 解析 JSON 数组
     */
    private List<String> parseJsonArray(String jsonResponse) {
        try {
            return OBJECT_MAPPER.readValue(jsonResponse, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            // 尝试从响应中提取 JSON 数组
            Matcher matcher = JSON_ARRAY_PATTERN.matcher(jsonResponse);
            if (matcher.find()) {
                try {
                    return OBJECT_MAPPER.readValue(matcher.group(), new TypeReference<List<String>>() {});
                } catch (Exception ex) {
                    log.warn("解析提取的 JSON 数组失败: {}", matcher.group());
                }
            }
            log.warn("JSON 解析失败，原始响应: {}", jsonResponse);
            return Collections.emptyList();
        }
    }

    /**
     * 生成基于三元组的 LLM 回答
     */
    private String generateAnswer(List<String> triples, String question, ChatModel chatModel) {
        if (triples.isEmpty()) {
            return "抱歉，在当前知识库中未找到相关信息。";
        }

        String contextPrompt = """
            基于以下知识图谱信息回答用户问题。
            
            知识图谱信息：
            %s
            
            用户问题：%s
            
            请基于以上信息给出简洁、准确的回答。如果信息不足以回答，请如实说明。
            """;

        String prompt = String.format(contextPrompt, String.join("\n", triples), question);
        ChatResponse response = chatModel.chat(UserMessage.from(prompt));
        return response.aiMessage().text();
    }

    /**
     * 获取查询 Prompt（用于 Text2Query 场景）
     * 目前放弃了使用Langchain4j提供的Text2Query，生成的查询语句效果不佳
     */
    public String getQueryPrompt(String knowledgeBaseId) {
        return graphRetriever.buildQueryPrompt(knowledgeBaseId);
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
