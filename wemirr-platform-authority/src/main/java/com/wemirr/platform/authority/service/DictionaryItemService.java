package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.entity.common.DictionaryItem;

/**
 * <p>
 * 业务接口
 * 字典项
 * </p>
 *
 * @author Levin
 * @since 2019-07-02
 */
public interface DictionaryItemService extends SuperService<DictionaryItem> {


    /**
     * 添加字典项
     *
     * @param dictionaryId 字典ID
     * @param item         字典项
     */
    void addDictionaryItem(Long dictionaryId, DictionaryItem item);

    /**
     * 修改字典项
     *
     * @param dictionaryId 字典ID
     * @param item         字典项
     */
    void editDictionaryItem(Long dictionaryId, DictionaryItem item);
}
