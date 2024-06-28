package com.wemirr.framework.pdf.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * PDF 实体
 *
 * @author Levin
 */
@Builder
@Data
public class PDFDomain {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 模板名字
     */
    private String templateName;

    /**
     * 水印内容
     */
    private String watermark;

    /**
     * 模板里面的数据模型
     */
    private Map<String, Object> model;

}
