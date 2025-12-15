package com.wemirr.framework.log.diff.core.annotation;

import java.lang.annotation.*;

/**
 * 差异日志注解，用于标记需要记录操作日志的方法
 * <p>
 * 使用示例：
 * <pre>
 * {@code @DiffLog(group = "用户管理", tag = "编辑用户", businessKey = "{{#id}}",
 *         success = "更新用户信息 {_DIFF{#_newObj}}")}
 * public void modify(Long id, UserUpdateReq req) {
 *     // 业务代码中使用 DiffLogContext.putDiffItem(oldObj, newObj) 设置对比对象
 * }
 * </pre>
 *
 * @author muzhantong
 */
@Repeatable(DiffLogs.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DiffLog {

    /**
     * 业务分组，用于日志归类
     *
     * @return 业务组名称
     */
    String group() default "";

    /**
     * 业务标签，描述具体操作类型
     *
     * @return 操作标签，如：编辑用户、删除订单
     */
    String tag();

    /**
     * 业务唯一标识，支持 SpEL 表达式
     *
     * @return 业务标识，如：{{#id}}
     */
    String businessKey();

    /**
     * 操作成功时的日志模板，支持 SpEL 表达式
     *
     * @return 成功日志模板
     */
    String success();

    /**
     * 操作失败时的日志模板，支持 SpEL 表达式
     *
     * @return 失败日志模板
     */
    String fail() default "";

    /**
     * 记录日志的条件表达式，为 false 时不记录日志
     *
     * @return 条件表达式
     */
    String condition() default "";

    /**
     * 判断操作是否成功的条件表达式
     * <p>
     * 默认为空，表示方法不抛异常即为成功
     *
     * @return 成功条件表达式
     */
    String successCondition() default "";

}
