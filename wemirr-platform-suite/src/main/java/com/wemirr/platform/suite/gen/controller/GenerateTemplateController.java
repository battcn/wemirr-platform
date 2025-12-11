package com.wemirr.platform.suite.gen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplatePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplateDetailResp;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplateListResp;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplatePageResp;
import com.wemirr.platform.suite.gen.service.GenerateTemplateService;
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
@RequestMapping("/generate-template")
@Tag(name = "代码生成模板", description = "代码生成模板")
public class GenerateTemplateController {

    private final GenerateTemplateService generateTemplateService;

    /**
     * 分页查询模板
     */
    @Operation(summary = "分页查询模板")
    @GetMapping("/page")
    public IPage<GenerateTemplatePageResp> pageList(GenerateTemplatePageReq req) {
        return generateTemplateService.pageList(req);
    }

    /**
     * 新增模板
     */
    @Operation(summary = "新增模板")
    @PostMapping("/create")
    public void create(@Validated @RequestBody GenerateTemplateSaveReq req) {
        generateTemplateService.create(req);
    }

    /**
     * 编辑模板
     */
    @Operation(summary = "编辑模板")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody GenerateTemplateSaveReq req) {
        generateTemplateService.modify(id, req);
    }

    /**
     * 模板详情
     */
    @Operation(summary = "模板详情")
    @GetMapping("/{id}/detail")
    public GenerateTemplateDetailResp detail(@PathVariable("id") Long id) {
        return generateTemplateService.detail(id);
    }

    /**
     * 删除模板
     */
    @Operation(summary = "删除模板")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        generateTemplateService.removeTemplate(id);
    }

    /**
     * 查询所有的模板信息
     */
    @Operation(summary = "查询所有的模板信息")
    @GetMapping("/list-all")
    public List<GenerateTemplateListResp> listAll() {
        return generateTemplateService.list().stream().map(
                x -> {
                    GenerateTemplateListResp rep = new GenerateTemplateListResp();
                    rep.setId(x.getId());
                    rep.setName(x.getName());
                    return rep;
                }
        ).toList();
    }
}
