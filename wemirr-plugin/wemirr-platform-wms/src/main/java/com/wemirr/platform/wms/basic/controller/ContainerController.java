package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Container;
import com.wemirr.platform.wms.basic.domain.req.ContainerPageReq;
import com.wemirr.platform.wms.basic.domain.req.ContainerSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.ContainerPageResp;
import com.wemirr.platform.wms.basic.service.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 容器 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/containers")
@Tag(name = "容器管理", description = "容器管理")
public class ContainerController {

    private final ContainerService containerService;

    @Operation(summary = "查询可用 - [DONE]", description = "查询可用数据")
    @PostMapping("/ids")
    public List<ContainerPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.containerService.list(Wraps.<Container>lbQ().in(Container::getId, ids)), ContainerPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<ContainerPageResp> pageList(@RequestBody ContainerPageReq req) {
        return this.containerService.page(req.buildPage(), Wraps.<Container>lbQ()
                .like(Container::getCode, req.getCode())
                .like(Container::getName, req.getName())
                .eq(Container::getSpecId, req.getSpecId())
        ).convert(x -> BeanUtil.toBean(x, ContainerPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "容器管理 - 新增容器")
    @Operation(summary = "新增容器 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody ContainerSaveReq req) {
        containerService.create(req);

    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "容器管理 - 修改容器")
    @Operation(summary = "修改容器 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody ContainerSaveReq req) {
        containerService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "容器管理 - 删除容器")
    @Operation(summary = "删除容器 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        containerService.removeById(id);

    }

}




