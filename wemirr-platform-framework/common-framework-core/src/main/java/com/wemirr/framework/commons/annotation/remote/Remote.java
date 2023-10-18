package com.wemirr.framework.commons.annotation.remote;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static cn.hutool.core.text.CharSequenceUtil.EMPTY;

/**
 * 在某个对象的字段上标记该注解，该字段的值将被主动注入
 * <p>
 * 如：@Remote(bean = "userApiImpl")
 * private String userId;
 *
 * <p>
 * 强烈建议：不要对象之间互相依赖
 * 如： User 想要注入 File， File也想注入User
 *
 * @author Levin
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
public @interface Remote {

    /**
     * 提供自动注入值的 查询类
     * 注意： 用 @Remote(bean = "xxxServiceImpl")时，要保证当前服务有 xxxServiceImpl 类. 没这个类就要用 xxxApi  (FeignClient)
     *
     * @return 查询类的Spring Name
     */
    String bean() default EMPTY;

    /**
     * 执行查询任务的类
     */
    Class<?> beanClass() default Object.class;


    /**
     * 规则
     *
     * @return 分隔符
     */
    String rule() default EMPTY;

    /**
     * 回显到那个字段
     *
     * @return 回显到那个字段
     */
    FieldRef[] fields() default {};

    /**
     * 自动注入值的类型， 用于强制转换（配置 ref 可以忽略）
     *
     * @return 待强转的类
     */
    Class<?> objectClass() default Object.class;


    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @interface FieldRef {
        String source() default EMPTY;

        String target();
    }


}
