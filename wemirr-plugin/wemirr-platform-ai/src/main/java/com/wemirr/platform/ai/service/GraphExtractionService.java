package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeItem;

/**
 * 图谱提取服务接口
 * <p>
 * 负责从知识条目中提取实体关系并存储到知识图谱
 *
 * @author xJh
 * @since 2025/12/28
 */
public interface GraphExtractionService {

    /**
     * 处理图谱提取
     *
     * @param item 知识条目
     * @param kb   知识库
     * @return 提取结果
     */
    GraphExtractionResult extractAndStore(KnowledgeItem item, KnowledgeBase kb);

    /**
     * 异步处理图谱提取
     *
     * @param item 知识条目
     * @param kb   知识库
     */
    void extractAndStoreAsync(KnowledgeItem item, KnowledgeBase kb);

    /**
     * 删除知识条目的图谱数据
     *
     * @param itemId 知识条目ID
     * @param kbId   知识库ID
     * @return 是否删除成功
     */
    boolean deleteGraphData(Long itemId, Long kbId);

    /**
     * 图谱提取结果
     */
    record GraphExtractionResult(
            int nodesCreated,
            int relationshipsCreated,
            boolean success,
            String errorMessage
    ) {
        public static GraphExtractionResult success(int nodes, int relationships) {
            return new GraphExtractionResult(nodes, relationships, true, null);
        }

        public static GraphExtractionResult failure(String errorMessage) {
            return new GraphExtractionResult(0, 0, false, errorMessage);
        }
    }
}
