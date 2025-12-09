package com.wemirr.platform.ai.controller;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.ModelConfigService;
import com.wemirr.platform.ai.service.VectorSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 向量搜索控制器
 * 提供向量搜索相关的测试和管理功能
 *
 * @author xJh
 * @since 2025/10/21
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/vector-search")
@Tag(name = "向量搜索", description = "向量搜索测试和管理功能")
public class VectorSearchController {

    private final VectorSearchService vectorSearchService;
    private final KnowledgeBaseService knowledgeBaseService;
    private final ModelConfigService modelConfigService;

    @GetMapping("/test")
    @Operation(summary = "测试向量搜索")
    public Result<String> testVectorSearch(@RequestParam String kbId, 
                                        @RequestParam String query,
                                        @RequestParam(defaultValue = "5") int topK) {
        try {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
            if (knowledgeBase == null) {
                return Result.fail("知识库不存在: " + kbId);
            }

            // 执行向量搜索
            var matches = vectorSearchService.search(knowledgeBase, query, topK);
            
            return Result.success("向量搜索测试成功，找到 " + matches.size() + " 个结果");
            
        } catch (Exception e) {
            log.error("向量搜索测试失败: kbId={}, query={}", kbId, query, e);
            return Result.fail("向量搜索测试失败: " + e.getMessage());
        }
    }


    @GetMapping("/check")
    @Operation(summary = "检查向量存储可用性")
    public Result<Boolean> checkVectorStoreAvailability(@RequestParam String kbId) {
        try {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
            if (knowledgeBase == null) {
                return Result.fail("知识库不存在: " + kbId);
            }

            // 获取默认模型配置
            ModelConfig defaultModel = getDefaultEmbeddingModel();
            if (defaultModel == null) {
                return Result.fail("未找到可用的嵌入模型配置");
            }

            boolean available = vectorSearchService.isVectorStoreAvailable(knowledgeBase, defaultModel);
            return Result.success(available);
            
        } catch (Exception e) {
            log.error("检查向量存储可用性失败: kbId={}", kbId, e);
            return Result.fail("检查失败: " + e.getMessage());
        }
    }

    /**
     * 获取默认的嵌入模型配置
     */
    private ModelConfig getDefaultEmbeddingModel() {
        try {
            // 查询可用的嵌入模型配置
            List<ModelConfig> embeddingModels = modelConfigService.list(
                com.wemirr.framework.db.mybatisplus.wrap.Wraps.<ModelConfig>lbQ()
                    .eq(ModelConfig::getModelType, "EMBEDDING")
            );
            
            if (embeddingModels.isEmpty()) {
                log.warn("未找到可用的嵌入模型配置");
                return null;
            }
            // 返回第一个可用的嵌入模型
            return embeddingModels.get(0);
            
        } catch (Exception e) {
            log.error("获取默认嵌入模型失败", e);
            return null;
        }
    }
}
