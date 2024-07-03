package com.wemirr.framework.excel.web.handler;

import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.excel.handler.ISheetWriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 处理 Spring MVC 框架 ExcelFile 类型的返回值，将其解析为文件下载
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class ExcelWriteFileReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final ApplicationContext context;
    private final List<ISheetWriteHandler> sheetWriteHandlerList;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Method m = returnType.getMethod();
        return m != null && ExcelWriteFile.class.equals(m.getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, @NonNull MethodParameter returnType, @NonNull ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        mavContainer.setRequestHandled(true);
        ExcelWriteFile writeFile = (ExcelWriteFile) returnValue;
        sheetWriteHandlerList.stream()
                .filter(handler -> handler.support(writeFile.getSheetList()))
                .findFirst()
                .ifPresent(handler -> handler.export(context, response, writeFile));
    }


}