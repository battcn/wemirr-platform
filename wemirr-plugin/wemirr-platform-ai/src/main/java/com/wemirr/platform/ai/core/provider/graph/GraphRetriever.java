package com.wemirr.platform.ai.core.provider.graph;

import dev.langchain4j.rag.content.Content;

import java.util.List;

/**
 * 图检索抽象接口
 * <p>
 * 定义图数据库的通用检索操作，支持多种检索策略：
 * - 全文索引搜索
 * - 子图扩展
 * - 关键词检索
 * - Cypher/SPARQL 查询（由具体实现扩展）
 *
 * @author xJh
 * @since 2025/12/18
 */
public interface GraphRetriever {

    // ==================== 全文搜索 ====================

    /**
     * 基于全文索引搜索实体节点
     *
     * @param knowledgeBaseId 知识库ID
     * @param keywords        关键词列表
     * @param scoreThreshold  相关度阈值 (0-1)
     * @param limit           每个关键词返回的最大结果数
     * @return 匹配的实体节点ID列表
     */
    List<String> searchByFulltext(String knowledgeBaseId, List<String> keywords,
                                  double scoreThreshold, int limit);

    // ==================== 子图扩展 ====================

    /**
     * 基于锚点实体扩展子图，获取三元组上下文
     *
     * @param knowledgeBaseId 知识库ID
     * @param anchorEntities  锚点实体ID列表
     * @param hopDepth        扩展深度 (1-2 hop)
     * @param maxTriples      最大返回三元组数
     * @return 三元组文本列表，格式: (head) --[relation]--> (tail)
     */
    List<String> expandSubgraph(String knowledgeBaseId, List<String> anchorEntities,
                                int hopDepth, int maxTriples);

    // ==================== 关键词检索 ====================

    /**
     * 一站式关键词检索：全文搜索 + 子图扩展（使用默认参数）
     *
     * @param knowledgeBaseId 知识库ID
     * @param keywords        关键词列表
     * @return 三元组上下文列表
     */
    default List<String> retrieveByKeywords(String knowledgeBaseId, List<String> keywords) {
        return retrieveByKeywords(knowledgeBaseId, keywords, 0.3, 5, 1, 30);
    }

    /**
     * 关键词检索
     *
     * @param knowledgeBaseId 知识库ID
     * @param keywords        关键词列表
     * @param scoreThreshold  全文搜索相关度阈值
     * @param searchLimit     每个关键词的搜索结果数
     * @param hopDepth        子图扩展深度
     * @param maxTriples      最大三元组数
     * @return 三元组上下文列表
     */
    List<String> retrieveByKeywords(String knowledgeBaseId, List<String> keywords,
                                    double scoreThreshold, int searchLimit,
                                    int hopDepth, int maxTriples);

    // ==================== Content 转换 ====================

    /**
     * 将三元组列表转换为 Content 对象
     *
     * @param triples 三元组列表
     * @return Content 列表
     */
    default List<Content> toContents(List<String> triples) {
        if (triples == null || triples.isEmpty()) {
            return List.of();
        }
        String context = "知识图谱检索结果：\n" + String.join("\n", triples);
        return List.of(Content.from(context));
    }

    // ==================== Prompt 构建 ====================

    /**
     * 构建知识库查询 Prompt（用于 Text2Query 场景）
     *
     * @param knowledgeBaseId 知识库ID
     * @return Prompt 模板
     */
    String buildQueryPrompt(String knowledgeBaseId);
}
