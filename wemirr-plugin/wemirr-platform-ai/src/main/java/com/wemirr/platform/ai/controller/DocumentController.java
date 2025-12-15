package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.ai.core.enums.KnowledgeItemType;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeItemResp;
import com.wemirr.platform.ai.domain.dto.rep.PreviewChunkResp;
import com.wemirr.platform.ai.domain.dto.req.DocumentSaveReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemPageReq;
import com.wemirr.platform.ai.service.KnowledgeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文档控制器
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rag-documents")
@Tag(name = "文档管理", description = "待改用 /item 通用接口。此控制器作为兼容层保留。")
public class DocumentController {

    private final KnowledgeItemService knowledgeItemService;

    @GetMapping("/{kbId}/page")
    @Operation(summary = "分页查询文档")
    public IPage<KnowledgeItemResp> page(@PathVariable String kbId, PageRequest req) {
        KnowledgeItemPageReq pageReq = new KnowledgeItemPageReq();
        pageReq.setKbId(kbId);
        pageReq.setType(KnowledgeItemType.DOCUMENT.getCode());
        BeanUtils.copyProperties(req, pageReq);
        return knowledgeItemService.pageList(pageReq);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "查询文档详情")
    public KnowledgeItemResp detail(@PathVariable Long id) {
        return knowledgeItemService.detail(id);
    }

    @PostMapping
    @Operation(summary = "创建文档")
    public void create(@Valid @RequestBody DocumentSaveReq req) {
        knowledgeItemService.createDocument(req);
    }

    //todo 更新文档内容时候，需要删除原始分片以及向量
    @PutMapping("/{id}")
    @Operation(summary = "更新文档")
    public void update(@PathVariable Long id, @Valid @RequestBody DocumentSaveReq req) {
        knowledgeItemService.updateDocument(
                id,
                req.getTitle(),
                req.getContent(),
                req.getContentType(),
                req.getFilePath(),
                req.getFileSize(),
                req.getMetadata()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除文档")
    public void delete(@PathVariable Long id) {
        knowledgeItemService.delete(id);
    }
    
    @PostMapping("/upload")
    @Operation(summary = "上传文档")
    public Long upload(@RequestParam Long kbId, @RequestParam("file") MultipartFile file) throws IOException {
        return knowledgeItemService.uploadAndProcess(kbId, file);
    }

    @PostMapping("/preview/{itemId}")
    @Operation(summary = "文档预览")
    public String preview(@PathVariable Long itemId){
        return knowledgeItemService.preview(itemId);
    }

    @PostMapping("/preview-chunk/{itemId}/")
    @Operation(summary = "文档分块预览")
    public List<PreviewChunkResp> previewChunk(@PathVariable Long itemId){
        return knowledgeItemService.previewChunk(itemId);
    }

    @PostMapping("/bulk-update-chunks")
    @Operation(summary ="批量更新文档分块;要求先删除文档的向量化后指向" )
    public void bulkUpdateChunks(@RequestBody List<Long> chunkIds){
        knowledgeItemService.bulkUpdateChunks(chunkIds);
    }
    
    @PostMapping("/{id}/reprocess")
    @Operation(summary = "重新处理文档")
    public void reprocess(@PathVariable Long id) throws IOException {
        knowledgeItemService.reprocess(id);
    }
}
