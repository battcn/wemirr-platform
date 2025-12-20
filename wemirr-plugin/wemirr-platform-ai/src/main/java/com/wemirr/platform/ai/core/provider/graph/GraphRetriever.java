package com.wemirr.platform.ai.core.provider.graph;

import dev.langchain4j.rag.content.Content;

import java.util.List;

/**
 * 图检索抽象接口
 * <p>
 * 定义图数据库的通用检索操作，支持多种检索策略：
 * - 向量语义搜索（语义路）
 * - 实体精确匹配（精确路）
 * - 混合检索（Hybrid Search）
 * - 子图扩展
 *
 * @author xJh
 * @since 2025/12/18
 */
public interface GraphRetriever {

    // ==================== 向量语义搜索（语义路） ====================

    /**
     * 基于向量相似度搜索实体节点
     * <p>
     * 将用户问题转化为向量，在图数据库中进行语义相似度搜索
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题（将被向量化）
     * @param scoreThreshold  相似度阈值 (0-1)
     * @param limit           返回的最大结果数
     * @return 匹配的实体节点ID列表
     */
    List<String> searchByVector(String knowledgeBaseId, String question,
                                double scoreThreshold, int limit);

    // ==================== 实体精确匹配（精确路） ====================

    /**
     * 基于实体名称精确匹配搜索节点
     * <p>
     * 使用 CONTAINS 或精确匹配在图数据库中查找实体节点，
     * 适用于人名、专有名词、ID等需要精确匹配的场景
     *
     * @param knowledgeBaseId 知识库ID
     * @param entities        实体名称列表（由 LLM 提取）
     * @param limit           每个实体返回的最大结果数
     * @return 匹配的实体节点ID列表
     */
    List<String> searchByEntityMatch(String knowledgeBaseId, List<String> entities, int limit);

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

    // ==================== 向量语义检索（推荐） ====================

    /**
     * 一站式向量语义检索：向量搜索 + 子图扩展（使用默认参数）
     * <p>
     * 推荐使用此方法替代关键词检索，能更好地处理语义查询
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @return 三元组上下文列表
     */
    default List<String> retrieveByVector(String knowledgeBaseId, String question) {
        return retrieveByVector(knowledgeBaseId, question, 0.7, 5, 1, 30);
    }

    /**
     * 向量语义检索：向量搜索 + 子图扩展
     *
     * @param knowledgeBaseId 知识库ID
     * @param question        用户问题
     * @param scoreThreshold  向量相似度阈值
     * @param searchLimit     搜索结果数
     * @param hopDepth        子图扩展深度
     * @param maxTriples      最大三元组数
     * @return 三元组上下文列表
     */
    List<String> retrieveByVector(String knowledgeBaseId, String question,
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
