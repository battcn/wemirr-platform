package com.wemirr.framework.excel.configuration;

import com.wemirr.framework.excel.handler.ISheetWriteHandler;
import com.wemirr.framework.excel.handler.SingleSheetWriteHandler;
import com.wemirr.framework.excel.head.I18nHeaderCellWriteHandler;
import com.wemirr.framework.excel.web.handler.ExcelWriteFileReturnValueHandler;
import com.wemirr.framework.excel.web.handler.ResponseExcelReturnValueHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author Levin
 * @version 1.0
 */
@RequiredArgsConstructor
public class ExcelHandlerConfiguration {


    /**
     * 头部国际化处理
     *
     * @param messageSource messageSource
     * @return I18nHeaderCellWriteHandler
     */
    @Bean
    public I18nHeaderCellWriteHandler i18nHeaderCellWriteHandler(MessageSource messageSource) {
        return new I18nHeaderCellWriteHandler(messageSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public SingleSheetWriteHandler singleSheetWriteHandler(ApplicationContext context) {
        return new SingleSheetWriteHandler(context);
    }

    @Bean
    @ConditionalOnMissingBean
    public ResponseExcelReturnValueHandler responseExcelReturnValueHandler(List<ISheetWriteHandler> sheetWriteHandlers) {
        return new ResponseExcelReturnValueHandler(sheetWriteHandlers);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExcelWriteFileReturnValueHandler excelWriteFileReturnValueHandler(List<ISheetWriteHandler> sheetWriteHandlers) {
        return new ExcelWriteFileReturnValueHandler(sheetWriteHandlers);
    }

}
