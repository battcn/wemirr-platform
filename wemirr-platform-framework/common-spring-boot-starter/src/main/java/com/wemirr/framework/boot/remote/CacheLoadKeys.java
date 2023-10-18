package com.wemirr.framework.boot.remote;


import com.wemirr.framework.commons.annotation.remote.Remote;
import com.wemirr.framework.commons.remote.LoadService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author Levin
 */
@Getter
@Slf4j
@ToString
@EqualsAndHashCode
public class CacheLoadKeys {

    /**
     * 执行查询任务的类
     */
    private final String beanName;
    private final Class<?> beanClass;

    /**
     * 动态查询值
     */
    private Set<Serializable> keys = new HashSet<>();
    private LoadService<Serializable, Object> loadService;

    public CacheLoadKeys(Remote rf) {
        this.beanName = rf.beanName();
        this.beanClass = rf.beanClass();
    }

    public CacheLoadKeys(LoadKey lk, LoadService<Serializable, Object> loadService, Set<Serializable> keys) {
        this.beanName = lk.getBeanName();
        this.beanClass = lk.getBeanClass();
        this.loadService = loadService;
        this.keys = keys;
    }


    /**
     * 加载数据
     *
     * @return 查询指定接口后得到的值
     */
    public Map<Serializable, Object> loadMap() {
        return loadService.findByIds(keys);
    }
}
