package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.tms.domain.req.BmsDetailCalcReq;
import com.wemirr.platform.tms.domain.req.SettleDetailPageReq;
import com.wemirr.platform.tms.domain.req.SettleDetailSaveReq;
import com.wemirr.platform.tms.domain.resp.SettleDetailPageResp;
import com.wemirr.platform.tms.service.SettleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/settle/detail")
@Tag(name = "结算模块",description = "结算模块")
public class SettleController {

    private final SettleService settleService;

    @PostMapping("/page")
    @Operation(summary = "费用列表 - [Levin] - [DONE]")
    public IPage<SettleDetailPageResp> pageList(@RequestBody SettleDetailPageReq req) {
        return this.settleService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "创建费用 - [Levin] - [DONE]")
    public SettleDetailPageResp create(@RequestBody SettleDetailSaveReq req) {
        return this.settleService.createFee(req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "取消费用 - [Levin] - [DONE]")
    public SettleDetailPageResp cancel(@PathVariable Long id) {
        return this.settleService.cancelFee(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑费用 - [Levin] - [DONE]")
    public SettleDetailPageResp update(@PathVariable Long id, @RequestBody SettleDetailSaveReq req) {
        return this.settleService.updateFee(id, req);
    }

    @PostMapping("/calc")
    @Operation(summary = "批量计算费用 - [Levin] - [DONE]")
    public List<Long> calc(@RequestBody BmsDetailCalcReq req) {
        return this.settleService.calc(req);
    }
}
