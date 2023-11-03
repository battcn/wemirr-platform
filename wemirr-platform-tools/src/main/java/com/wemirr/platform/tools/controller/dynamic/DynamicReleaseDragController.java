package com.wemirr.platform.tools.controller.dynamic;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
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
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final DynamicReleaseService<Long> dynamicReleaseService;

    @PostMapping("/{model}/pages")
    public IPage<?> pages(@PathVariable String model, @RequestBody DynamicReleaseQueryDrag params) {
        log.debug("[查询条件] - {}", params);
        return this.dynamicReleaseService.pages(model, params);
    }

    @GetMapping("/{model}/curd_options")
    public DynamicReleaseCurdOptionResp curdOptions(@PathVariable String model) {
        return this.dynamicReleaseService.curdOptions(model);
    }

    @Operation(summary = "修改数据记录")
    @PutMapping("/{model}/{id}")
    public void edit(@PathVariable String model, @PathVariable Long id, @RequestBody Map<String, Object> body) {
        this.dynamicReleaseService.updateById(model, id, body);
    }

    @Operation(summary = "添加数据记录")
    @PostMapping("/{model}")
    public void save(@PathVariable String model, @RequestBody Map<String, Object> body) {
        this.dynamicReleaseService.save(model, body);
    }

    @Operation(summary = "获取推送日志")
    @GetMapping("/{model}/{id}/log_tracks")
    public List<DynamicReleaseLogTrackResp<Long>> logTrack(@PathVariable String model, @PathVariable Long id) {
        return this.dynamicReleaseService.logTrack(model, id);
    }

    @Operation(summary = "推送数据")
    @PatchMapping("/{model}/{id}/push_tracks")
    public void pushLogTrack(@PathVariable String model, @PathVariable Long id) {
        this.dynamicReleaseService.pushTrack(model, id);
    }

    @DeleteMapping("/{model}/{id}")
    public void del(@PathVariable String model, @PathVariable Long id) {
        this.dynamicReleaseService.deleteById(model, id);
    }

    @DeleteMapping("/{model}/batch_delete")
    public void batchDel(@PathVariable String model, @RequestBody BatchKey<Long> batchKey) {
        this.dynamicReleaseService.batchDeleteByKeys(model, batchKey.getIds());
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
    public void add(@Validated @RequestBody DynamicReleaseDragReq req) {
        this.dynamicReleaseDragService.add(req);
    }

    @Operation(summary = "修改模板列表")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseDragReq req) {
        this.dynamicReleaseDragService.edit(id, req);
    }

    @Operation(summary = "查询模板列表")
    @GetMapping
    public Page<DynamicReleaseDrag> list(PageRequest request) {
        return dynamicReleaseDragService.page(request.buildPage());
    }

    @Operation(summary = "删除模板列表")
    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id) {
        this.dynamicReleaseDragService.removeById(id);
    }
}

