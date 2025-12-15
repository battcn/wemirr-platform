package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.tms.domain.req.AccidentPageReq;
import com.wemirr.platform.tms.domain.req.AccidentSaveReq;
import com.wemirr.platform.tms.domain.resp.AccidentPageResp;
import com.wemirr.platform.tms.service.AccidentService;
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
@RequestMapping("/accidents")
@Tag(name = "车辆事故", description = "车辆事故")
public class AccidentController {

    private final AccidentService accidentService;
    @PostMapping("/page")
    @Operation(summary = "车辆事故列表信息 - [Levin] - [DONE]")
    public IPage<AccidentPageResp> pageList(@RequestBody AccidentPageReq req) {
        return this.accidentService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "删除车辆事故 - [Levin] - [DONE]")
    public void create(@Validated @RequestBody AccidentSaveReq req) {
        this.accidentService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑车辆事故 - [Levin] - [DONE]")
    public void edit(@PathVariable Long id, @Validated @RequestBody AccidentSaveReq req) {
        this.accidentService.edit(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除车辆事故 - [Levin] - [DONE]")
    public void remove(@PathVariable Long id) {
        this.accidentService.removeById(id);
    }

}
