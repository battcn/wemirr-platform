package com.wemirr.framework.db.mybatisplus.audit;

import java.lang.annotation.*;

/**
 * 表字段标识
 *
 * @author hubin sjy tantan
 * @since 2016-09-09
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface AuditColumn {

    /**
     * 字段名,如果不配置或者为空默认提取 swagger v3 注释
     *
     * @return 名称
     */
    String label() default "";

    /**
     * 是否忽略
     *
     * @return 默认都不忽略
     */
    boolean ignore() default false;


}
