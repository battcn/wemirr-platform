package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigPageRep;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigSaveReq;
import com.wemirr.platform.ai.service.ModelConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xJh
 * @since 2025/10/31
 **/
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/models-config")
@Tag(name = "AI模型配置", description = "AI模型配置管理")
public class ModelConfigController {

    private final ModelConfigService modelService;

    @Operation(summary = "分页查询模型配置")
    @GetMapping("/page")
    public IPage<ModelConfigPageRep> pageList(ModelConfigPageReq req) {
        return modelService.pageList(req);
    }

    @Operation(summary = "获取模型配置详情")
    @GetMapping("/{id}/detail")
    public ModelConfigDetailRep detail(@PathVariable Long id) {
        return modelService.detail(id);
    }

    @Operation(summary = "新增模型配置")
    @PostMapping
    public void create(@Validated @RequestBody ModelConfigSaveReq req) {
        modelService.create(req);
    }

    @Operation(summary = "修改模型配置")
    @PutMapping("/{id}")
    public void modify(@PathVariable Long id, @Validated @RequestBody ModelConfigSaveReq req) {
        modelService.modify(id, req);
    }

    @Operation(summary = "删除模型配置")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        modelService.remove(id);
    }
}
