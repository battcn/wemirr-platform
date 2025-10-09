package com.wemirr.platform.suite.gen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplateGroupListRep;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplateGroupPageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.wemirr.platform.suite.gen.service.GenerateTemplateGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/gennerate-template-group")
@Tag(name = "代码生成模板组", description = "代码生成模板组")
public class GenerateTemplateGroupController {
    private final GenerateTemplateGroupService generateTemplateGroupService;

    /**
     * 分页查询模板
     */
    @Operation(summary = "分页查询分组")
    @GetMapping("/page")
    public IPage<GenerateTemplateGroupPageRep> pageList(GenerateTemplateGroupPageReq req) {
        return generateTemplateGroupService.pageList(req);
    }

    /**
     * 新增模板
     */
    @Operation(summary = "新增分组")
    @PostMapping("/create")
    public void create(@Validated @RequestBody GenerateTemplateGroupSaveReq req) {
        generateTemplateGroupService.create(req);
    }

    /**
     * 编辑模板
     */
    @Operation(summary = "编辑分组")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody GenerateTemplateGroupSaveReq req) {
        generateTemplateGroupService.modify(id, req);
    }

    /**
     * 删除模板
     */
    @Operation(summary = "删除分组")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        generateTemplateGroupService.removeGroup(id);
    }

    /**
     * 获取所有的模板分组
     */
    @Operation(summary = "获取所有的模板分组")
    @GetMapping("/list-all")
    public List<GenerateTemplateGroupListRep> listAll() {
        return generateTemplateGroupService.list().stream().map(
                x -> {
                    GenerateTemplateGroupListRep rep = new GenerateTemplateGroupListRep();
                    rep.setId(x.getId());
                    rep.setName(x.getName());
                    return rep;
                }
        ).toList();
    }

}
