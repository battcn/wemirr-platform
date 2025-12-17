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
 * 支持通过 Text2Cypher 方式从知识图谱中检索相关内容。
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
     * 用于生成 Cypher 查询的 ChatModel
     */
    private final ChatModel chatModel;

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
        if (graphRagService == null || chatModel == null) {
            log.warn("GraphContentRetriever not properly configured, returning empty results");
            return Collections.emptyList();
        }

        String question = query.text();
        log.debug("GraphContentRetriever retrieving for question: '{}' in kb: '{}'", question, knowledgeBaseId);

        try {
            List<Content> results = graphRagService.retrieve(knowledgeBaseId, question, chatModel, !silentOnEmpty);

            // 限制返回结果数量
            if (results.size() > maxResults) {
                results = results.subList(0, maxResults);
            }

            log.debug("GraphContentRetriever retrieved {} results for question: '{}'", results.size(), question);
            return results;

        } catch (GraphRagService.EmptyResultException e) {
            log.info("No graph results found for question: '{}' in kb: '{}'", question, knowledgeBaseId);
            return Collections.emptyList();

        } catch (Exception e) {
            log.error("Error retrieving from graph for question: '{}' in kb: '{}'", question, knowledgeBaseId, e);
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
        return "Knowledge Graph retriever for structured entity and relationship queries in knowledge base: " + knowledgeBaseId;
    }
}
