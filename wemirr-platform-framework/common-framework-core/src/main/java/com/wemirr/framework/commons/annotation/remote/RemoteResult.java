package com.wemirr.framework.commons.annotation.remote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将该注解标记在service方法上， 调用该方法后，返回值中标记了 @Remote 注解的字段将会自动注入属性
 * <p>
 * 注意，该方法不能写在 DAO/Mapper 的方法上。
 *
 * @author Levin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface RemoteResult {


}
