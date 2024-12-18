package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Aisle;
import com.wemirr.platform.wms.basic.domain.req.AislePageReq;
import com.wemirr.platform.wms.basic.domain.req.AisleSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.AislePageResp;
import com.wemirr.platform.wms.basic.service.AisleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 巷道 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aisles")
@Tag(name = "巷道管理", description = "储位管理")
public class AisleController {

    private final AisleService aisleService;


    @Operation(summary = "查询可用 - [DONE]", description = "查询可用数据")
    @PostMapping("/ids")
    public List<AislePageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.aisleService.list(Wraps.<Aisle>lbQ().in(Aisle::getId, ids)), AislePageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<AislePageResp> pageList(@RequestBody AislePageReq req) {
        return this.aisleService.page(req.buildPage(), Wraps.<Aisle>lbQ()
                        .like(Aisle::getCode, req.getCode())
                        .like(Aisle::getName, req.getName())
                        .eq(Aisle::getStatus, req.getStatus()))
                .convert(x -> BeanUtil.toBean(x, AislePageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "巷道管理 - 新增巷道")
    @Operation(summary = "新增巷道 - [DONE] - [Levin]")
    public void create(@RequestBody AisleSaveReq req) {
        aisleService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "巷道管理 - 修改巷道")
    @Operation(summary = "修改巷道 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @RequestBody AisleSaveReq req) {
        aisleService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "巷道管理 - 删除巷道")
    @Operation(summary = "删除巷道 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        aisleService.removeById(id);
    }

}




