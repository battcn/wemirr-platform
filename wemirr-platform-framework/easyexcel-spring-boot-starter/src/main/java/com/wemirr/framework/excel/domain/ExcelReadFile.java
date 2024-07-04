package com.wemirr.framework.excel.domain;


import com.wemirr.framework.excel.handler.read.DefaultValidateAnalysisEventListener;
import com.wemirr.framework.excel.handler.read.ValidateAnalysisEventListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelReadFile {

    /**
     * 文件路径
     * 如果 inputStream 和 file 都不为空，优先使用 file。
     */
    private String pathName;

    /**
     * 文件流
     */
    private InputStream inputStream;

    /**
     * 本地文件
     */
    private File file;


    /**
     * 加密密码
     */
    private String password;

    /**
     * excel 解析模型
     */
    private Class<?> excelModelClass;

    /**
     * 读取监听器
     */
    @Builder.Default
    private ValidateAnalysisEventListener<?> readListener = new DefaultValidateAnalysisEventListener(null);

    /**
     * 忽略空行
     */
    private Boolean ignoreEmptyRow;

    /**
     * 读取的标题行数
     */
    private Integer headRowNumber;

    /**
     * 从 0 行开始
     */
    private Integer sheetNo;


    /**
     * sheetName
     */
    private String sheetName;
}
