package com.wemirr.platform.authority.service.impl;


import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.DictionaryItem;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionary;
import com.wemirr.platform.authority.repository.common.DictionaryItemMapper;
import com.wemirr.platform.authority.repository.tenant.TenantDictionaryMapper;
import com.wemirr.platform.authority.service.TenantDictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantDictionaryServiceImpl extends SuperServiceImpl<TenantDictionaryMapper, TenantDictionary> implements TenantDictionaryService {

    private final DictionaryItemMapper dictionaryItemMapper;

    @Override
    public void addDictionary(TenantDictionary dictionary) {
        if (dictionary == null) {
            throw CheckedException.notFound("字典内容不能为空");
        }
        final Long count = this.baseMapper.selectCount(Wraps.<TenantDictionary>lbQ().eq(TenantDictionary::getCode, dictionary.getCode()));
        if (count != 0 && count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        this.baseMapper.insert(dictionary);
    }

    @DSTransactional
    @Override
    public void deleteById(Long id) {
        final TenantDictionary dictionary = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("字典不存在"));
        if (dictionary.getReadonly()) {
            throw CheckedException.notFound("内置数据无法删除");
        }
        this.baseMapper.deleteById(id);
        this.dictionaryItemMapper.delete(Wraps.<DictionaryItem>lbQ().eq(DictionaryItem::getDictionaryId, id));
    }

    @DSTransactional
    @Override
    public void editDictionary(TenantDictionary dictionary) {
        final TenantDictionary record = Optional.ofNullable(this.baseMapper.selectById(dictionary.getId()))
                .orElseThrow(() -> CheckedException.notFound("字典不存在"));
        if (record.getReadonly()) {
            throw CheckedException.notFound("内置数据无法删除");
        }
        final Long count = this.baseMapper.selectCount(Wraps.<TenantDictionary>lbQ().ne(TenantDictionary::getId, dictionary.getId())
                .eq(TenantDictionary::getCode, dictionary.getCode()));
        if (count != 0 && count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        this.baseMapper.updateById(dictionary);
        this.dictionaryItemMapper.update(DictionaryItem.builder()
                .status(dictionary.getStatus())
                .dictionaryCode(dictionary.getCode())
                .build(), Wraps.<DictionaryItem>lbQ()
                .eq(DictionaryItem::getDictionaryId, dictionary.getId()));
    }


}