package com.wemirr.platform.ai.controller;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.ai.domain.dto.rep.EmbeddingMatchRep;
import com.wemirr.platform.ai.service.KnowledgeSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 知识搜索控制器
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/knowledge-search")
@Tag(name = "知识搜索")
public class KnowledgeSearchController {

    private final KnowledgeSearchService knowledgeSearchService;

    @GetMapping("/semantic-search")
    @Operation(summary = "语义搜索，返回topK")
    public List<EmbeddingMatchRep> semanticSearch(@RequestParam Long kbId, @RequestParam String query,
                                                  @RequestParam(defaultValue = "5") int topK) {
        try {
            return knowledgeSearchService.semanticSearch(kbId, query, topK);
        } catch (Exception e) {
            log.error("语义搜索失败: kbId={}, query={}", kbId, query, e);
            return null;
        }
    }

    @GetMapping("/hybrid-search")
    @Operation(summary = "混合搜索（语义+关键词）")
    public Result<List<Map<String, Object>>> hybridSearch(@RequestParam Long kbId,
                                                          @RequestParam String query,
                                                          @RequestParam(defaultValue = "5") int topK) {
        try {
            List<Map<String, Object>> results = knowledgeSearchService.hybridSearch(kbId, query, topK);
            return Result.success(results);
        } catch (Exception e) {
            log.error("混合搜索失败: kbId={}, query={}", kbId, query, e);
            return Result.fail("搜索失败: " + e.getMessage());
        }
    }

    @GetMapping("/keyword-search")
    @Operation(summary = "关键词搜索")
    public Result<List<Map<String, Object>>> keywordSearch(@RequestParam Long kbId,
                                                           @RequestParam String query,
                                                           @RequestParam(defaultValue = "5") int topK) {
        try {
            List<Map<String, Object>> results = knowledgeSearchService.keywordSearch(kbId, query, topK);
            return Result.success(results);
        } catch (Exception e) {
            log.error("关键词搜索失败: kbId={}, query={}", kbId, query, e);
            return Result.fail("搜索失败: " + e.getMessage());
        }
    }
}