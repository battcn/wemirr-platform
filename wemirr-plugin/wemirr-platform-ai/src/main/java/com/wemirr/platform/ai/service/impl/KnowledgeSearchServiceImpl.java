package com.wemirr.platform.ai.service.impl;

import com.wemirr.platform.ai.domain.dto.rep.EmbeddingMatchRep;
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
 * @since 2025/10/21
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
    public List<EmbeddingMatchRep> semanticSearch(Long kbId, String query, int topK) {
        try {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
            if (knowledgeBase == null) {
                throw new IllegalArgumentException("知识库不存在: " + kbId);
            }

            ModelConfig embeddingModel = getEmbeddingModelById(knowledgeBase.getEmbeddingModelId());
            if (embeddingModel == null) {
                log.warn("未找到可用的嵌入模型配置....");
                return null;
            }

            if (!vectorSearchService.isVectorStoreAvailable(knowledgeBase, embeddingModel)) {
                log.warn("向量存储不可用...");
                return null;
            }

            List<EmbeddingMatch<TextSegment>> matches = vectorSearchService.search(knowledgeBase, embeddingModel, query, topK);
            
            return matches.stream()
                    .map(match -> {
                        return EmbeddingMatchRep.builder()
                                .content(match.embedded().text())
                                .score(match.score())
                                .metadata(match.embedded().metadata().toMap())
                                //语义搜索（向量）
                                .searchType("semantic")
                                .build();
                    })
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            log.error("语义搜索失败: kbId={}, query={}", kbId, query, e);
            log.warn("向量搜索失败....");
            return null;
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
            List<Map<String, Object>> semanticResults = semanticSearchMap(kbId, query, topK);
            
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

    public List<Map<String, Object>> semanticSearchMap(Long kbId, String query, int topK) {
        try {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
            if (knowledgeBase == null) {
                throw new IllegalArgumentException("知识库不存在: " + kbId);
            }

            ModelConfig embeddingModel = getEmbeddingModelById(knowledgeBase.getEmbeddingModelId());
            if (embeddingModel == null) {
                log.warn("未找到可用的嵌入模型配置，使用关键词搜索替代");
                return keywordSearch(kbId, query, topK);
            }

            if (!vectorSearchService.isVectorStoreAvailable(knowledgeBase, embeddingModel)) {
                log.warn("向量存储不可用，使用关键词搜索替代");
                return keywordSearch(kbId, query, topK);
            }

            List<EmbeddingMatch<TextSegment>> matches = vectorSearchService.search(knowledgeBase, embeddingModel, query, topK);

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
            log.warn("向量搜索失败，降级到关键词搜索");
            return keywordSearch(kbId, query, topK);
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
    private ModelConfig getEmbeddingModelById(Long modelId) {
        try {
            ModelConfig modelConfig = modelConfigService.getById(modelId);

            if (modelConfig==null) {
                log.warn("未找到可用的嵌入模型配置");
                return null;
            }
            return modelConfig;
            
        } catch (Exception e) {
            log.error("获取默认嵌入模型失败", e);
            return null;
        }
    }
}
