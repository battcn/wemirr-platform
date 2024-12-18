package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Workbench;
import com.wemirr.platform.wms.basic.domain.req.WorkbenchPageReq;
import com.wemirr.platform.wms.basic.domain.req.WorkbenchSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.WorkbenchPageResp;
import com.wemirr.platform.wms.basic.service.WorkbenchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 工作台 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/workbench")
@Tag(name = "工作台管理", description = "工作台管理")
public class WorkbenchController {

    private final WorkbenchService workbenchService;

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<WorkbenchPageResp> pageList(@RequestBody WorkbenchPageReq req) {
        return this.workbenchService.page(req.buildPage(),
                        Wraps.<Workbench>lbQ()
                                .eq(Workbench::getStatus, req.getStatus())
                                .like(Workbench::getCode, req.getCode())
                                .like(Workbench::getName, req.getName()))
                .convert(x -> BeanUtil.toBean(x, WorkbenchPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "工作台管理 - 新增工作台")
    @Operation(summary = "新增工作台 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody WorkbenchSaveReq req) {
        workbenchService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "工作台管理 - 修改工作台")
    @Operation(summary = "修改工作台 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody WorkbenchSaveReq req) {
        workbenchService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "工作台管理 - 删除工作台")
    @Operation(summary = "删除工作台 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        workbenchService.removeById(id);

    }

}




