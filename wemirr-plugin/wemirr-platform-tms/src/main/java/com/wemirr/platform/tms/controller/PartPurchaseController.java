package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.tms.domain.req.PartPurchasePageReq;
import com.wemirr.platform.tms.domain.req.PartPurchaseSaveReq;
import com.wemirr.platform.tms.domain.resp.PartPurchasePageResp;
import com.wemirr.platform.tms.service.PartPurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/part_purchases")
@Tag(name = "配件采购", description = "配件采购")
public class PartPurchaseController {



    private final PartPurchaseService partPurchaseService;
    @PostMapping("/page")
    @Operation(summary = "配件采购列表信息 - [Levin] - [DONE]")
    public IPage<PartPurchasePageResp> pageList(@RequestBody PartPurchasePageReq req) {
        return this.partPurchaseService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "删除配件采购 - [Levin] - [DONE]")
    public void create(@Validated @RequestBody PartPurchaseSaveReq req) {
        this.partPurchaseService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑配件采购 - [Levin] - [DONE]")
    public void edit(@PathVariable Long id, @Validated @RequestBody PartPurchaseSaveReq req) {
        this.partPurchaseService.edit(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除配件采购 - [Levin] - [DONE]")
    public void remove(@PathVariable Long id) {
        this.partPurchaseService.removeById(id);
    }




}
