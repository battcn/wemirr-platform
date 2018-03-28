package com.battcn.framework.redis.cache;

import com.battcn.framework.redis.exception.CacheException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 缓存组件-缓存接口
 *
 * @author 许泓林
 */
public interface CacheService {

    /**
     * 计数器
     *
     * @param key 缓存key
     * @param val 自增大小
     * @return 自增后的数
     * @throws CacheException CacheException CacheException
     */
    Long incrBy(String key, long val) throws CacheException;

    /**
     * 计数器
     *
     * @param key 缓存key
     * @return 自增后的数
     * @throws CacheException CacheException CacheException
     */
    Long incr(String key) throws CacheException;

    /**
     * 带过期时间的计数器，该过期时间的设置只有计数器的值为1的时候生效,请一定要注意
     *
     * @param key      缓存key
     * @param val      自增大小
     * @param interval 过期时间
     * @param unit     时间类型
     * @return 自增后的数
     * @throws CacheException CacheException
     */
    Long incr(String key, long val, int interval, TimeUnit unit) throws CacheException;

    /**
     * 设置对象过期时间
     *
     * @param key      缓存key
     * @param interval 过期时间，单位秒
     * @throws CacheException CacheException
     */
    void expire(String key, int interval) throws CacheException;

    /**
     * 设置对象过期时间
     *
     * @param key      缓存key
     * @param interval 过期时间
     * @param unit     时间单位
     * @throws CacheException CacheException
     */
    void expire(String key, int interval, TimeUnit unit) throws CacheException;

    /**
     * 添加对象到缓存
     *
     * @param key     缓存key
     * @param value   缓存对象
     * @param minutes 过期时间
     * @throws CacheException CacheException
     */
    void add(String key, Serializable value, int minutes) throws CacheException;

    /**
     * 添加对象到缓存
     *
     * @param key      缓存key
     * @param value    缓存对象
     * @param interval 过期时间
     * @param unit     时间单位
     * @throws CacheException CacheException
     */
    void add(String key, Serializable value, int interval, TimeUnit unit) throws CacheException;

    /**
     * 在Redis键中设置指定的字符串值，并返回其旧值。同时设置过期时间
     *
     * @param key      缓存key
     * @param value    缓存对象
     * @param interval 过期时间
     * @param unit     时间单位
     * @return 序列化的对象
     * @throws CacheException CacheException
     */
    Serializable getAndSet(String key, Serializable value, int interval, TimeUnit unit) throws CacheException;

    /**
     * 添加对象到缓存
     *
     * @param key   缓存key
     * @param value 缓存对象
     * @throws CacheException CacheException
     */
    void add(String key, Serializable value) throws CacheException;

    /**
     * 添加集合到缓存
     *
     * @param key    缓存key
     * @param values 缓存对象
     * @throws CacheException CacheException
     */
    void addList(String key, Collection<Serializable> values) throws CacheException;

    /**
     * 添加集合到缓存
     *
     * @param key     缓存key
     * @param values  缓存对象
     * @param minutes 过期时间
     * @throws CacheException CacheException
     */
    void addList(String key, Collection<Serializable> values, int minutes) throws CacheException;

    /**
     * 添加Map到缓存
     *
     * @param key      缓存key
     * @param valueMap 缓存对象
     * @throws CacheException CacheException
     */
    void addMap(String key, Map<Object, Object> valueMap) throws CacheException;

    /**
     * 添加Map到缓存
     *
     * @param key      缓存key
     * @param valueMap 缓存对象
     * @param minutes  过期时间（单位：分钟）
     * @throws CacheException CacheException
     */
    void addMap(String key, Map<Object, Object> valueMap, int minutes) throws CacheException;

    /**
     * 获取Map从缓存
     *
     * @param key 缓存key
     * @return Map<Object,Object>
     * @throws CacheException CacheException
     */
    Map<Object, Object> getMap(String key) throws CacheException;

    /**
     * 添加对象到缓存集合中
     *
     * @param key   缓存key
     * @param value 缓存对象
     * @throws CacheException CacheException
     */
    void addOneToList(String key, Serializable value) throws CacheException;

    /**
     * 获取缓存对象
     *
     * @param key 缓存key
     * @return Serializable
     * @throws CacheException CacheException
     */
    Serializable get(String key) throws CacheException;

    /**
     * 获取缓存列表
     *
     * @param key 缓存key
     * @return List<Serializable>
     * @throws CacheException CacheException
     */
    List<Serializable> getList(String key) throws CacheException;

    /**
     * 获取列表
     *
     * @param key 缓存key
     * @return Serializable
     * @throws CacheException CacheException
     */
    Serializable getListFirstOne(String key) throws CacheException;

    /**
     * 获得缓存数量
     *
     * @param keyPrefix key前缀
     * @return 统计结果
     * @throws CacheException CacheException
     */
    long getCountLike(String keyPrefix) throws CacheException;

    /**
     * 删除缓存
     *
     * @param key 缓存key
     * @throws CacheException CacheException
     */
    void remove(String key) throws CacheException;

    /**
     * 模糊删除缓存
     *
     * @param keyPrefix 缓存前缀
     * @throws CacheException CacheException
     */
    void removeLike(String keyPrefix) throws CacheException;

    /**
     * 获取列表元素个数
     *
     * @param key 缓存key
     * @return 集合大小
     * @throws CacheException CacheException
     */
    Long getListSize(String key) throws CacheException;

    /**
     * 从缓存列表中获取指定个数的元素(从左到右),并将这些元素从缓存列表中移除
     *
     * @param key    缓存key
     * @param offset 偏移
     * @return 移出的集合
     * @throws CacheException CacheException
     */
    List<Serializable> popList(String key, int offset) throws CacheException;

    /**
     * 缓存指定key,若给定的 key 已经存在，则 SETNX 不做任何动作。
     *
     * @param key      缓存key
     * @param value    缓存对象
     * @param interval 时间数,如果为0,则不过期
     * @param timeUnit 时间单位
     */
    void setNX(String key, Serializable value, int interval, TimeUnit timeUnit) throws CacheException;

    /**
     * 保存hash数据
     *
     * @param key      缓存key
     * @param field    缓存的字段
     * @param value    缓存的对象
     * @param interval 时间数,如果为0,则不过期
     * @param timeUnit 时间单位
     */
    void hset(String key, String field, Serializable value, int interval, TimeUnit timeUnit) throws CacheException;

    /**
     * 保存hash数据
     *
     * @param key   缓存key
     * @param field 缓存的字段
     * @param value 缓存的对象
     */
    void hset(String key, String field, Serializable value) throws CacheException;

    /**
     * 获取hash数据
     *
     * @param key   hashKey
     * @param field hashField
     * @return Object
     */
    Object hget(String key, String field) throws CacheException;


    /**
     * 在集合最后插入一条记录
     *
     * @param key    缓存key
     * @param values 缓存对象
     * @throws CacheException CacheException
     */
    void addListRight(String key, Collection<Serializable> values) throws CacheException;

    /**
     * 在集合最后插入一条记录,带过期时间
     *
     * @param key     缓存key
     * @param values  缓存对象
     * @param minutes 过期时间（单位：分）
     */
    void addListRight(String key, Collection<Serializable> values, int minutes);

    /**
     * FIFO 策略,获取一条缓存数据
     *
     * @param key   缓存key
     * @param value 缓存对象
     * @throws CacheException CacheException
     */
    void addOneToListRight(String key, Serializable value) throws CacheException;

    /**
     * 随机获取一条记录
     *
     * @param key 缓存key
     * @return 随机对象
     * @throws CacheException CacheException
     */
    Serializable getListRandomOne(String key) throws CacheException;


}
