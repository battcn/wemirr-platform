package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.enums.KnowledgeItemStatus;
import com.wemirr.platform.ai.core.processor.VectorizationProcessor;
import com.wemirr.platform.ai.domain.dto.rep.VectorizationRep;
import com.wemirr.platform.ai.domain.dto.req.VectorizationTaskPageReq;
import com.wemirr.platform.ai.domain.dto.result.VectorizationResult;
import com.wemirr.platform.ai.domain.entity.*;
import com.wemirr.platform.ai.repository.VectorizationTaskMapper;
import com.wemirr.platform.ai.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 向量化服务实现类
 * 主要负责向量化任务的管理和调度，委托实际的向量化处理给VectorizationProcessor
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class VectorServiceImpl extends SuperServiceImpl<VectorizationTaskMapper, VectorizationTask> implements VectorService {

    private final VectorizationProcessor vectorizationProcessor;
    private final VectorizationOrchestrationService vectorizationOrchestrationService;
    private final KnowledgeItemService knowledgeItemService;
    private final VectorMetadataService vectorMetadataService;
    private final KnowledgeBaseService knowledgeBaseService;
    private final ModelConfigService modelConfigService;


    @Override
    public void vectorizeKnowledgeItem(Long itemId) {
        KnowledgeItem item = knowledgeItemService.getById(itemId);
        VectorizationTask vectorizationTask = this.baseMapper.selectOne(Wraps.<VectorizationTask>lbQ().eq(VectorizationTask::getItemId, itemId));
        if (vectorizationTask != null && vectorizationTask.getTaskStatus() != VectorizationTaskStatus.FAILED) {
            throw CheckedException.badRequest("不可再次创建任务");
        }
        item.setStatus(KnowledgeItemStatus.PROCESSING);
        knowledgeItemService.updateById(item);
        String taskId = IdUtil.fastSimpleUUID();
        createTask(taskId, item.getKbId(), itemId, "KNOWLEDGE_ITEM");
        runAsyncTask(taskId, () -> vectorizationOrchestrationService.vectorizeKnowledgeItem(itemId));
    }

    @Override
    public String vectorizeKnowledgeItems(List<Long> itemIds) {
        String taskId = IdUtil.fastSimpleUUID();
        createTask(taskId, null, null, "BATCH");
        CompletableFuture.runAsync(() -> {
            try {
                updateTaskStatus(taskId, VectorizationTaskStatus.PROCESSING, 10, null);
                for (Long itemId : itemIds) {
                    try {
                        vectorizationOrchestrationService.vectorizeKnowledgeItem(itemId);
                    } catch (Exception e) {
                        log.error("向量化知识条目失败: itemId={}", itemId, e);
                    }
                }
                updateTaskStatus(taskId, VectorizationTaskStatus.COMPLETED, 100, null);
            } catch (Exception e) {
                log.error("批量知识条目向量化失败: {}", e.getMessage(), e);
                updateTaskStatus(taskId, VectorizationTaskStatus.FAILED, 0, e.getMessage());
            }
        });
        return taskId;
    }

    @Override
    public String vectorizeDocument(Long docId) {
        String taskId = IdUtil.fastSimpleUUID();
        createTask(taskId, null, null, "DOCUMENT");
        runAsyncTask(taskId, () -> vectorizationOrchestrationService.vectorizeDocument(docId));
        return taskId;
    }

    @Override
    public String vectorizeFAQ(Long faqId) {
        String taskId = IdUtil.fastSimpleUUID();
        createTask(taskId, null, faqId, "FAQ");
        runAsyncTask(taskId, () -> vectorizationOrchestrationService.vectorizeKnowledgeItem(faqId));
        return taskId;
    }

    @Override
    public String vectorizeStructuredData(Long structuredDataId) {
        String taskId = IdUtil.fastSimpleUUID();
        createTask(taskId, null, structuredDataId, "STRUCTURED");
        runAsyncTask(taskId, () -> vectorizationOrchestrationService.vectorizeKnowledgeItem(structuredDataId));
        return taskId;
    }


    @Override
    public VectorizationTaskStatus getTaskStatus(String taskId) {
        VectorizationTask task = baseMapper.selectByTaskId(taskId);
        return task != null ? VectorizationTaskStatus.valueOf(task.getStatus()) : VectorizationTaskStatus.PENDING;
    }

    @Override
    public boolean deleteVector(Long baseItemId) {
        KnowledgeItem item = knowledgeItemService.getById(baseItemId);
        if (item == null) {
            log.warn("删除向量失败，知识条目不存在: itemId={}", baseItemId);
            return false;
        }
        List<String> vectorIds = vectorMetadataService.findByItemId(baseItemId).stream()
                .map(VectorMetadata::getVectorId)
                .collect(Collectors.toList());
        if (vectorIds.isEmpty()) {
            log.info("知识条目无向量可删: itemId={}", baseItemId);
            return true;
        }
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(item.getKbId());
        ModelConfig modelConfig = modelConfigService.getById(knowledgeBase.getEmbeddingModelId());
        vectorizationProcessor.batchDeleteVectors(vectorIds, knowledgeBase, modelConfig);
        vectorMetadataService.deleteByItemId(baseItemId);
        // 更新条目标记
        item.setVectorized(false);
        knowledgeItemService.updateById(item);
        return true;
    }

    @Override
    public int deleteVectorsByMetadata(String metadataKey, String metadataValue) {
        // 基于元数据查找所有向量元数据并按kb分组删除
        List<VectorMetadata> all = vectorMetadataService.list();
        List<VectorMetadata> targets = all.stream()
                .filter(vm -> Boolean.FALSE.equals(vm.getDeleted()))
                .filter(vm -> vm.getMetadata() != null && metadataValue.equals(String.valueOf(vm.getMetadata().get(metadataKey))))
                .collect(Collectors.toList());
        Map<Long, List<String>> kbIdToVectorIds = targets.stream()
                .collect(Collectors.groupingBy(VectorMetadata::getKbId, Collectors.mapping(VectorMetadata::getVectorId, Collectors.toList())));
        int totalDeleted = 0;
        for (Map.Entry<Long, List<String>> entry : kbIdToVectorIds.entrySet()) {
            Long kbId = entry.getKey();
            List<String> vectorIds = entry.getValue();
            if (vectorIds.isEmpty()) {
                continue;
            }
            KnowledgeBase kb = knowledgeBaseService.getById(kbId);
            if (kb == null) {
                continue;
            }
            ModelConfig modelConfig = modelConfigService.getById(kb.getEmbeddingModelId());
            totalDeleted += vectorizationProcessor.batchDeleteVectors(vectorIds, kb, modelConfig);
        }
        // 软删元数据
        targets.forEach(vm -> vectorMetadataService.deleteByVectorId(vm.getVectorId()));
        return totalDeleted;
    }

    @Override
    public int deleteVectorsByKbId(Long kbId) {
        List<VectorMetadata> vms = vectorMetadataService.findByKbId(kbId);
        if (vms.isEmpty()) {
            return 0;
        }
        KnowledgeBase kb = knowledgeBaseService.getById(kbId);
        if (kb == null) {
            return 0;
        }
        ModelConfig modelConfig = modelConfigService.getById(kb.getEmbeddingModelId());
        int deleted = vectorizationProcessor.batchDeleteVectors(
                vms.stream().map(VectorMetadata::getVectorId).collect(Collectors.toList()),
                kb,
                modelConfig
        );
        vectorMetadataService.deleteByKbId(kbId);
        return deleted;
    }

    @Override
    public int deleteVectorsByItemId(Long itemId) {
        KnowledgeItem item = knowledgeItemService.getById(itemId);
        if (item == null) {
            return 0;
        }
        List<VectorMetadata> vms = vectorMetadataService.findByItemId(itemId);
        if (vms.isEmpty()) {
            return 0;
        }
        KnowledgeBase kb = knowledgeBaseService.getById(item.getKbId());
        ModelConfig modelConfig = modelConfigService.getById(kb.getEmbeddingModelId());
        int deleted = vectorizationProcessor.batchDeleteVectors(
                vms.stream().map(VectorMetadata::getVectorId).collect(Collectors.toList()),
                kb,
                modelConfig
        );
        vectorMetadataService.deleteByItemId(itemId);
        item.setVectorized(false);
        knowledgeItemService.updateById(item);
        return deleted;
    }

    @Override
    public int deleteVectorsByChunkId(Long chunkId) {
        VectorMetadata vm = vectorMetadataService.findByChunkId(chunkId);
        if (vm == null) {
            return 0;
        }
        KnowledgeBase kb = knowledgeBaseService.getById(vm.getKbId());
        ModelConfig modelConfig = modelConfigService.getById(kb.getEmbeddingModelId());
        int deleted = vectorizationProcessor.batchDeleteVectors(List.of(vm.getVectorId()), kb, modelConfig);
        vectorMetadataService.deleteByVectorId(vm.getVectorId());
        return deleted;
    }

    @Override
    public IPage<VectorizationTask> pageList(VectorizationTaskPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<VectorizationTask>lbQ()
                .orderByDesc(VectorizationTask::getCreateName)
        );
    }

    @Override
    public VectorizationRep getVectorizeStatus(Long itemId) {
        VectorizationTask vectorizationTask = this.baseMapper.selectOne(Wraps.<VectorizationTask>lbQ().eq(VectorizationTask::getItemId, itemId));
        if (vectorizationTask != null) {
            return BeanUtilPlus.toBean(vectorizationTask, VectorizationRep.class);
        }
        return null;
    }

    /**
     * 创建向量化任务
     *
     * @param taskId 任务ID
     * @param kbId 知识库ID
     * @param itemId 知识条目ID
     * @param taskType 任务类型
     * @return 任务实体
     */
    private VectorizationTask createTask(String taskId, Long kbId, Long itemId, String taskType) {
        VectorizationTask task = VectorizationTask.builder()
                .taskId(taskId)
                .kbId(kbId)
                .itemId(itemId)
                .taskType(taskType)
                .status(VectorizationTaskStatus.PENDING.name())
                .vectorized(false)
                .progress(0)
                .deleted(false)
                .build();

        baseMapper.insert(task);
        return task;
    }

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param status 任务状态
     * @param progress 处理进度
     * @param errorMessage 错误信息
     */
    private void updateTaskStatus(String taskId, VectorizationTaskStatus status, Integer progress, String errorMessage) {
        baseMapper.updateStatus(taskId, status.name(), progress, errorMessage);

        // 如果任务完成，更新vectorized字段
        if (status == VectorizationTaskStatus.COMPLETED) {
            VectorizationTask task = baseMapper.selectByTaskId(taskId);
            if (task != null) {
                task.setVectorized(false);
                task.setProgress(progress);
                task.setTaskStatus(status);
                baseMapper.updateById(task);
            }
        }
    }

    /**
     * 更新任务向量ID
     *
     * @param taskId 任务ID
     * @param vectorIds 向量ID列表
     */
    private void updateTaskVectorIds(String taskId, Map<String, Object> vectorIds) {
        baseMapper.updateVectorIds(taskId, vectorIds);
    }

    /**
     * 统一的异步任务执行模板：负责状态机与异常处理
     */
    private void runAsyncTask(String taskId, Supplier<VectorizationResult> taskSupplier) {
        CompletableFuture.supplyAsync(taskSupplier)
                .thenAccept(result -> {
                    // 成功：更新任务状态 + token 消耗
                    updateTaskStatus(taskId, VectorizationTaskStatus.COMPLETED, 100, null);
                    if (result != null && result.getTotalTokenUsage() != null) {
                        updateTaskTokenUsage(taskId, result.getTotalTokenUsage());
                    }
                })
                .exceptionally(throwable -> {
                    log.error("向量化任务执行失败: taskId={}", taskId, throwable);
                    updateTaskStatus(taskId, VectorizationTaskStatus.FAILED, 0, throwable.getMessage());
                    return null;
                });
    }

    private void updateTaskTokenUsage(String taskId, Integer totalTokenUsage) {
        VectorizationTask task = baseMapper.selectByTaskId(taskId);
        if (task != null) {
            task.setTokenUsage(totalTokenUsage);
            baseMapper.updateById(task);
        }
    }
}