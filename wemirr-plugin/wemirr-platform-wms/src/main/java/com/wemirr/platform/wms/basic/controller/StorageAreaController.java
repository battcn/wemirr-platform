package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.StorageArea;
import com.wemirr.platform.wms.basic.domain.req.StorageAreaPageReq;
import com.wemirr.platform.wms.basic.domain.req.StorageAreaSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.StorageAreaPageResp;
import com.wemirr.platform.wms.basic.service.StorageAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 库区 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/storage-areas")
@Tag(name = "库区管理", description = "库区管理")
public class StorageAreaController {

    private final StorageAreaService storageAreaService;

    @Operation(summary = "查询可用 - [DONE]", description = "查询可用数据")
    @PostMapping("/ids")
    public List<StorageAreaPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.storageAreaService.list(Wraps.<StorageArea>lbQ().in(StorageArea::getId, ids)), StorageAreaPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<StorageAreaPageResp> pageList(@RequestBody StorageAreaPageReq req) {
        return storageAreaService.page(req.buildPage(), Wraps.<StorageArea>lbQ()
                .eq(StorageArea::getWarehouseId, req.getWarehouseId())
                .like(StorageArea::getCode, req.getCode())
                .like(StorageArea::getName, req.getName())
                .eq(StorageArea::getType, req.getType())
                .eq(StorageArea::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, StorageAreaPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "库区管理 - 新增库区")
    @Operation(summary = "新增库区 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody StorageAreaSaveReq req) {
        storageAreaService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "库区管理 - 修改库区")
    @Operation(summary = "修改库区 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody StorageAreaSaveReq req) {
        storageAreaService.modify(id, req);
    }


    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "库区管理 - 删除库区")
    @Operation(summary = "删除库区 - [DONE] - [Levin]")
    public void removeById(@PathVariable Long id) {
        storageAreaService.removeById(id);

    }

}




