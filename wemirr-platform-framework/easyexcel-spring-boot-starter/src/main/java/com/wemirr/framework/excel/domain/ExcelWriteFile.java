package com.wemirr.framework.excel.domain;


import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.WriteHandler;
import com.wemirr.framework.excel.head.HeadGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelWriteFile {

    /**
     * 文件名称
     */
    @Builder.Default
    private String fileName = "default";

    /**
     * 模板
     */
    private String template;
    /**
     * 文件类型
     */
    @Builder.Default
    private ExcelTypeEnum excelType = ExcelTypeEnum.XLSX;

    /**
     * 写类型
     */
    @Builder.Default
    private WriterType writerType = WriterType.WRITE;

    /**
     * 内存操作
     */
    @Builder.Default
    private Boolean inMemory = false;

    /**
     * 包含字段
     */
    private List<String> include;

    /**
     * 排除字段
     */
    private List<String> exclude;
    /**
     * 文件密码
     */
    private String password;
    /**
     * sheet 列表
     */
    @Builder.Default
    private List<SheetInfo> sheetList = List.of(new SheetInfo());

    /**
     * 拦截器，自定义样式等处理器
     */
    private Class<? extends WriteHandler>[] writeHandlers;

    /**
     * 转换器
     */
    private Class<? extends Converter<?>>[] converters;

    @Builder.Default
    private Boolean i18nHeader = false;

    @Builder.Default
    private Class<? extends HeadGenerator> headGenerator = HeadGenerator.class;

    /**
     * 导出数据
     */
    private Object data;

    public void addSheet(SheetInfo sheetInfo) {
        if (sheetList == null) {
            sheetList = new ArrayList<>();
        }
        sheetList.add(sheetInfo);
    }
}
