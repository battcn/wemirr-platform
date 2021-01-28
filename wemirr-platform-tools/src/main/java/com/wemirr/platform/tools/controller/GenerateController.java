package com.wemirr.platform.tools.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.tools.domain.entity.GenerateRequest;
import com.wemirr.platform.tools.domain.req.GenerateReq;
import com.wemirr.platform.tools.service.GenerateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/generates")
@RequiredArgsConstructor
@Tag(name = "代码生成", description = "代码生成")
@Validated
@Deprecated
public class GenerateController {

    private final GenerateService generateService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Result<Page<GenerateRequest>> page(PageRequest pageRequest) {
        final Page<GenerateRequest> page = generateService.page(pageRequest.buildPage());
        return Result.success(page);
    }


    @Operation(summary = "代码生成")
    @PatchMapping("/{id}/download")
    public void add(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final GenerateRequest generateRequest = this.generateService.getById(id);
        final String path = generateService.generate(generateRequest);
        final File file = ZipUtil.zip(path);
        if (!file.exists()) {
            throw CheckedException.badRequest("文件不存在");
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            log.error("[文件下载失败]", e);
            throw CheckedException.badRequest("文件下载失败");
        } finally {
            try {
                Files.deleteIfExists(Paths.get(file.getPath()));
                log.info("[删除成功] - [目录: {}]", file.getPath());
            } catch (IOException e) {
                log.warn("[文件删除失败] - [{}]", e.getLocalizedMessage());
            }
        }
    }

    @Operation(summary = "添加代码生成")
    @PostMapping
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody GenerateReq req) {
        generateService.save(BeanUtil.toBean(req, GenerateRequest.class));
        return Result.success();
    }


    @Operation(summary = "编辑代码生成")
    @PutMapping("/{id}")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody GenerateReq req) {
        final GenerateRequest request = BeanUtil.toBean(req, GenerateRequest.class);
        request.setId(id);
        generateService.updateById(request);
        return Result.success();
    }

    @Operation(summary = "删除代码生成")
    @DeleteMapping("/{id}")
    public Result<ResponseEntity<Void>> remove(@PathVariable Long id) {
        generateService.removeById(id);
        return Result.success();
    }
}
