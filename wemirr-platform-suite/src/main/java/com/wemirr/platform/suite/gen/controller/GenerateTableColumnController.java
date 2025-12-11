package com.wemirr.platform.suite.gen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTableColumnPageResp;
import com.wemirr.platform.suite.gen.service.GenerateTableColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/generate-table-column")
@Tag(name = "代码生成列配置", description = "代码生成列配置")
public class GenerateTableColumnController {

    private final GenerateTableColumnService generateTableColumnService;

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<GenerateTableColumnPageResp> pageList(GenerateTableColumnPageReq req) {
        return generateTableColumnService.pageList(req);
    }

    /**
     * 新增字段
     */
    @Operation(summary = "新增字段")
    @PostMapping("/create")
    public void create(@Validated @RequestBody GenerateTableColumnSaveReq req) {
        //暂不支持
        generateTableColumnService.create(req);
    }

    /**
     * 编辑字段
     */
    @Operation(summary = "编辑字段")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody GenerateTableColumnSaveReq req) {
        generateTableColumnService.modify(id, req);
    }

    /*
     * 批量更新
     */
    @Operation(summary = "批量更新")
    @PutMapping("/batch/modify")
    public void batchModify(@Validated @RequestBody List<GenerateTableColumnSaveReq> req) {
        //暂不支持
        generateTableColumnService.batchModify(req);
    }

    /**
     * 字段详情
     */
    @Operation(summary = "字段详情")
    @GetMapping("/{id}/detail")
    public void detail(@PathVariable("id") Long id) {
        //暂不支持
        //return generateTableColumnService.detail(id);
    }

    /**
     * 删除字段
     */
    @Operation(summary = "删除字段")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        //暂不支持
        // generateTableColumnService.removeTemplate(id);
    }
}
