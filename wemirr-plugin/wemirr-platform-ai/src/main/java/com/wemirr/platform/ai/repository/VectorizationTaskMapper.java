package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.VectorizationTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 向量化任务数据访问层
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Repository
public interface VectorizationTaskMapper extends SuperMapper<VectorizationTask> {

    /**
     * 根据任务ID查询任务
     *
     * @param taskId 任务ID
     * @return 向量化任务
     */
    VectorizationTask selectByTaskId(@Param("taskId") String taskId);

    /**
     * 根据知识条目ID查询任务
     *
     * @param itemId 知识条目ID
     * @return 向量化任务列表
     */
    List<VectorizationTask> selectByItemId(@Param("itemId") Long itemId);

    /**
     * 根据知识库ID查询任务
     *
     * @param kbId 知识库ID
     * @return 向量化任务列表
     */
    List<VectorizationTask> selectByKbId(@Param("kbId") String kbId);

    /**
     * 根据状态查询任务
     *
     * @param status 任务状态
     * @return 向量化任务列表
     */
    List<VectorizationTask> selectByStatus(@Param("status") String status);

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param status 任务状态
     * @param progress 处理进度
     * @param errorMessage 错误信息
     * @return 影响行数
     */
    int updateStatus(@Param("taskId") String taskId,
                     @Param("status") String status,
                     @Param("progress") Integer progress,
                     @Param("errorMessage") String errorMessage);

    /**
     * 更新任务向量ID
     *
     * @param taskId 任务ID
     * @param vectorIds 向量ID列表
     * @return 影响行数
     */
    int updateVectorIds(@Param("taskId") String taskId,
                        @Param("vectorIds") Map<String, Object> vectorIds);
}
