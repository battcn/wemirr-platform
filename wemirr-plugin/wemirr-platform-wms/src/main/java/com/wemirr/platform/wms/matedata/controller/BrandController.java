package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Brand;
import com.wemirr.platform.wms.matedata.domain.req.BrandPageReq;
import com.wemirr.platform.wms.matedata.domain.req.BrandSaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.BrandPageResp;
import com.wemirr.platform.wms.matedata.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/brands")
@Tag(name = "品牌管理", description = "品牌管理")
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<BrandPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.brandService.list(Wraps.<Brand>lbQ().in(Brand::getId, ids)), BrandPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<BrandPageResp> pageList(@RequestBody BrandPageReq req) {
        return this.brandService.page(req.buildPage(), Wraps.<Brand>lbQ()
                        .like(Brand::getCode, req.getCode())
                        .like(Brand::getName, req.getName())
                        .like(Brand::getStatus, req.getStatus()))
                .convert(x -> BeanUtil.toBean(x, BrandPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "品牌管理 - 新增品牌")
    @Operation(summary = "新增品牌 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody BrandSaveReq req) {
        brandService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "品牌管理 - 修改品牌")
    @Operation(summary = "修改品牌 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody BrandSaveReq req) {
        brandService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "品牌管理 - 删除品牌")
    @Operation(summary = "删除品牌 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        brandService.removeById(id);
    }

}




