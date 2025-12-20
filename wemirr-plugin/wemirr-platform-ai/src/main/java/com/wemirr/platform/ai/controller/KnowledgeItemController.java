package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeItemResp;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemPageReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemSaveReq;
import com.wemirr.platform.ai.service.KnowledgeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识条目通用控制器
 * 统一管理 DOCUMENT / QA_PAIR / STRUCTURED / TEXT_SNIPPET 等类型接口
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
@Tag(name = "知识条目管理")
public class KnowledgeItemController {

    private final KnowledgeItemService knowledgeItemService;

    @GetMapping("/page")
    @Operation(summary = "分页查询知识条目（可按类型过滤）")
    public IPage<KnowledgeItemResp> page(@RequestParam KnowledgeItemPageReq pageReq) {
        return knowledgeItemService.pageList(pageReq);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "查询知识条目详情")
    public KnowledgeItemResp detail(@PathVariable Long id) {
        return knowledgeItemService.detail(id);
    }

    @PostMapping
    @Operation(summary = "创建知识条目（根据type分发）")
    public Long create(@Valid @RequestBody KnowledgeItemSaveReq req) {
        return knowledgeItemService.create(req);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建知识条目")
    public List<Long> batchCreate(@Valid @RequestBody List<KnowledgeItemSaveReq> reqs) {
        return knowledgeItemService.batchCreate(reqs);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新知识条目")
    public void update(@PathVariable Long id, @Valid @RequestBody KnowledgeItemSaveReq req) {
        knowledgeItemService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除知识条目")
    public void delete(@PathVariable Long id) {
        knowledgeItemService.delete(id);
    }
}


