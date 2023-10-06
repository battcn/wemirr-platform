package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionaryItem;

/**
 * <p>
 * 业务接口
 * 字典项
 * </p>
 *
 * @author Levin
 */
public interface TenantDictionaryItemService extends SuperService<TenantDictionaryItem> {


    /**
     * 添加字典项
     *
     * @param dictionaryId 字典ID
     * @param item         字典项
     */
    void addDictionaryItem(Long dictionaryId, TenantDictionaryItem item);

    /**
     * 修改字典项
     *
     * @param dictionaryId 字典ID
     * @param item         字典项
     */
    void editDictionaryItem(Long dictionaryId, TenantDictionaryItem item);
}
