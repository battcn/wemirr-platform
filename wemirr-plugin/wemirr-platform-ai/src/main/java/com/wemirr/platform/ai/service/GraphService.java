package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.domain.dto.rep.EntityRecallResp;
import com.wemirr.platform.ai.domain.dto.rep.GraphVisualizationResp;

import java.util.List;
import java.util.Map;

/**
 * 图谱化服务接口
 * 提供知识条目的图谱化处理功能
 *
 * @author xJh
 * @since 2025/12/18
 */
public interface GraphService {

    /**
     * 对知识条目进行图谱化
     *
     * @param itemId 知识条目ID
     * @return 图谱化结果（节点数、关系数）
     */
    GraphResult graphizeKnowledgeItem(Long itemId);

    /**
     * 批量对知识条目进行图谱化
     *
     * @param itemIds 知识条目ID列表
     * @return 批量处理结果
     */
    List<GraphResult> graphizeKnowledgeItems(List<Long> itemIds);

    /**
     * 删除知识条目的图谱数据
     *
     * @param itemId 知识条目ID
     * @return 是否成功
     */
    boolean deleteGraphForItem(Long itemId);

    /**
     * 删除知识库的所有图谱数据
     *
     * @param kbId 知识库ID
     * @return 是否成功
     */
    boolean deleteGraphForKnowledgeBase(Long kbId);

    /**
     * 获取知识库的图谱统计信息
     *
     * @param kbId 知识库ID
     * @return 统计信息
     */
    Map<String, Object> getGraphStatistics(Long kbId);

    /**
     * 获取知识库的图谱 Schema
     *
     * @param kbId 知识库ID
     * @return Schema 描述
     */
    String getGraphSchema(Long kbId);

    /**
     * 检查图谱服务是否可用
     *
     * @return 是否可用
     */
    boolean isGraphServiceAvailable();

    /**
     * 获取图谱可视化数据
     *
     * @param kbId  知识库ID
     * @param limit 最大节点数量
     * @return 可视化数据
     */
    GraphVisualizationResp getVisualizationData(Long kbId, int limit);

    /**
     * 实体召回测试
     *
     * @param kbId     知识库ID
     * @param keywords 关键词列表
     * @return 召回结果
     */
    EntityRecallResp testEntityRecall(Long kbId, List<String> keywords);

    /**
     * 图谱化处理结果
     */
    record GraphResult(Long itemId, int nodesCreated, int relationshipsCreated, boolean success, String message) {
        public static GraphResult success(Long itemId, int nodes, int relationships) {
            return new GraphResult(itemId, nodes, relationships, true, null);
        }

        public static GraphResult fail(Long itemId, String message) {
            return new GraphResult(itemId, 0, 0, false, message);
        }
    }
}
