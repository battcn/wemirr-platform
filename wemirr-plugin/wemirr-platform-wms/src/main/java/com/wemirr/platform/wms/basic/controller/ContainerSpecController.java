package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.ContainerSpec;
import com.wemirr.platform.wms.basic.domain.req.ContainerSpecPageReq;
import com.wemirr.platform.wms.basic.domain.req.ContainerSpecSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.ContainerSpecPageResp;
import com.wemirr.platform.wms.basic.service.ContainerSpecService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 容器规格 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/container-specs")
@Tag(name = "容器规格", description = "容器规格")
public class ContainerSpecController {

    private final ContainerSpecService containerSpecService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<ContainerSpecPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.containerSpecService.list(Wraps.<ContainerSpec>lbQ().in(ContainerSpec::getId, ids)), ContainerSpecPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<ContainerSpecPageResp> pageList(@RequestBody ContainerSpecPageReq req) {
        return this.containerSpecService.page(req.buildPage(), Wraps.<ContainerSpec>lbQ()
                .eq(ContainerSpec::getStatus, req.getStatus())
                .like(ContainerSpec::getName, req.getName())
        ).convert(x -> BeanUtil.toBean(x, ContainerSpecPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "容器规格管理 - 新增容器规格")
    @Operation(summary = "新增容器规格 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody ContainerSpecSaveReq req) {
        containerSpecService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "容器规格管理 - 修改容器规格")
    @Operation(summary = "修改容器规格 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody ContainerSpecSaveReq req) {
        containerSpecService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "容器规格管理 - 删除容器规格")
    @Operation(summary = "删除容器规格 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        containerSpecService.removeById(id);

    }

}




