package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.req.ModelPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelSaveReq;
import com.wemirr.platform.ai.domain.dto.resp.ModelDetailResp;
import com.wemirr.platform.ai.domain.dto.resp.ModelPageResp;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import com.wemirr.platform.ai.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AI 模型配置控制器
 * <p>
 * 管理 AI 模型配置，支持 OpenAI、通义千问、百度文心等模型
 *
 * @author Levin
 * @since 2025-10
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
@Tag(name = "模型配置", description = "AI 模型配置管理")
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/list")
    @Operation(summary = "分页查询", description = "分页查询模型配置列表")
    public List<ModelPageResp> list(String type) {
        var list = modelService.list(Wraps.<ModelEntity>lbQ().eq(ModelEntity::getType, type));
        return BeanUtilPlus.toBeans(list, ModelPageResp.class);
    }


    @PostMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询模型配置列表")
    public IPage<ModelPageResp> page(@RequestBody ModelPageReq req) {
        return modelService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "配置详情", description = "获取模型配置详情")
    public ModelDetailResp detail(@PathVariable Long id) {
        return modelService.detail(id);
    }

    @PostMapping
    @Operation(summary = "新增配置", description = "新增 AI 模型配置")
    public void create(@Validated @RequestBody ModelSaveReq req) {
        modelService.create(req);
    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "修改配置", description = "修改 AI 模型配置")
    public void update(@PathVariable Long id, @Validated @RequestBody ModelSaveReq req) {
        modelService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除配置", description = "删除 AI 模型配置")
    public void delete(@PathVariable Long id) {
        modelService.remove(id);
    }
}
