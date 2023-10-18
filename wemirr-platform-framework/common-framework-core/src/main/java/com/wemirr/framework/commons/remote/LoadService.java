package com.wemirr.framework.commons.remote;


import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 加载数据
 * <p>
 * 只保留一个方法，若一个表，想要有多重回显场景，可以新建多个实现类，返回不一样的Map
 *
 * @author Levin
 */
public interface LoadService<KEY extends Serializable, VALUE> {

    /**
     * 根据id查询待回显参数
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return Map<Serializable, Object>
     */
    Map<KEY, VALUE> findByIds(Set<KEY> ids);

}
