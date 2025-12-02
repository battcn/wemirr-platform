package com.wemirr.platform.ai.service.impl;

import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.EnhancedVectorStoreFactory;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.VectorSearchService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 向量搜索服务实现类
 * 专门处理向量搜索相关功能
 *
 * @author xJh
 * @date 2025/10/21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class VectorSearchServiceImpl implements VectorSearchService {

    private final EnhancedVectorStoreFactory vectorStoreFactory;
    private final EmbeddingModelService embeddingModelService;

    @Override
    public List<EmbeddingMatch<TextSegment>> search(KnowledgeBase knowledgeBase, ModelConfig modelConfig, String query, int topK) {
        try {
            // 1. 获取向量存储
            EmbeddingStore<TextSegment> embeddingStore = vectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
            
            // 2. 创建嵌入模型实例
            EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
            
            // 3. 生成查询向量
            Embedding queryEmbedding = embeddingModel.embed(query).content();
            
            // 4. 创建搜索请求
            EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(topK)
                    .build();
            
            // 5. 执行向量搜索
            List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
            
            log.info("向量搜索完成: kbId={}, query={}, topK={}, results={}", 
                    knowledgeBase.getId(), query, topK, matches.size());
            
            return matches;
            
        } catch (Exception e) {
            log.error("向量搜索失败: kbId={}, query={}", knowledgeBase.getId(), query, e);
            throw new RuntimeException("向量搜索失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<EmbeddingMatch<TextSegment>> search(KnowledgeBase knowledgeBase, String query, int topK) {
        try {
            // 获取默认模型配置
            ModelConfig defaultModel = getDefaultModelConfig();
            if (defaultModel == null) {
                throw new IllegalStateException("未找到可用的嵌入模型配置");
            }
            
            return search(knowledgeBase, defaultModel, query, topK);
            
        } catch (Exception e) {
            log.error("向量搜索失败（使用默认模型）: kbId={}, query={}", knowledgeBase.getId(), query, e);
            throw new RuntimeException("向量搜索失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<List<EmbeddingMatch<TextSegment>>> batchSearch(KnowledgeBase knowledgeBase, ModelConfig modelConfig, List<String> queries, int topK) {
        try {
            // 1. 获取向量存储
            EmbeddingStore<TextSegment> embeddingStore = vectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
            
            // 2. 创建嵌入模型实例
            EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
            
            // 3. 批量执行向量搜索
            List<List<EmbeddingMatch<TextSegment>>> results = new ArrayList<>();
            for (String query : queries) {
                // 生成查询向量
                Embedding queryEmbedding = embeddingModel.embed(query).content();
                
                // 创建搜索请求
                EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                        .queryEmbedding(queryEmbedding)
                        .maxResults(topK)
                        .build();
                
                // 执行搜索
                List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
                results.add(matches);
            }
            
            log.info("批量向量搜索完成: kbId={}, queries={}, topK={}", 
                    knowledgeBase.getId(), queries.size(), topK);
            
            return results;
            
        } catch (Exception e) {
            log.error("批量向量搜索失败: kbId={}, queries={}", knowledgeBase.getId(), queries.size(), e);
            throw new RuntimeException("批量向量搜索失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Embedding getQueryEmbedding(String query, ModelConfig modelConfig) {
        try {
            // 创建嵌入模型实例
            EmbeddingModel embeddingModel = embeddingModelService.getModel(modelConfig);
            
            // 生成查询向量
            Embedding queryEmbedding = embeddingModel.embed(query).content();
            
            log.debug("生成查询向量: query={}, dimension={}", query, queryEmbedding.dimension());
            
            return queryEmbedding;
            
        } catch (Exception e) {
            log.error("生成查询向量失败: query={}", query, e);
            throw new RuntimeException("生成查询向量失败: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isVectorStoreAvailable(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        try {
            // 尝试创建向量存储连接
            vectorStoreFactory.createForKnowledgeBase(knowledgeBase, modelConfig);
            
            // 尝试创建嵌入模型
            embeddingModelService.getModel(modelConfig);
            
            log.debug("向量存储可用性检查通过: kbId={}, model={}", knowledgeBase.getId(), modelConfig.getModelName());
            return true;
            
        } catch (Exception e) {
            log.warn("向量存储不可用: kbId={}, model={}, error={}", 
                    knowledgeBase.getId(), modelConfig.getModelName(), e.getMessage());
            return false;
        }
    }

    @Override
    public Map<String, Object> getVectorStoreStats(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        Map<String, Object> stats = new HashMap<>();
        
        try {
            // 检查向量存储可用性
            boolean available = isVectorStoreAvailable(knowledgeBase, modelConfig);
            stats.put("available", available);
            
        if (available) {
            // 这里可以添加更多统计信息
            // 例如：向量数量、存储大小等
            stats.put("storeType", "vector");
            stats.put("modelName", modelConfig.getModelName());
            stats.put("modelProvider", modelConfig.getProvider());
        }
            
        } catch (Exception e) {
            log.error("获取向量存储统计信息失败: kbId={}", knowledgeBase.getId(), e);
            stats.put("error", e.getMessage());
        }
        
        return stats;
    }

    /**
     * 获取默认模型配置
     * 这里需要根据实际业务逻辑来实现
     */
    private ModelConfig getDefaultModelConfig() {
        // TODO: 实现获取默认模型配置的逻辑
        // 可以从配置文件中读取，或者从数据库中查询
        throw new UnsupportedOperationException("获取默认模型配置功能尚未实现");
    }
}
