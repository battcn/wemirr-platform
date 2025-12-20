package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigPageRep;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigSaveReq;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.ModelConfigService;
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
@RequestMapping("/model-configs")
@Tag(name = "模型配置", description = "AI 模型配置管理")
public class ModelConfigController {

    private final ModelConfigService modelService;

    @GetMapping("/list")
    @Operation(summary = "分页查询", description = "分页查询模型配置列表")
    public List<ModelConfigPageRep> list(String type) {
        var list = modelService.list(Wraps.<ModelConfig>lbQ().eq(ModelConfig::getModelType, type));
        return BeanUtilPlus.toBeans(list, ModelConfigPageRep.class);
    }


    @PostMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询模型配置列表")
    public IPage<ModelConfigPageRep> page(@RequestBody ModelConfigPageReq req) {
        return modelService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "配置详情", description = "获取模型配置详情")
    public ModelConfigDetailRep detail(@PathVariable Long id) {
        return modelService.detail(id);
    }

    @PostMapping
    @Operation(summary = "新增配置", description = "新增 AI 模型配置")
    public void create(@Validated @RequestBody ModelConfigSaveReq req) {
        modelService.create(req);
    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "修改配置", description = "修改 AI 模型配置")
    public void update(@PathVariable Long id, @Validated @RequestBody ModelConfigSaveReq req) {
        modelService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除配置", description = "删除 AI 模型配置")
    public void delete(@PathVariable Long id) {
        modelService.remove(id);
    }
}
