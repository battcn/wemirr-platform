package com.wemirr.framework.excel.annotation;

import com.wemirr.framework.excel.handler.read.DefaultValidateAnalysisEventListener;
import com.wemirr.framework.excel.handler.read.ValidateAnalysisEventListener;

import java.lang.annotation.*;

/**
 * @author Levin
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestExcel {

    /**
     * 前端上传字段名称 file
     */
    String fileName() default "file";


    /**
     * 文件密码
     *
     * @return 密码
     */
    String password() default "";

    /**
     * 读取的监听器类
     *
     * @return readListener
     */
    Class<? extends ValidateAnalysisEventListener<?>> readListener() default DefaultValidateAnalysisEventListener.class;

    /**
     * 是否跳过空行
     *
     * @return 默认跳过
     */
    boolean ignoreEmptyRow() default false;

    /**
     * 读取的标题行数
     *
     * @return 行号
     */
    int headRowNumber() default 1;


    /**
     * 0 开始
     *
     * @return 0
     */
    int sheetNo() default 0;

    /**
     * sheetName
     *
     * @return sheetName
     */
    String sheetName() default "";

}
