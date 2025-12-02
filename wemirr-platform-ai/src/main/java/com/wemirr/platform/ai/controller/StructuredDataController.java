package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.ai.core.enums.KnowledgeItemType;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeItemResp;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemPageReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemSaveReq;
import com.wemirr.platform.ai.domain.dto.req.StructuredDataSaveReq;
import com.wemirr.platform.ai.service.KnowledgeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 结构化数据控制器
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/structured-data")
@Tag(name = "结构化数据管理")
public class StructuredDataController {

    private final KnowledgeItemService knowledgeItemService;

    @GetMapping("/list")
    @Operation(summary = "查询结构化数据列表")
    public List<KnowledgeItemResp> list(@RequestParam Long kbId,
                                               @RequestParam(required = false) String dataType) {
        List<KnowledgeItemResp> items = knowledgeItemService.listByKbIdAndType(kbId, KnowledgeItemType.STRUCTURED);
        
        // 如果指定了数据类型，进行过滤
//        if (dataType != null && !dataType.isEmpty()) {
//            items = items.stream()
//                    .filter(item -> {
//                        Map<String, Object> content = item.getContent();
//                        return content != null &&
//                               content.containsKey("data_type") &&
//                               dataType.equals(content.get("data_type"));
//                    })
//                    .collect(Collectors.toList());
//        }
        
        return items;
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询结构化数据")
    public IPage<KnowledgeItemResp> page(@RequestParam String kbId, PageRequest req) {
        KnowledgeItemPageReq pageReq = new KnowledgeItemPageReq();
        pageReq.setKbId(kbId);
        pageReq.setType(KnowledgeItemType.STRUCTURED.getCode());
        BeanUtils.copyProperties(req, pageReq);
        return knowledgeItemService.pageList(pageReq);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "查询结构化数据详情")
    public KnowledgeItemResp detail(@PathVariable Long id) {
        return knowledgeItemService.detail(id);
    }

    @PostMapping
    @Operation(summary = "创建结构化数据")
    public void create(@Valid @RequestBody StructuredDataSaveReq req) {
        knowledgeItemService.createStructuredData(
                req.getKbId(), 
                req.getTitle(), 
                Map.of(
                        "data_type", req.getDataType(),
                        "content", req.getContent()
                ),
                req.getMetadata()
        );
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建结构化数据")
    public List<Long> batchCreate(@Valid @RequestBody List<StructuredDataSaveReq> reqs) {
        List<KnowledgeItemSaveReq> itemReqs = reqs.stream()
                .map(req -> {
                    KnowledgeItemSaveReq itemReq = new KnowledgeItemSaveReq();
                    itemReq.setKbId(req.getKbId());
                    itemReq.setTenantId(req.getTenantId());
                    itemReq.setType(KnowledgeItemType.STRUCTURED.getCode());
                    itemReq.setContent(Map.of(
                            "title", req.getTitle(),
                            "data_type", req.getDataType(),
                            "data", req.getContent()
                    ));
                    itemReq.setMetadata(req.getMetadata());
                    return itemReq;
                })
                .collect(Collectors.toList());
        
        return knowledgeItemService.batchCreate(itemReqs);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新结构化数据")
    public void update(@PathVariable Long id, @Valid @RequestBody StructuredDataSaveReq req) {
        KnowledgeItemSaveReq itemReq = new KnowledgeItemSaveReq();
        itemReq.setKbId(req.getKbId());
        itemReq.setTenantId(req.getTenantId());
        itemReq.setType(KnowledgeItemType.STRUCTURED.getCode());
        itemReq.setContent(Map.of(
                "title", req.getTitle(),
                "data_type", req.getDataType(),
                "data", req.getContent()
        ));
        itemReq.setMetadata(req.getMetadata());
        
        knowledgeItemService.update(id, itemReq);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除结构化数据")
    public void delete(@PathVariable Long id) {
        knowledgeItemService.delete(id);
    }
}