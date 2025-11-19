package com.wemirr.framework.excel.web.resolver;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.read.builder.ExcelReaderBuilder;
import com.wemirr.framework.excel.convert.InstantConverter;
import com.wemirr.framework.excel.convert.LocalDictConverter;
import com.wemirr.framework.excel.domain.ExcelReadFile;
import com.wemirr.framework.excel.handler.read.ValidateAnalysisEventListener;


/**
 * Excel读取解析程序
 *
 * @author Levin
 */
public class ExcelReadResolver {

    /**
     * 解析 ExcelReadFile 对象,并且返回校验的集合对象
     *
     * @param file file 对象
     * @return 解析结果
     */
    public static ValidateAnalysisEventListener<?> read(ExcelReadFile file) {
        ValidateAnalysisEventListener<?> readListener = file.getReadListener();
        ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getFile(), file.getExcelModelClass(), readListener);
        // 自动适配多类型
        if (file.getInputStream() != null) {
            readerBuilder.file(file.getInputStream());
        }
        if (file.getFile() != null) {
            readerBuilder.file(file.getFile());
        }
        if (file.getPathName() != null) {
            readerBuilder.file(file.getPathName());
        }
        readerBuilder
                .registerConverter(new LocalDictConverter())
                .registerConverter(new InstantConverter())
                .ignoreEmptyRow(file.getIgnoreEmptyRow())
                .headRowNumber(file.getHeadRowNumber())
                .password(file.getPassword())
                .sheet(file.getSheetNo(), file.getSheetName()).doRead();
        return readListener;
    }

}
