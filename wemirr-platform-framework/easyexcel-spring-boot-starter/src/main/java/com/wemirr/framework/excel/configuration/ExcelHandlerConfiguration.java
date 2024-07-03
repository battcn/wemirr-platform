package com.wemirr.framework.excel.configuration;

import com.wemirr.framework.excel.handler.ISheetWriteHandler;
import com.wemirr.framework.excel.handler.head.I18nHeaderCellWriteHandler;
import com.wemirr.framework.excel.handler.write.SingleSheetWriteHandler;
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
    public ResponseExcelReturnValueHandler responseExcelReturnValueHandler(ApplicationContext context, List<ISheetWriteHandler> sheetWriteHandlers) {
        return new ResponseExcelReturnValueHandler(context, sheetWriteHandlers);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExcelWriteFileReturnValueHandler excelWriteFileReturnValueHandler(ApplicationContext context, List<ISheetWriteHandler> sheetWriteHandlers) {
        return new ExcelWriteFileReturnValueHandler(context, sheetWriteHandlers);
    }

}
