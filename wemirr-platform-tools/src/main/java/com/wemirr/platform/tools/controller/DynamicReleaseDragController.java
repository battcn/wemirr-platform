package com.wemirr.platform.tools.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.entity.ExportExcelReq;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragReq;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQueryDrag;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseLogTrackResp;
import com.wemirr.platform.tools.service.DynamicReleaseDragService;
import com.wemirr.platform.tools.service.DynamicReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/dynamic_release_drag")
@RequiredArgsConstructor
@Tag(name = "代码生成", description = "代码生成")
@Validated
public class DynamicReleaseDragController {

    @Qualifier("mongoDynamicReleaseServiceImpl")
    private final DynamicReleaseService<String> dynamicReleaseService;

    @PostMapping("/{model}/pages")
    public Result<IPage<?>> pages(@PathVariable String model, @RequestBody DynamicReleaseQueryDrag params) {
        log.debug("[查询条件] - {}", params);
        final IPage<?> pages = this.dynamicReleaseService.pages(model, params);
        return Result.success(pages);
    }

    @GetMapping("/{model}/curd_options")
    public Result<DynamicReleaseCurdOptionResp> curdOptions(@PathVariable String model) {
        return Result.success(this.dynamicReleaseService.curdOptions(model));
    }

    @Operation(summary = "修改数据记录")
    @PutMapping("/{model}/{id}")
    public Result<ResponseEntity<Void>> edit(@PathVariable String model, @PathVariable String id, @RequestBody Map<String, Object> body) {
        this.dynamicReleaseService.updateById(model, id, body);
        return Result.success();
    }

    @Operation(summary = "添加数据记录")
    @PostMapping("/{model}")
    public Result<ResponseEntity<Void>> save(@PathVariable String model, @RequestBody Map<String, Object> body) {
        this.dynamicReleaseService.save(model, body);
        return Result.success();
    }

    @Operation(summary = "获取推送日志")
    @GetMapping("/{model}/{id}/log_tracks")
    public Result<List<DynamicReleaseLogTrackResp<String>>> logTrack(@PathVariable String model, @PathVariable String id) {
        return Result.success(this.dynamicReleaseService.logTrack(model, id));
    }

    @Operation(summary = "推送数据")
    @PatchMapping("/{model}/{id}/push_tracks")
    public Result<RequestEntity<Void>> pushLogTrack(@PathVariable String model, @PathVariable String id) {
        this.dynamicReleaseService.pushTrack(model, id);
        return Result.success();
    }

    @DeleteMapping("/{model}/{id}")
    public Result<RequestEntity<Void>> del(@PathVariable String model, @PathVariable String id) {
        this.dynamicReleaseService.deleteById(model, id);
        return Result.success();
    }

    @DeleteMapping("/{model}/batch_delete")
    public Result<RequestEntity<Void>> batchDel(@PathVariable String model, @RequestBody BatchKey<String> batchKey) {
        this.dynamicReleaseService.batchDeleteByKeys(model, batchKey.getIds());
        return Result.success();
    }

    @SneakyThrows
    @PatchMapping(value = "/{model}/export", produces = "application/octet-stream")
    public void export(@PathVariable String model, @RequestBody ExportExcelReq req, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = URLEncoder.encode("运力宽表", "utf-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + filename + ExcelTypeEnum.XLSX.getValue());
        final long millis = System.currentTimeMillis();
        log.info("Excel导出 - 开始时间 - {}", millis);
        dynamicReleaseService.exportExcel(model, req, response);
        // 使空闲的线程回收
        response.flushBuffer();
        log.info("Excel导出 - 结束时间 - {}", (System.currentTimeMillis() - millis));
    }

    private final DynamicReleaseDragService dynamicReleaseDragService;

    @Operation(summary = "添加模板列表")
    @PostMapping
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody DynamicReleaseDragReq req) {
        this.dynamicReleaseDragService.add(req);
        return Result.success();
    }

    @Operation(summary = "修改模板列表")
    @PutMapping("/{id}")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseDragReq req) {
        this.dynamicReleaseDragService.edit(id, req);
        return Result.success();
    }

    @Operation(summary = "查询模板列表")
    @GetMapping
    public Result<Page<DynamicReleaseDrag>> list(PageRequest request) {
        final Page<DynamicReleaseDrag> page = dynamicReleaseDragService.page(request.buildPage());
        return Result.success(page);
    }

    @Operation(summary = "删除模板列表")
    @DeleteMapping("/{id}")
    public Result<Page<DynamicReleaseDrag>> del(@PathVariable Long id) {
        this.dynamicReleaseDragService.removeById(id);
        return Result.success();
    }
}
