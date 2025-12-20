package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.ai.domain.dto.rep.VectorizationRep;
import com.wemirr.platform.ai.domain.dto.req.VectorizationTaskPageReq;
import com.wemirr.platform.ai.domain.entity.VectorizationTask;
import com.wemirr.platform.ai.service.VectorService;
import com.wemirr.platform.ai.service.VectorService.VectorizationTaskStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 向量化控制器
 * 提供对知识库内容进行向量化的接口
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "向量化管理")
@RequestMapping("/vectorization")
public class VectorizationController {

    private final VectorService vectorService;


    @PostMapping("/page")
    @Operation(summary = "分页查询向量化任务")
    public IPage<VectorizationTask> page(@RequestBody VectorizationTaskPageReq params) {
        return vectorService.pageList(params);
    }

    @PostMapping("/knowledge-item/{itemId}")
    @Operation(summary = "对知识条目进行向量化")
    public void vectorizeKnowledgeItem(@PathVariable Long itemId) {
        vectorService.vectorizeKnowledgeItem(itemId);
    }

    @PostMapping("/knowledge-items")
    @Operation(summary = "批量对知识条目进行向量化")
    public String vectorizeKnowledgeItems(@RequestBody List<Long> itemIds) {
        return vectorService.vectorizeKnowledgeItems(itemIds);
    }

    @PostMapping("/document/{docId}")
    @Operation(summary = "对文档进行向量化")
    public String vectorizeDocument(@PathVariable Long docId) {
        return vectorService.vectorizeDocument(docId);
    }

    @PostMapping("/faq/{faqId}")
    @Operation(summary = "对FAQ进行向量化")
    public String vectorizeFAQ(@PathVariable Long faqId) {
        return vectorService.vectorizeFAQ(faqId);
    }

    @PostMapping("/structured-data/{dataId}")
    @Operation(summary = "对结构化数据进行向量化")
    public String vectorizeStructuredData(@PathVariable Long dataId) {
        return vectorService.vectorizeStructuredData(dataId);
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "查询向量化任务状态")
    public VectorizationTaskStatus getTaskStatus(@PathVariable String taskId) {
        return vectorService.getTaskStatus(taskId);
    }

    @Operation(summary = "查询文档向量化状态")
    @GetMapping("/{itemId}/vectorize-status")
    public VectorizationRep getVectorizeStatus(@PathVariable Long itemId) {

        return vectorService.getVectorizeStatus(itemId);
    }

    @DeleteMapping("/vector/{baseItemId}")
    @Operation(summary = "根据知识条目删除向量")
    public boolean deleteVector(@PathVariable Long baseItemId) {
        return vectorService.deleteVector(baseItemId);
    }

    @DeleteMapping("/vectors")
    @Operation(summary = "按元数据删除向量")
    public int deleteVectorsByMetadata(@RequestParam String key, @RequestParam String value) {
        return vectorService.deleteVectorsByMetadata(key, value);
    }
}
