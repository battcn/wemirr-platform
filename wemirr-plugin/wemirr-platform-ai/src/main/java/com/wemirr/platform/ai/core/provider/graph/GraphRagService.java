package com.wemirr.platform.ai.core.provider.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.community.data.document.graph.GraphDocument;
import dev.langchain4j.community.data.document.transformer.graph.LLMGraphTransformer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.rag.content.Content;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                // 2. 存储到图数据库
                graphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);

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
        graphStore.addGraphDocuments(knowledgeBaseId, graphDocuments, includeSource);
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

    // ==================== 关键词检索 ====================

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Pattern JSON_ARRAY_PATTERN = Pattern.compile("\\[.*?]", Pattern.DOTALL);

    private static final String KEYWORD_EXTRACTION_PROMPT = """
        你是一个知识图谱查询助手。请从用户的问题中提取核心实体名称（人名、组织、地点、专有名词、概念）。
        
        规则：
        1. 忽略通用词汇（如"是什么"、"在哪里"、"怎么样"等）
        2. 提取最重要的3-5个实体
        3. 保留实体的原始形式（中文/英文）
        
        问题: %s
        
        仅返回一个 JSON 字符串列表，不要包含 Markdown 格式或任何解释。
        示例：["实体1", "实体2"]
        """;

    /**
     * 基于关键词的图谱检索
     * <p>
     * 流程：LLM 提取关键词 -> 全文索引搜索 -> 子图扩展
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       用于提取关键词的 ChatModel
     * @return 三元组上下文列表
     */
    public List<String> retrieveByKeywords(String knowledgeBaseId, String question, ChatModel chatModel) {
        return retrieveByKeywords(knowledgeBaseId, question, chatModel, 0.3, 5, 1, 30);
    }

    /**
     * 基于关键词的图谱检索
     */
    public List<String> retrieveByKeywords(String knowledgeBaseId, String question, ChatModel chatModel,
                                           double scoreThreshold, int searchLimit,
                                           int hopDepth, int maxTriples) {
        List<String> keywords = extractKeywords(question, chatModel);
        if (keywords.isEmpty()) {
            return Collections.emptyList();
        }
        return graphRetriever.retrieveByKeywords(knowledgeBaseId, keywords,
                scoreThreshold, searchLimit, hopDepth, maxTriples);
    }

    /**
     * 基于关键词检索并返回 Content 对象
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       ChatModel
     * @return Content 列表
     */
    public List<Content> retrieveAsContent(String knowledgeBaseId, String question, ChatModel chatModel) {
        List<String> triples = retrieveByKeywords(knowledgeBaseId, question, chatModel);
        return graphRetriever.toContents(triples);
    }

    /**
     * 基于关键词检索并生成 LLM 回答
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param chatModel       ChatModel
     * @return LLM 生成的回答
     */
    public String retrieveWithAnswer(String knowledgeBaseId, String question, ChatModel chatModel) {
        List<String> triples = retrieveByKeywords(knowledgeBaseId, question, chatModel);
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


    private List<String> extractKeywords(String question, ChatModel chatModel) {
        List<String> keywords = extractKeywordsUsingLLM(question, chatModel);
        if (keywords.isEmpty()) {
            log.info("关键词提取结果为空: question={}", question);
        } else {
            log.info("提取到关键词: keywords={}", keywords);
        }
        return keywords;
    }

    private List<String> extractKeywordsUsingLLM(String question, ChatModel chatModel) {
        try {
            String prompt = String.format(KEYWORD_EXTRACTION_PROMPT, question);
            ChatResponse response = chatModel.chat(UserMessage.from(prompt));
            String jsonResponse = response.aiMessage().text().trim();
            log.debug("LLM 关键词提取原始响应: {}", jsonResponse);
            return parseJsonArray(jsonResponse);
        } catch (Exception e) {
            log.error("关键词提取失败: question={}", question, e);
            return Collections.emptyList();
        }
    }

    private List<String> parseJsonArray(String jsonResponse) {
        try {
            return OBJECT_MAPPER.readValue(jsonResponse, new TypeReference<List<String>>() {});
        } catch (Exception e) {
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
}
