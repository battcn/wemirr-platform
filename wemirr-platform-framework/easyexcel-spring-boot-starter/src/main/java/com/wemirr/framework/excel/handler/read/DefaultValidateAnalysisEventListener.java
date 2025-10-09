package com.wemirr.framework.excel.handler.read;


import cn.idev.excel.context.AnalysisContext;
import com.wemirr.framework.excel.annotation.ExcelLine;
import com.wemirr.framework.excel.domain.ValidateLine;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 默认带校验的 Excel 监听解析器，如果不符合请自行实现自己的校验逻辑
 *
 * @author Levin
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class DefaultValidateAnalysisEventListener implements ValidateAnalysisEventListener<Object> {

    private final Validator validator;
    private final List<Object> list = new ArrayList<>();
    private final List<ValidateLine> validateLines = new ArrayList<>();

    private long line = 1L;

    private boolean validate(Object data) {
        Set<ConstraintViolation<Object>> violations = Optional.ofNullable(validator).orElse(Validation.buildDefaultValidatorFactory().getValidator()).validate(data);
        if (violations == null || violations.isEmpty()) {
            return true;
        }
        Set<String> messageSet = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
        validateLines.add(ValidateLine.builder().line(line).errors(messageSet).build());
        return false;
    }

    @Override
    public void invoke(Object data, AnalysisContext analysisContext) {
        line++;
        if (!validate(data)) {
            return;
        }
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ExcelLine.class) || field.getType() != Long.class) {
                continue;
            }
            try {
                field.setAccessible(true);
                field.set(data, line);
            } catch (IllegalAccessException e) {
                log.error("ExcelLine 设置异常,请检查类型是否匹配", e);
            }
        }
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("Excel read analysed");
    }
}