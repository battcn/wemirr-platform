package com.wemirr.framework.boot.remote;


import com.wemirr.framework.commons.annotation.remote.Remote;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author Levin
 */
@Getter
@ToString
@EqualsAndHashCode
public class LoadKey {

    /**
     * 执行查询任务的类
     */
    private final String bean;
    /**
     * bean 类型
     */
    private final Class<?> beanClass;

    public LoadKey(Remote rf) {
        this.bean = rf.bean();
        this.beanClass = rf.beanClass();
    }
}
