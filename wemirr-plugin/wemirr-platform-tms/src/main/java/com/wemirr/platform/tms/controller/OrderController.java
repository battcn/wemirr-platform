package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.tms.domain.req.OrderPageReq;
import com.wemirr.platform.tms.domain.req.OrderSaveReq;
import com.wemirr.platform.tms.domain.resp.OrderDetailResp;
import com.wemirr.platform.tms.domain.resp.OrderPageResp;
import com.wemirr.platform.tms.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "订单模块", description = "订单模块")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/page")
    @Operation(summary = "订单列表 - [Levin] - [DONE]")
    public IPage<OrderPageResp> pageList(@RequestBody OrderPageReq req) {
        return this.orderService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "订单信息 - [Levin] - [DONE]")
    public OrderDetailResp detail(@PathVariable Long id) {
        return this.orderService.detail(id);
    }

    @PostMapping("/create")
    @Operation(summary = "创建订单 - [Levin] - [DONE]")
    @AccessLog(module = "", description = "创建订单")
    public void create(@Validated @RequestBody OrderSaveReq req) {
        this.orderService.createOrder(req);
    }

    @DeleteMapping("/{id}/cancel")
    @Operation(summary = "取消订单 - [Levin] - [DONE]")
    @AccessLog(module = "", description = "取消订单")
    public void cancel(@PathVariable Long id) {
        this.orderService.cancelOrder(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除订单 - [Levin] - [DONE]")
    @AccessLog(module = "", description = "删除订单")
    public void delete(@PathVariable Long id) {
        this.orderService.removeById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑订单 - [Levin] - [DONE]")
    @AccessLog(module = "", description = "编辑订单")
    public void update(@Validated @PathVariable Long id, @RequestBody OrderSaveReq req) {
        this.orderService.updateOrder(id, req);
    }
}
