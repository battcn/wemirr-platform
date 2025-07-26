package com.wemirr.framework.excel.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = EasyExcelProperties.PREFIX)
public class EasyExcelProperties {

    public static final String PREFIX = "extend.boot.excel";


    /**
     * 模板路径
     */
    private String templatePath = "template";

    /**
     * 自动列宽
     */
    private Boolean autoColumnWidth = false;


}
