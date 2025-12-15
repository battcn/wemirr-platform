package com.wemirr.platform.ai.core.processor;

import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.EnhancedVectorStoreFactory;
import com.wemirr.platform.ai.domain.dto.result.BatchVectorResult;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeItem;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.domain.entity.VectorMetadata;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.KnowledgeChunkService;
import com.wemirr.platform.ai.service.KnowledgeItemService;
import com.wemirr.platform.ai.service.VectorMetadataService;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 向量化处理器
 * 专门负责将文本分片转换为向量表示并存储
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class VectorizationProcessor {

    private final EnhancedVectorStoreFactory enhancedVectorStoreFactory;
    private final EmbeddingModelService embeddingModelService;
    private final KnowledgeBaseService knowledgeBaseService;
    private final KnowledgeItemService knowledgeItemService;
    private final KnowledgeChunkService knowledgeChunkService;
    private final VectorMetadataService vectorMetadataService;

//    public VectorizationProcessor(EnhancedVectorStoreFactory enhancedVectorStoreFactory,
//                                  EmbeddingModelService embeddingModelService) {
//        this.enhancedVectorStoreFactory = enhancedVectorStoreFactory;
//        this.embeddingModelService = embeddingModelService;
//    }

    /**
     * 向量化单个文本并存储
     *
     * @param text 文本
     * @param metadata 元数据
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @return 向量ID
     */
    public CompletableFuture<String> vectorizeAndStore(String text, Map<String, String> metadata, 
                                                      KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 获取知识库专用的向量存储
                EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
                
                // 动态获取嵌入模型
                EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
                
                // 生成嵌入向量
                Embedding embedding = embeddingModel.embed(text).content();
                Metadata from = Metadata.from(metadata);
                
                // 存储向量
                TextSegment segment = TextSegment.from(text, from);
                String vectorId = embeddingStore.add(embedding, segment);
                
                return vectorId;
            } catch (Exception e) {
                log.error("向量化处理失败: {}", e.getMessage(), e);
                throw new RuntimeException("向量化处理失败", e);
            }
        });
    }
    
    /**
     * 向量化单个文本并存储（使用默认存储）
     *
     * @param text 文本
     * @param metadata 元数据
     * @param modelConfig 模型配置
     * @return 向量ID
     */
    public CompletableFuture<String> vectorizeAndStore(String text, Map<String, String> metadata, ModelConfig modelConfig) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 使用默认向量存储
                EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createefault();
                
                // 动态获取嵌入模型
                EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
                
                // 生成嵌入向量
                Embedding embedding = embeddingModel.embed(text).content();
                Metadata from = Metadata.from(metadata);
                
                // 存储向量
                TextSegment segment = TextSegment.from(text, from);
                String vectorId = embeddingStore.add(embedding, segment);
                
                return vectorId;
            } catch (Exception e) {
                log.error("向量化处理失败: {}", e.getMessage(), e);
                throw new RuntimeException("向量化处理失败", e);
            }
        });
    }

    /**
     * 批量向量化文本并存储
     *
     * @param texts 文本列表
     * @param metadataList 元数据列表
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @return 向量ID列表
     */
    public CompletableFuture<BatchVectorResult> batchVectorAndStore(List<String> texts, List<Map<String, String>> metadataList,
                                                                    KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 获取知识库专用的向量存储
                EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
                
                // 动态获取嵌入模型
                EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
                AtomicInteger totalTokens = new AtomicInteger(0);
                // 生成嵌入向量
                List<Embedding> embeddings = texts.stream()
                        .map(text ->{
                            Response<Embedding> result = embeddingModel.embed(text);
                            totalTokens.addAndGet(Objects.requireNonNull(result.tokenUsage()).totalTokenCount());
                            return result.content();
                        })
                        .collect(Collectors.toList());

                // 创建文本分片
                List<TextSegment> segments = createTextSegments(texts, metadataList);

                // 存储向量
                List<String> vectorIds = embeddingStore.addAll(embeddings, segments);
                return BatchVectorResult.builder()
                        .vectorIds(vectorIds)
                        .tokenUsage(totalTokens.get())
                        .build();
            } catch (Exception e) {
                log.error("批量向量化处理失败: {}", e.getMessage(), e);
                throw new RuntimeException("批量向量化处理失败", e);
            }
        });
    }
    
    /**
     * 批量向量化文本并存储（使用默认存储）
     *
     * @param texts 文本列表
     * @param metadataList 元数据列表
     * @param modelConfig 模型配置
     * @return 向量ID列表
     */
    public CompletableFuture<List<String>> batchVectorAndStore(List<String> texts, List<Map<String, String>> metadataList, ModelConfig modelConfig) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 使用默认向量存储
                EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createefault();
                
                // 动态获取嵌入模型
                EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
                
                // 生成嵌入向量
                List<Embedding> embeddings = texts.stream()
                        .map(text -> embeddingModel.embed(text).content())
                        .collect(Collectors.toList());

                // 创建文本分片
                List<TextSegment> segments = createTextSegments(texts, metadataList);

                // 存储向量
                List<String> vectorIds = embeddingStore.addAll(embeddings, segments);

                return vectorIds;
            } catch (Exception e) {
                log.error("批量向量化处理失败: {}", e.getMessage(), e);
                throw new RuntimeException("批量向量化处理失败", e);
            }
        });
    }
    
    /**
     * 创建文本分片
     *
     * @param texts 文本列表
     * @param metadataList 元数据列表
     * @return 文本分片列表
     */
    private List<TextSegment> createTextSegments(List<String> texts, List<Map<String, String>> metadataList) {
        return texts.stream()
                .map(text -> {
                    int index = texts.indexOf(text);
                    Map<String, String> metadata = index < metadataList.size() ? metadataList.get(index) : Map.of();
                    Metadata from = Metadata.from(metadata);
                    return TextSegment.from(text, from);
                })
                .collect(Collectors.toList());
    }
    
    /**
     * 删除向量
     *
     * @param vectorId 向量ID
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @return 是否删除成功
     */
    public boolean deleteVector(String vectorId, KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        try {
            // 获取知识库专用的向量存储
            EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
            
            // 删除向量
            try {
                embeddingStore.remove(vectorId);
                log.info("成功删除向量: vectorId={}, kbId={}", vectorId, knowledgeBase.getId());
                return true;
            } catch (Exception e) {
                log.warn("向量不存在或删除失败: vectorId={}", vectorId, e);
                return false;
            }
        } catch (Exception e) {
            log.error("删除向量失败: vectorId={}", vectorId, e);
            return false;
        }
    }
    
    /**
     * 删除向量（使用默认存储）
     *
     * @param baseItemId 原始条目ID
     * @return 是否删除成功
     */
    public boolean deleteVector(String baseItemId) {

        try {
            // 使用默认向量存储
            EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createefault();
            KnowledgeItem item = knowledgeItemService.getById(baseItemId);
            //获取向量idList
            List<String> vectorIds = vectorMetadataService.findByItemId(item.getId()).stream().map(VectorMetadata::getVectorId).toList();
            embeddingStore.removeAll(vectorIds);
            // 删除向量元数据
            vectorMetadataService.findByItemId(Long.valueOf(baseItemId));
            return true;
        } catch (Exception e) {
            log.error("删除向量失败: 原始条目ID={}", baseItemId, e);
            return false;
        }
    }
    
    /**
     * 批量删除向量
     *
     * @param vectorIds 向量ID列表
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @return 删除成功的数量
     */
    public int batchDeleteVectors(List<String> vectorIds, KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        try {
            // 获取知识库专用的向量存储
            EmbeddingStore<TextSegment> embeddingStore = enhancedVectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
            
            int deletedCount = 0;
            for (String vectorId : vectorIds) {
                try {
                    embeddingStore.remove(vectorId);
                    deletedCount++;
                } catch (Exception e) {
                    log.warn("删除向量失败: vectorId={}", vectorId, e);
                }
            }
            
            log.info("批量删除向量完成: 总数={}, 成功={}", vectorIds.size(), deletedCount);
            return deletedCount;
        } catch (Exception e) {
            log.error("批量删除向量失败", e);
            return 0;
        }
    }
    
}