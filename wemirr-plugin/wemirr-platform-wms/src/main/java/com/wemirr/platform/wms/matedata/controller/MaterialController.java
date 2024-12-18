package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Material;
import com.wemirr.platform.wms.matedata.domain.req.MaterialPageReq;
import com.wemirr.platform.wms.matedata.domain.req.MaterialSaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.MaterialPageResp;
import com.wemirr.platform.wms.matedata.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 物料 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/materials")
@Tag(name = "物料管理", description = "物料管理")
public class MaterialController {

    private final MaterialService materialService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<MaterialPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.materialService.list(Wraps.<Material>lbQ().in(Material::getId, ids)), MaterialPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<MaterialPageResp> pageList(@RequestBody MaterialPageReq req) {
        return this.materialService.page(req.buildPage(), Wraps.<Material>lbQ()
                .like(Material::getCode, req.getCode())
                .like(Material::getName, req.getName())
                .eq(Material::getCategoryId, req.getTypeId())
                .eq(Material::getBrandId, req.getBrandId())
                .eq(Material::getBarCode, req.getBarCode())
                .like(Material::getSpec, req.getSpec())
                .like(Material::getModel, req.getModel())
                .eq(Material::getUnit, req.getUnit())
        ).convert(x -> BeanUtil.toBean(x, MaterialPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "物料管理 - 新增物料")
    @Operation(summary = "新增物料 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody MaterialSaveReq req) {
        materialService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "物料管理 - 修改物料")
    @Operation(summary = "修改物料 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @RequestBody MaterialSaveReq req) {
        materialService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "物料管理 - 删除物料")
    @Operation(summary = "删除物料 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        materialService.removeById(id);
    }

}




