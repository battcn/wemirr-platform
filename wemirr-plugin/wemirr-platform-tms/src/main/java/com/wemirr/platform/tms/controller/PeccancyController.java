package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.tms.domain.req.PeccancyPageReq;
import com.wemirr.platform.tms.domain.req.PeccancySaveReq;
import com.wemirr.platform.tms.domain.resp.PeccancyPageResp;
import com.wemirr.platform.tms.service.PeccancyService;
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
@RequestMapping("/peccancy")
@Tag(name = "车辆违章", description = "车辆违章")
public class PeccancyController {

    private final PeccancyService peccancyService;
    @PostMapping("/page")
    @Operation(summary = "车辆违章列表信息 - [Levin] - [DONE]")
    public IPage<PeccancyPageResp> pageList(@RequestBody PeccancyPageReq req) {
        return this.peccancyService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "删除车辆违章 - [Levin] - [DONE]")
    public void create(@Validated @RequestBody PeccancySaveReq req) {
        this.peccancyService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑车辆违章 - [Levin] - [DONE]")
    public void edit(@PathVariable Long id, @Validated @RequestBody PeccancySaveReq req) {
        this.peccancyService.edit(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除车辆违章 - [Levin] - [DONE]")
    public void remove(@PathVariable Long id) {
        this.peccancyService.removeById(id);
    }

}
