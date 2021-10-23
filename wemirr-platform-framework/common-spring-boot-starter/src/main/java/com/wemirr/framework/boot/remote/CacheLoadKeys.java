package com.wemirr.framework.boot.remote;


import com.google.common.base.Objects;
import com.wemirr.framework.commons.annotation.remote.Remote;
import com.wemirr.framework.commons.remote.LoadService;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@Slf4j
@ToString
@NoArgsConstructor
public class CacheLoadKeys {

    /**
     * 执行查询任务的类
     */
    private String bean;

    /**
     * 动态查询值
     */
    private Set<Serializable> keys = new HashSet<>();
    private LoadService<Serializable> loadService;

    public CacheLoadKeys(Remote rf) {
        this.bean = rf.bean();
    }

    public CacheLoadKeys(LoadKey lk, LoadService<Serializable> loadService, Set<Serializable> keys) {
        this.bean = lk.getBean();
        this.loadService = loadService;
        this.keys = keys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CacheLoadKeys that = (CacheLoadKeys) o;
        boolean apiMethod = Objects.equal(bean, that.bean);
        boolean isEqualsKeys = keys.size() == that.keys.size() && keys.containsAll(that.keys);
        return apiMethod && isEqualsKeys;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bean, keys);
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
