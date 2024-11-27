package com.wemirr.platform.demo.controller;

import com.wemirr.framework.excel.annotation.RequestExcel;
import com.wemirr.framework.excel.annotation.ResponseExcel;
import com.wemirr.framework.excel.domain.ExcelReadFile;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.excel.handler.read.ValidateAnalysisEventListener;
import com.wemirr.framework.excel.web.resolver.ExcelReadResolver;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.demo.domain.resp.ExcelDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
@Tag(name = "Excel 测试", description = "Excel 测试")
public class ExcelController {


    @IgnoreAuthorize
    @GetMapping("/export1")
    @ResponseExcel(i18nHeader = true)
    @Operation(summary = "注解导出-带国际化")
    public List<ExcelDomain> export1() {
        ExcelDomain d1 = ExcelDomain.builder().name("中文").type("哈哈").build();
        ExcelDomain d2 = ExcelDomain.builder().name("英文").type("哈哈").build();
        return List.of(d1, d2);
    }

    @IgnoreAuthorize
    @GetMapping("/export2")
    @Operation(summary = "普通导出-带国际化")
    public ExcelWriteFile export2() {
        ExcelDomain d1 = ExcelDomain.builder().name("中文").type("哈哈").build();
        ExcelDomain d2 = ExcelDomain.builder().name("英文").type("哈哈").build();
        return ExcelWriteFile.builder().fileName("导出测试").i18nHeader(true).data(List.of(d1, d2)).build();
    }

    @IgnoreAuthorize
    @PostMapping("/import1")
    @Operation(summary = "普通导入-1")
    @Parameter(name = "file")
    public List<ExcelDomain> import1(@Parameter(hidden = true) @RequestExcel List<ExcelDomain> list) {

        return list;
    }


    @SneakyThrows
    @IgnoreAuthorize
    @PostMapping("/import2")
    @Operation(summary = "普通导入-2")
    public ValidateAnalysisEventListener<?> import2(@RequestParam MultipartFile file) {
        return ExcelReadResolver.read(ExcelReadFile.builder().inputStream(file.getInputStream()).build());
    }

}
