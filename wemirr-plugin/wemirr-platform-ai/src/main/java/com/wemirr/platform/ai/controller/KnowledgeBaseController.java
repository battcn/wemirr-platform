package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeBaseResp;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeBasePageReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeBaseSaveReq;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 知识库控制器
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "知识库管理")
@RequestMapping("/knowledge-bases")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    @GetMapping("/page")
    @Operation(summary = "分页查询知识库")
    public IPage<KnowledgeBaseResp> page(@Valid KnowledgeBasePageReq req) {
        return knowledgeBaseService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "查询知识库详情")
    public KnowledgeBaseResp detail(@PathVariable Long id) {
        return knowledgeBaseService.detail(id);
    }

    @PostMapping
    @Operation(summary = "创建知识库")
    public void create(@Valid @RequestBody KnowledgeBaseSaveReq req) {
        knowledgeBaseService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新知识库")
    public void modify(@PathVariable Long id, @Valid @RequestBody KnowledgeBaseSaveReq req) {
        knowledgeBaseService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除知识库")
    public void delete(@PathVariable Long id) {
        knowledgeBaseService.delete(id);
    }
}
