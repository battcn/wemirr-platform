package com.wemirr.framework.excel.domain;


import cn.idev.excel.converters.Converter;
import cn.idev.excel.support.ExcelTypeEnum;
import cn.idev.excel.write.handler.WriteHandler;
import com.wemirr.framework.excel.handler.head.HeadGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "文件名不能为空")
    private String fileName = "default";

    /**
     * 模板
     */
    private String template;
    /**
     * 文件类型
     */
    @Builder.Default
    @NotNull(message = "文件类型不能为空")
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
    @NotEmpty(message = "Sheet 列表不能为空")
    @Size(min = 1, max = 10, message = "Sheet 列表必须在{min} - {max} 之间")
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

    /**
     * 头生成器
     */
    @Builder.Default
    @NotNull(message = "HeadGenerator 不能为空")
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
