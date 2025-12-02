package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeChunk;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.repository.KnowledgeChunkMapper;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.KnowledgeSearchService;
import com.wemirr.platform.ai.service.ModelConfigService;
import com.wemirr.platform.ai.service.VectorSearchService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 向量、文本搜索
 *
 * @author xJh
 * @date 2025/10/21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class KnowledgeSearchServiceImpl implements KnowledgeSearchService {

    private final KnowledgeChunkMapper knowledgeChunkMapper;
    private final KnowledgeBaseService knowledgeBaseService;
    private final ModelConfigService modelConfigService;
    private final VectorSearchService vectorSearchService;

    @Override
    public List<Map<String, Object>> semanticSearch(Long kbId, String query, int topK) {
        try {
            // 1. 获取知识库信息
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
            if (knowledgeBase == null) {
                throw new IllegalArgumentException("知识库不存在: " + kbId);
            }

            // 2. 获取默认的嵌入模型配置
            ModelConfig embeddingModel = getDefaultEmbeddingModel();
            if (embeddingModel == null) {
                log.warn("未找到可用的嵌入模型配置，使用关键词搜索替代");
                return keywordSearch(kbId, query, topK);
            }

            // 3. 检查向量存储是否可用
            if (!vectorSearchService.isVectorStoreAvailable(knowledgeBase, embeddingModel)) {
                log.warn("向量存储不可用，使用关键词搜索替代");
                return keywordSearch(kbId, query, topK);
            }

            // 4. 执行向量搜索
            List<EmbeddingMatch<TextSegment>> matches = vectorSearchService.search(knowledgeBase, embeddingModel, query, topK);
            
            // 5. 转换为结果格式
            return matches.stream()
                    .map(match -> {
                        Map<String, Object> result = new HashMap<>();
                        result.put("content", match.embedded().text());
                        result.put("score", match.score());
                        result.put("metadata", match.embedded().metadata().toMap());
                        result.put("searchType", "semantic");
                        return result;
                    })
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            log.error("语义搜索失败: kbId={}, query={}", kbId, query, e);
            // 如果向量搜索失败，降级到关键词搜索
            log.warn("向量搜索失败，降级到关键词搜索");
            return keywordSearch(kbId, query, topK);
        }
    }

    @Override
    public List<Map<String, Object>> keywordSearch(Long kbId, String query, int topK) {
        try {
            // 使用SQL进行关键词搜索
            List<KnowledgeChunk> chunks = knowledgeChunkMapper.searchByKeyword(kbId, query, topK);
            
            return chunks.stream()
                    .map(chunk -> {
                        Map<String, Object> result = new HashMap<>();
                        result.put("content", chunk.getContent());
                        result.put("score", calculateKeywordScore(chunk.getContent(), query));
                        result.put("metadata", chunk.getMetadata());
                        result.put("chunkId", chunk.getId());
                        result.put("itemId", chunk.getItemId());
                        return result;
                    })
                    .sorted((a, b) -> Double.compare((Double) b.get("score"), (Double) a.get("score")))
                    .limit(topK)
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            log.error("关键词搜索失败: kbId={}, query={}", kbId, query, e);
            throw new RuntimeException("关键词搜索失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Map<String, Object>> hybridSearch(Long kbId, String query, int topK) {
        try {
            // 执行语义搜索
            List<Map<String, Object>> semanticResults = semanticSearch(kbId, query, topK);
            
            // 执行关键词搜索
            List<Map<String, Object>> keywordResults = keywordSearch(kbId, query, topK);
            
            // 合并结果并去重
            Map<String, Map<String, Object>> resultMap = new HashMap<>();
            
            // 添加语义搜索结果
            for (Map<String, Object> result : semanticResults) {
                String content = (String) result.get("content");
                if (StringUtils.hasText(content)) {
                    result.put("searchType", "semantic");
                    resultMap.put(content, result);
                }
            }
            
            // 添加关键词搜索结果，如果内容已存在则合并分数
            for (Map<String, Object> result : keywordResults) {
                String content = (String) result.get("content");
                if (StringUtils.hasText(content)) {
                    if (resultMap.containsKey(content)) {
                        // 合并分数
                        Map<String, Object> existing = resultMap.get(content);
                        double semanticScore = (Double) existing.get("score");
                        double keywordScore = (Double) result.get("score");
                        existing.put("score", (semanticScore + keywordScore) / 2);
                        existing.put("searchType", "hybrid");
                    } else {
                        result.put("searchType", "keyword");
                        resultMap.put(content, result);
                    }
                }
            }
            
            // 按分数排序并返回topK结果
            return resultMap.values().stream()
                    .sorted((a, b) -> Double.compare((Double) b.get("score"), (Double) a.get("score")))
                    .limit(topK)
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            log.error("混合搜索失败: kbId={}, query={}", kbId, query, e);
            throw new RuntimeException("混合搜索失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> retrieveContent(Long kbId, String query, int topK) {
        try {
            // 使用混合搜索获取相关内容
            List<Map<String, Object>> results = hybridSearch(kbId, query, topK);
            
            // 提取内容文本
            return results.stream()
                    .map(result -> (String) result.get("content"))
                    .filter(StringUtils::hasText)
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            log.error("内容召回失败: kbId={}, query={}", kbId, query, e);
            throw new RuntimeException("内容召回失败: " + e.getMessage(), e);
        }
    }


    /**
     * 计算关键词匹配分数
     */
    private double calculateKeywordScore(String content, String query) {
        if (!StringUtils.hasText(content) || !StringUtils.hasText(query)) {
            return 0.0;
        }
        
        String lowerContent = content.toLowerCase();
        String lowerQuery = query.toLowerCase();
        
        // 简单的关键词匹配分数计算
        int matchCount = 0;
        String[] queryWords = lowerQuery.split("\\s+");
        
        for (String word : queryWords) {
            if (lowerContent.contains(word)) {
                matchCount++;
            }
        }
        
        return (double) matchCount / queryWords.length;
    }

    /**
     * 获取默认的嵌入模型配置
     */
    private ModelConfig getDefaultEmbeddingModel() {
        try {
            // 查询可用的嵌入模型配置
            // 这里可以根据实际业务需求来选择默认模型
            // 例如：选择第一个可用的嵌入模型，或者根据优先级选择
            List<ModelConfig> embeddingModels = modelConfigService.list(
                Wraps.<ModelConfig>lbQ()
                    .eq(ModelConfig::getModelType, "EMBEDDING")
//                    .eq(ModelConfig::getEnabled, true)
            );
            
            if (embeddingModels.isEmpty()) {
                log.warn("未找到可用的嵌入模型配置");
                return null;
            }
            
            // 返回第一个可用的嵌入模型
            ModelConfig defaultModel = embeddingModels.get(0);
            log.info("使用默认嵌入模型: {}", defaultModel.getModelName());
            return defaultModel;
            
        } catch (Exception e) {
            log.error("获取默认嵌入模型失败", e);
            return null;
        }
    }
}
