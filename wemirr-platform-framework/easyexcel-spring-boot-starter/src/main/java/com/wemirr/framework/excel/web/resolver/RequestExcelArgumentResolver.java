package com.wemirr.framework.excel.web.resolver;

import com.wemirr.framework.excel.annotation.RequestExcel;
import com.wemirr.framework.excel.domain.ExcelReadFile;
import com.wemirr.framework.excel.handler.read.DefaultValidateAnalysisEventListener;
import com.wemirr.framework.excel.handler.read.ValidateAnalysisEventListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.lang.NonNull;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.InputStream;
import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class RequestExcelArgumentResolver implements HandlerMethodArgumentResolver {

    private final ApplicationContext context;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestExcel.class) && parameter.getParameterType().isAssignableFrom(List.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestExcel excel = parameter.getParameterAnnotation(RequestExcel.class);
        Assert.state(excel != null, "@RequestExcel not found");
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(request != null, "HttpServletRequest is empty");
        InputStream inputStream;
        if (request instanceof MultipartRequest) {
            MultipartFile file = ((MultipartRequest) request).getFile(excel.fileName());
            Assert.state(file != null, "MultipartFile is empty");
            inputStream = file.getInputStream();
        } else {
            inputStream = request.getInputStream();
        }
        ValidateAnalysisEventListener<?> listener = resolverEventListener(parameter, inputStream, excel);
        // 校验失败的数据处理 交给 BindResult
        WebDataBinder dataBinder = binderFactory.createBinder(webRequest, listener.getValidateLines(), "excel");
        ModelMap model = mavContainer.getModel();
        model.put(BindingResult.MODEL_KEY_PREFIX + "excel", dataBinder.getBindingResult());
        return listener.getList();
    }

    /**
     * 解析对象并且返回监听结果
     *
     * @param parameter   请求参数
     * @param inputStream 文件流
     * @param excel       注解
     * @return 监听结果
     */
    public ValidateAnalysisEventListener<?> resolverEventListener(MethodParameter parameter, InputStream inputStream, RequestExcel excel) {
        // 获取目标类型
        Class<?> excelModelClass = ResolvableType.forMethodParameter(parameter).getGeneric(0).resolve();
        Class<? extends ValidateAnalysisEventListener<?>> listener = excel.readListener();
        ValidateAnalysisEventListener<?> readListener;
        if (DefaultValidateAnalysisEventListener.class.isAssignableFrom(listener)) {
            readListener = new DefaultValidateAnalysisEventListener(context.getBean(Validator.class));
        } else {
            readListener = BeanUtils.instantiateClass(listener);
        }
        return ExcelReadResolver.read(ExcelReadFile.builder()
                .inputStream(inputStream)
                .readListener(readListener)
                .excelModelClass(excelModelClass)
                .ignoreEmptyRow(excel.ignoreEmptyRow())
                .headRowNumber(excel.headRowNumber())
                .build());
    }
}
