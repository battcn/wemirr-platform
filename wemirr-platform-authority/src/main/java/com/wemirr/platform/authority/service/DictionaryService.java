package com.wemirr.platform.authority.service;


import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.common.entity.Dictionary;
import com.wemirr.platform.authority.domain.common.entity.DictionaryItem;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author Levin
 * @since 2019-07-02
 */
public interface DictionaryService extends SuperService<Dictionary> {

    String SYS_DICT_CACHE_KEY_PREFIX = "sys:dict:-";

    /**
     * 添加字典
     *
     * @param dictionary 字典信息
     */
    void addDictionary(Dictionary dictionary);

    /**
     * 删除字典
     *
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 编辑字典
     *
     * @param dictionary 字典信息
     */
    void editDictionary(Dictionary dictionary);

    /**
     * 刷新缓存
     *
     * @param code code
     */
    @CacheInvalidate(name = SYS_DICT_CACHE_KEY_PREFIX, key = "#code")
    void refresh(String code);

    @Cached(name = SYS_DICT_CACHE_KEY_PREFIX, key = "#code", expire = 10, timeUnit = TimeUnit.MINUTES, cacheType = CacheType.LOCAL)
    @CacheRefresh(refresh = 5, timeUnit = TimeUnit.MINUTES)
    List<DictionaryItem> findItemByCode(String code);


}
