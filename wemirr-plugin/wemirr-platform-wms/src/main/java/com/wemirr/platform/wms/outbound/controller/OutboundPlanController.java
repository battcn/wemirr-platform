package com.wemirr.platform.wms.outbound.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.pdf.configuration.PDFTemplate;
import com.wemirr.framework.pdf.domain.PDFDomain;
import com.wemirr.platform.wms.outbound.domain.entity.OutboundPlan;
import com.wemirr.platform.wms.outbound.domain.req.OutboundPlanPageReq;
import com.wemirr.platform.wms.outbound.domain.req.OutboundPlanSaveReq;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanDetailResp;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanItemPageResp;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanPageResp;
import com.wemirr.platform.wms.outbound.service.OutboundPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 出库计划 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-07-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/outbound-plans")
@Tag(name = "出库计划", description = "出库计划")
public class OutboundPlanController {

    private final OutboundPlanService outboundPlanService;
    private final PDFTemplate pdfTemplate;
    private final AuthenticationContext context;

    @PostMapping("/ids")
    @Operation(summary = "查询可用", description = "查询可用数据")
    public List<OutboundPlanPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.outboundPlanService.list(Wraps.<OutboundPlan>lbQ().in(OutboundPlan::getId, ids)), OutboundPlanPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<OutboundPlanPageResp> pageList(@RequestBody OutboundPlanPageReq req) {
        return this.outboundPlanService.page(req.buildPage(), Wraps.<OutboundPlan>lbQ()
                .like(OutboundPlan::getPlanNum, req.getPlanNum())
                .like(OutboundPlan::getCustomerOrderNum, req.getCustomerOrderNum())
                .eq(OutboundPlan::getWarehouseId, req.getWarehouseId())
                .eq(OutboundPlan::getDockId, req.getDockId())
                .eq(OutboundPlan::getSupplierId, req.getSupplierId())
                .eq(OutboundPlan::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, OutboundPlanPageResp.class));
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "详情查询 - [DONE] - [Levin]")
    public OutboundPlanDetailResp detail(@PathVariable("id") Long id) {
        return outboundPlanService.detail(id);
    }

    @GetMapping("/{id}/item_page")
    @Operation(summary = "行项详情查询 - [DONE] - [Levin]")
    public IPage<OutboundPlanItemPageResp> itemPageList(@PathVariable("id") Long id, PageRequest request) {
        return outboundPlanService.itemPage(request.buildPage(), id);
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "出库计划管理 - 新增出库计划")
    @Operation(summary = "新增出库计划 - [DONE] - [Levin]")
    public void save(@RequestBody OutboundPlanSaveReq req) {
        outboundPlanService.saveOrUpdateOutboundPlan(null, req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "出库计划管理 - 修改出库计划")
    @Operation(summary = "修改出库计划 - [DONE] - [Levin]")
    public void updateById(@PathVariable("id") Long id, @Validated @RequestBody OutboundPlanSaveReq req) {
        outboundPlanService.saveOrUpdateOutboundPlan(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "出库计划管理 - 删除出库计划")
    @Operation(summary = "删除出库计划 - [DONE] - [Levin]")
    public void removeById(@PathVariable Long id) {
        outboundPlanService.removeOutboundPlan(id);
    }


    @PostMapping("/{id}/submit")
    @AccessLog(module = "", description = "出库计划管理 - 提交出库")
    @Operation(summary = "提交出库 - [DONE] - [Levin]")
    public void submit(@PathVariable("id") Long id) {
        outboundPlanService.submit(id);
    }

    @PostMapping("/{id}/confirm")
    @AccessLog(module = "", description = "出库计划管理 - 完成出库")
    @Operation(summary = "完成出库 - [DONE] - [Levin]")
    public void confirm(@PathVariable("id") Long id) {
        outboundPlanService.confirm(id);
    }

    @PostMapping("/{id}/close")
    @AccessLog(module = "", description = "出库计划管理 - 关闭出库计划")
    @Operation(summary = "关闭出库计划 - [DONE] - [Levin]")
    public void close(@PathVariable("id") Long id) {
        outboundPlanService.close(id);
    }

    @PostMapping("/{id}/cancel")
    @AccessLog(module = "", description = "出库计划管理 - 撤回出库计划")
    @Operation(summary = "撤回出库计划 - [DONE] - [Levin]")
    public void cancel(@PathVariable("id") Long id) {
        outboundPlanService.cancel(id);
    }

    @PostMapping("/{id}/export")
    @AccessLog(module = "", description = "出库计划管理 - 导出出库计划")
    @Operation(summary = "导出出库计划")
    public void exportPdf(@PathVariable("id") Long id, HttpServletResponse response) {
        var detail = this.outboundPlanService.detail(id);
        String date = DateUtil.formatDateTime(DateUtil.date());
        Map<String, Object> map = new HashMap<>();
        map.put("detail", detail);
        map.put("currentDate", date);
        pdfTemplate.export(PDFDomain.builder().templateName("pdf/pages/OutboundPlan.ftl")
                .fileName(detail.getPlanNum()).model(map)
                .watermark(context.nickName()).build(), response);
    }

}
