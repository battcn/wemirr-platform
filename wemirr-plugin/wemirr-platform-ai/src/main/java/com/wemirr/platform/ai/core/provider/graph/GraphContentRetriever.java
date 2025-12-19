package com.wemirr.platform.ai.core.provider.graph;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * 图谱内容检索器
 * <p>
 * 实现 Langchain4j 的 ContentRetriever 接口，将 GraphRAG 检索能力集成到标准 RAG 管道中。
 * <p>
 * 检索流程（混合检索）：
 * <ol>
 *   <li>路数A：将用户问题向量化 -> 向量索引匹配</li>
 *   <li>路数B：LLM 提取实体 -> 精确匹配</li>
 *   <li>融合两路结果 -> 子图扩展获取三元组上下文</li>
 * </ol>
 *
 * @author xJh
 * @since 2025/12/17
 */
@Slf4j
@Builder
public class GraphContentRetriever implements ContentRetriever {

    /**
     * 知识库ID，用于隔离不同知识库的数据
     */
    private final String knowledgeBaseId;

    /**
     * GraphRAG 服务
     */
    private final GraphRagService graphRagService;

    /**
     * 用于实体提取和生成回答的 ChatModel
     */
    private final ChatModel chatModel;

    /**
     * 是否使用混合检索（推荐开启）
     */
    @Builder.Default
    private final boolean useHybridSearch = true;

    /**
     * 最大返回结果数
     */
    @Builder.Default
    private final int maxResults = 10;

    /**
     * 是否在无结果时静默返回空列表
     */
    @Builder.Default
    private final boolean silentOnEmpty = true;

    @Override
    public List<Content> retrieve(Query query) {
        if (graphRagService == null) {
            log.warn("GraphContentRetriever 未正确配置，返回空结果");
            return Collections.emptyList();
        }

        String question = query.text();
        log.debug("图谱检索开始: question='{}', knowledgeBaseId='{}', useHybrid={}", 
                question, knowledgeBaseId, useHybridSearch);

        try {
            List<Content> results;
            
            // 根据配置选择检索方式
            if (useHybridSearch && chatModel != null) {
                // 混合检索（推荐）
                results = graphRagService.retrieveAsContentHybrid(knowledgeBaseId, question, chatModel);
            } else {
                // 纯向量检索
                results = graphRagService.retrieveAsContentByVector(knowledgeBaseId, question);
            }

            // 限制返回结果数量
            if (results.size() > maxResults) {
                results = results.subList(0, maxResults);
            }

            log.debug("图谱检索完成: 返回 {} 条结果", results.size());
            return results;

        } catch (Exception e) {
            log.error("图谱检索失败: question='{}', knowledgeBaseId='{}'", question, knowledgeBaseId, e);
            if (silentOnEmpty) {
                return Collections.emptyList();
            }
            throw e;
        }
    }

    /**
     * 获取检索器描述（用于 QueryRouter）
     */
    public String getDescription() {
        return "Knowledge Graph retriever for knowledge base: " + knowledgeBaseId;
    }
}
