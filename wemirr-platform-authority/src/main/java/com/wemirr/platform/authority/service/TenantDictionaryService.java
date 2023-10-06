package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionary;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author Levin
 */
public interface TenantDictionaryService extends SuperService<TenantDictionary> {

    /**
     * 添加字典
     *
     * @param dictionary 字典信息
     */
    void addDictionary(TenantDictionary dictionary);

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
    void editDictionary(TenantDictionary dictionary);
}
