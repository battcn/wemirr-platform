package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.core.enums.KnowledgeItemStatus;
import com.wemirr.platform.ai.core.processor.VectorizationProcessor;
import com.wemirr.platform.ai.core.provider.vectorStore.EnhancedVectorStoreFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 向量化编排服务
 * 负责协调文档处理和向量化过程
 *
 * @author xJh
 * @since 2025/10/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VectorizationOrchestrationService {
    
    private final VectorizationProcessor vectorizationProcessor;
    private final KnowledgeChunkService knowledgeChunkService;
    private final KnowledgeItemService knowledgeItemService;
    private final KnowledgeBaseService knowledgeBaseService;
    private final ModelConfigService modelConfigService;
    private final EnhancedVectorStoreFactory enhancedVectorStoreFactory;
    private final VectorMetadataService vectorMetadataService;
    
    /**
     * 向量化知识条目
     * 
     * @param itemId 知识条目ID
     * @return 向量化任务ID
     */
    @Transactional(rollbackFor = Exception.class)
    public String vectorizeKnowledgeItem(Long itemId) {
        // 获取知识条目
        KnowledgeItem item = knowledgeItemService.getById(itemId);
        try {
            if (item == null) {
                throw new RuntimeException("知识条目不存在: " + itemId);
            }
            
            // 获取知识库和模型配置
            KnowledgeBase kb = knowledgeBaseService.getById(item.getKbId());
            ModelConfig modelConfig = modelConfigService.getById(kb.getEmbeddingModelId());
            
            // 获取知识库专用的向量存储（用于后续扩展）
            // EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createForKnowledgeBase(kb, modelConfig);
            
            // 获取相关的知识分片
            List<KnowledgeChunk> chunks = knowledgeChunkService.listByItemId(itemId);
            if (chunks.isEmpty()) {
                log.warn("知识条目没有关联的分片: itemId={}", itemId);
                return null;
            }
            
            // 准备向量化的文本和元数据
            List<String> texts = chunks.stream()
                    .map(KnowledgeChunk::getContent)
                    .collect(Collectors.toList());
            
            List<Map<String, String>> metadataList = chunks.stream()
                    .map(chunk -> {
                        Map<String, String> metadata = new HashMap<>();
                        metadata.put("kbId", String.valueOf(chunk.getKbId()));
                        metadata.put("itemId", String.valueOf(chunk.getItemId()));
                        metadata.put("chunkId", String.valueOf(chunk.getId()));
                        metadata.put("chunkType", chunk.getChunkType().getCode());
                        metadata.put("kbName", kb.getName());
                        metadata.put("itemTitle", item.getTitle());
                        if (chunk.getMetadata() != null) {
                            chunk.getMetadata().forEach((key, value) -> 
                                metadata.put(key, value != null ? value.toString() : "")
                            );
                        }
                        return metadata;
                    })
                    .collect(Collectors.toList());
            
            // 执行批量向量化
            CompletableFuture<List<String>> future = vectorizationProcessor.batchVectorizeAndStore(texts, metadataList, kb, modelConfig);
            List<String> vectorIds = future.get(); // 等待完成
            
            // 保存向量元数据
            List<VectorMetadata> vectorMetadataList = new ArrayList<>();
            for (int i = 0; i < chunks.size() && i < vectorIds.size(); i++) {
                KnowledgeChunk chunk = chunks.get(i);
                String vectorId = vectorIds.get(i);
                
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("kbName", kb.getName());
                metadata.put("itemTitle", item.getTitle());
                metadata.put("chunkType", chunk.getChunkType().getCode());
                metadata.put("chunkIndex", chunk.getChunkIndex());
                if (chunk.getMetadata() != null) {
                    metadata.putAll(chunk.getMetadata());
                }
                
                VectorMetadata vectorMetadata = VectorMetadata.builder()
                        .vectorId(vectorId)
                        .kbId(chunk.getKbId())
                        .itemId(chunk.getItemId())
                        .chunkId(chunk.getId())
                        .chunkType(chunk.getChunkType())
                        //.dimension(384) // TODO 默认维度，应该从模型配置获取
                        .storeType("milvus") //TODO 根据配置确定
                        .collectionName(generateCollectionName(kb))
                        .textContent(chunk.getContent())
                        .textHash(chunk.getContentHash())
                        .metadata(metadata)
                        .deleted(false)
                        .build();
                
                vectorMetadataList.add(vectorMetadata);
            }
            
            // 批量保存向量元数据
            if (!vectorMetadataList.isEmpty()) {
                vectorMetadataService.batchSave(vectorMetadataList);
            }
            
            // 更新知识分片的向量引用
            for (int i = 0; i < chunks.size() && i < vectorIds.size(); i++) {
                KnowledgeChunk chunk = chunks.get(i);
                String vectorId = vectorIds.get(i);
                chunk.setVectorRef("milvus:" + vectorId);
                knowledgeChunkService.updateById(chunk);
            }
            
            // 更新知识条目状态为已向量化
            item.setVectorized(true);
            item.setStatus(KnowledgeItemStatus.PROCESSED);
            knowledgeItemService.updateById(item);
            
            return "vectorization_task_" + itemId;
        } catch (Exception e) {
            item.setStatus(KnowledgeItemStatus.FAILED);
            knowledgeItemService.updateById(item);
            log.error("向量化知识条目失败: itemId={}", itemId, e);
            throw new RuntimeException("向量化知识条目失败", e);
        }
    }
    
    /**
     * 生成集合名称
     */
    private String generateCollectionName(KnowledgeBase kb) {
        return "kb_" + kb.getId() + "_vectors";
    }
    
    /**
     * 向量化文档
     * 
     * @param docId 文档ID
     * @return 向量化任务ID
     */
    public String vectorizeDocument(Long docId) {
        try {
            // 查找关联的知识条目
            KnowledgeItem item = knowledgeItemService.selectBySourceId(docId);
            if (item == null) {
                throw new RuntimeException("未找到文档对应的知识条目: docId=" + docId);
            }
            
            return vectorizeKnowledgeItem(item.getId());
        } catch (Exception e) {
            log.error("向量化文档失败: docId={}", docId, e);
            throw new RuntimeException("向量化文档失败", e);
        }
    }
    
    /**
     * 向量化FAQ
     * 
     * @param faqId FAQ ID
     * @return 向量化任务ID
     */
    public String vectorizeFAQ(Long faqId) {
        return vectorizeKnowledgeItem(faqId);
    }
    
    /**
     * 向量化结构化数据
     * 
     * @param structuredDataId 结构化数据ID
     * @return 向量化任务ID
     */
    public String vectorizeStructuredData(Long structuredDataId) {
        return vectorizeKnowledgeItem(structuredDataId);
    }
    
    /**
     * 删除知识条目的向量
     * 
     * @param itemId 知识条目ID
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteVectorForItem(Long itemId) {
        try {
            // 获取知识条目
            KnowledgeItem item = knowledgeItemService.getById(itemId);
            if (item == null) {
                log.warn("知识条目不存在: itemId={}", itemId);
                return false;
            }
            
            // 获取知识分片
            List<KnowledgeChunk> chunks = knowledgeChunkService.listByItemId(itemId);
            
            // 删除向量元数据
            int deletedCount = vectorMetadataService.deleteByItemId(itemId);
            log.info("删除了 {} 个向量元数据记录", deletedCount);
            
            // 更新知识分片的向量引用
            for (KnowledgeChunk chunk : chunks) {
                chunk.setVectorRef(null);
                knowledgeChunkService.updateById(chunk);
            }
            
            // 更新知识条目状态为未向量化
            item.setVectorized(false);
            knowledgeItemService.updateById(item);
            
            return true;
        } catch (Exception e) {
            log.error("删除知识条目向量失败: itemId={}", itemId, e);
            return false;
        }
    }
    
    /**
     * 删除知识库的所有向量
     * 
     * @param kbId 知识库ID
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteVectorForKnowledgeBase(Long kbId) {
        try {
            // 删除向量元数据
            int deletedCount = vectorMetadataService.deleteByKbId(kbId);
            log.info("删除了知识库 {} 的 {} 个向量元数据记录", kbId, deletedCount);
            
            // 清除向量存储缓存
            enhancedVectorStoreFactory.clearCacheForKnowledgeBase(Long.valueOf(kbId));
            
            return true;
        } catch (Exception e) {
            log.error("删除知识库向量失败: kbId={}", kbId, e);
            return false;
        }
    }
    
    /**
     * 重新向量化知识条目
     * 先删除现有向量，再重新创建
     * 
     * @param itemId 知识条目ID
     * @return 向量化任务ID
     */
    @Transactional(rollbackFor = Exception.class)
    public String reVectorizeKnowledgeItem(Long itemId) {
        try {
            // 先删除现有向量
            deleteVectorForItem(itemId);
            
            // 重新向量化
            return vectorizeKnowledgeItem(itemId);
        } catch (Exception e) {
            log.error("重新向量化知识条目失败: itemId={}", itemId, e);
            throw new RuntimeException("重新向量化知识条目失败", e);
        }
    }
}