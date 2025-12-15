package com.wemirr.framework.db.mybatisplus.audit;

import java.lang.annotation.*;

/**
 * 审计字段标识注解
 * <p>
 * 用于标记需要审计的实体字段，支持自定义字段显示名称和忽略配置
 *
 * @author Levin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface AuditColumn {

    /**
     * 字段显示名称
     * <p>
     * 如果不配置或为空，默认提取 Swagger @Schema 注解的 description
     *
     * @return 字段显示名称
     */
    String label() default "";

    /**
     * 是否忽略该字段的审计
     *
     * @return true 忽略，false 不忽略（默认）
     */
    boolean ignore() default false;
}
