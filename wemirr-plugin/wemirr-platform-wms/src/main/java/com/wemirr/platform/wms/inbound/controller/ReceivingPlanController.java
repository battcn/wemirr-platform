package com.wemirr.platform.wms.inbound.controller;

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
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.wms.inbound.domain.entity.ReceivingPlan;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanItemReceivingReq;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanPageReq;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanSaveReq;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanDetailResp;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanItemPageResp;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanPageResp;
import com.wemirr.platform.wms.inbound.service.ReceivingPlanService;
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
 * 收货计划 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inbound/receiving-plans")
@Tag(name = "收货计划", description = "收货计划")
public class ReceivingPlanController {

    private final ReceivingPlanService receivingPlanService;
    private final PDFTemplate pdfTemplate;
    private final AuthenticationContext context;

    @PostMapping("/ids")
    @Operation(summary = "查询可用", description = "查询可用数据")
    public List<ReceivingPlanPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.receivingPlanService.list(Wraps.<ReceivingPlan>lbQ().in(ReceivingPlan::getId, ids)), ReceivingPlanPageResp.class);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<ReceivingPlanPageResp> pageList(@RequestBody ReceivingPlanPageReq req) {
        return this.receivingPlanService.page(req.buildPage(), Wraps.<ReceivingPlan>lbQ()
                .like(ReceivingPlan::getPlanNum, req.getPlanNum())
                .like(ReceivingPlan::getReceivingNoticeNum, req.getReceivingNoticeNum())
                .eq(ReceivingPlan::getWarehouseId, req.getWarehouseId())
                .eq(ReceivingPlan::getDockId, req.getDockId())
                .eq(ReceivingPlan::getSupplierId, req.getSupplierId())
                .eq(ReceivingPlan::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, ReceivingPlanPageResp.class));
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "详情查询 - [DONE] - [Levin]")
    public ReceivingPlanDetailResp detail(@PathVariable("id") Long id) {
        return receivingPlanService.detail(id);
    }


    @GetMapping("/{id}/item_page")
    @Operation(summary = "详情查询 - [DONE] - [Levin]")
    public IPage<ReceivingPlanItemPageResp> itemPageList(@PathVariable("id") Long id, PageRequest request) {
        return receivingPlanService.itemPage(request.buildPage(), id);
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "收货计划管理 - 新增收货计划")
    @Operation(summary = "新增收货计划 - [DONE] - [Levin]")
    @RedisLock(prefix = "receiving-plans:create")
    public void create(@Validated @RequestBody ReceivingPlanSaveReq req) {
        receivingPlanService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "收货计划管理 - 修改收货计划")
    @Operation(summary = "修改收货计划 - [DONE] - [Levin]")
    @RedisLock(prefix = "receiving-plans:modify")
    public void modify(@RedisParam @PathVariable("id") Long id, @Validated @RequestBody ReceivingPlanSaveReq req) {
        receivingPlanService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "收货计划管理 - 删除收货计划")
    @Operation(summary = "删除收货计划 - [DONE] - [Levin]")
    @RedisLock(prefix = "receiving-plans:delete")
    public void delete(@RedisParam @PathVariable Long id) {
        receivingPlanService.removeReceivingPlan(id);
    }


    @PostMapping("/{id}/submit")
    @AccessLog(module = "", description = "收货计划管理 - 提交收货")
    @Operation(summary = "提交收货 - [DONE] - [Levin]")
    public void submit(@PathVariable("id") Long id) {
        receivingPlanService.submit(id);
    }

    @PostMapping("/{id}/bindDock")
    @AccessLog(module = "", description = "收货计划管理 - 绑定月台")
    @Operation(summary = "绑定月台 - [DONE] - [Levin]")
    public void bindDock(@PathVariable("id") Long id, @RequestParam("dockId") Long dockId) {
        receivingPlanService.bindingDock(id, dockId);
    }

    @PostMapping("/{id}/bindContainer")
    @AccessLog(module = "", description = "收货计划管理 - 绑定容器")
    @Operation(summary = "绑定容器 - [DONE] - [Levin]")
    public void bindContainer(@PathVariable("id") Long id, @RequestParam("containerId") Long containerId) {
        receivingPlanService.bindingContainer(id, containerId);
    }

    @PostMapping("/{id}/receiving")
    @AccessLog(module = "", description = "收货计划管理 - 批量收货")
    @Operation(summary = "收货 - [DONE] - [Levin]")
    public void receiving(@PathVariable("id") Long id, @Validated @RequestBody List<ReceivingPlanItemReceivingReq> reqList) {
        receivingPlanService.receiving(id, reqList);
    }

    @PostMapping("/{id}/all_receiving")
    @AccessLog(module = "", description = "收货计划管理 - 全量收货")
    @Operation(summary = "收货 - [DONE] - [Levin]")
    public void allReceiving(@PathVariable("id") Long id) {
        receivingPlanService.allReceiving(id);
    }

    @PostMapping("/{id}/close")
    @AccessLog(module = "", description = "收货计划管理 - 关闭收货计划")
    @Operation(summary = "关闭收货计划 - [DONE] - [Levin]")
    public void close(@PathVariable("id") Long id) {
        receivingPlanService.close(id);
    }

    @PostMapping("/{id}/cancel")
    @AccessLog(module = "", description = "收货计划管理 - 撤回收货计划")
    @Operation(summary = "撤回收货计划 - [DONE] - [Levin]")
    public void cancel(@PathVariable("id") Long id) {
        receivingPlanService.cancel(id);
    }

    @PostMapping("/{id}/export")
    @AccessLog(module = "", description = "收货计划管理 - 导出收货计划")
    @Operation(summary = "导出收货计划")
    public void exportPdf(@PathVariable("id") Long id, HttpServletResponse response) {
        var detail = this.receivingPlanService.detail(id);
        String date = DateUtil.formatDateTime(DateUtil.date());
        Map<String, Object> map = new HashMap<>();
        map.put("detail", detail);
        map.put("currentDate", date);
        pdfTemplate.export(PDFDomain.builder().templateName("pdf/pages/ReceivingPlan.ftl")
                .fileName(detail.getPlanNum()).model(map)
                .watermark(context.nickName()).build(), response);
    }

}
