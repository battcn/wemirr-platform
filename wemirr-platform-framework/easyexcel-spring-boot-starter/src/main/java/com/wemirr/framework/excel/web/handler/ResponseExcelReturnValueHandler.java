package com.wemirr.framework.excel.web.handler;

import com.wemirr.framework.excel.annotation.ResponseExcel;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.excel.domain.SheetInfo;
import com.wemirr.framework.excel.handler.ISheetWriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 处理 Spring MVC 框架函数带有 ResponseExcel 注解的返回值，将其解析为文件下载
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class ResponseExcelReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final ApplicationContext context;
    private final List<ISheetWriteHandler> sheetWriteHandlerList;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(ResponseExcel.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        ResponseExcel excel = returnType.getMethodAnnotation(ResponseExcel.class);
        Assert.state(excel != null, "No @ResponseExcel");
        mavContainer.setRequestHandled(true);
        ExcelWriteFile writeFile = getExcelWriteFile(returnValue, excel);
        sheetWriteHandlerList.stream()
                .filter(handler -> handler.support(writeFile.getSheetList()))
                .findFirst()
                .ifPresent(handler -> handler.export(context, response, writeFile));
    }

    public ExcelWriteFile getExcelWriteFile(Object returnValue, ResponseExcel excel) {
        var sheetList = Arrays.stream(excel.sheets()).map(sheet -> SheetInfo.builder()
                .sheetNo(sheet.sheetNo()).name(sheet.name())
                .excludes(List.of(sheet.excludes())).includes(List.of(sheet.includes()))
                .headGenerateClass(sheet.headGenerateClass())
                .build()).toList();
        return ExcelWriteFile.builder().fileName(excel.fileName())
                .template(excel.template())
                .excelType(excel.excelType())
                .writerType(excel.writerType())
                .inMemory(excel.inMemory())
                .include(Optional.ofNullable(excel.include()).map(Arrays::asList).orElse(null))
                .exclude(Optional.ofNullable(excel.exclude()).map(Arrays::asList).orElse(null))
                .headGenerator(excel.headGenerator())
                .writeHandlers(excel.writeHandlers())
                .converters(excel.converters())
                .i18nHeader(excel.i18nHeader())
                .sheetList(sheetList)
                .data(returnValue)
                .build();
    }
}
