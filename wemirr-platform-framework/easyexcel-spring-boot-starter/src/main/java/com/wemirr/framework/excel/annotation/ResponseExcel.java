package com.wemirr.framework.excel.annotation;


import cn.idev.excel.converters.Converter;
import cn.idev.excel.support.ExcelTypeEnum;
import cn.idev.excel.write.handler.WriteHandler;
import com.wemirr.framework.excel.domain.WriterType;
import com.wemirr.framework.excel.handler.head.HeadGenerator;

import java.lang.annotation.*;

/**
 * @author Levin
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExcel {


    /**
     * 文件名称
     */
    String fileName() default "default";

    /**
     * 文件类型
     */
    ExcelTypeEnum excelType() default ExcelTypeEnum.XLSX;

    /**
     * sheet 名称，支持多个
     *
     * @return String[]
     */
    Sheet[] sheets() default @Sheet(name = "sheet1");

    /**
     * 文件密码
     *
     * @return password
     */
    String password() default "";


    WriterType writerType() default WriterType.WRITE;

    /**
     * 内存操作
     *
     * @return false
     */
    boolean inMemory() default false;

    /**
     * excel 模板
     *
     * @return String
     */
    String template() default "";

    /**
     * + 包含字段
     *
     * @return String[]
     */
    String[] include() default {};

    /**
     * 排除字段
     *
     * @return String[]
     */
    String[] exclude() default {};

    /**
     * 拦截器，自定义样式等处理器
     *
     * @return WriteHandler[]
     */
    Class<? extends WriteHandler>[] writeHandlers() default {};

    /**
     * 转换器
     *
     * @return Converter[]
     */
    Class<? extends Converter<?>>[] converters() default {};

    /**
     * 自定义Excel头生成器
     *
     * @return HeadGenerator
     */
    Class<? extends HeadGenerator> headGenerator() default HeadGenerator.class;

    /**
     * excel 头信息国际化
     *
     * @return boolean
     */
    boolean i18nHeader() default false;

}

