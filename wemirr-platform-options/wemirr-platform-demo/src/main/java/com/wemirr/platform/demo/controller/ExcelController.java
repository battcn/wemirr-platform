package com.wemirr.platform.demo.controller;

import com.wemirr.framework.excel.annotation.ResponseExcel;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.demo.domain.resp.ExcelDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
