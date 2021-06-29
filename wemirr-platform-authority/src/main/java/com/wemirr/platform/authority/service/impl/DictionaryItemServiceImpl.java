package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.common.Dictionary;
import com.wemirr.platform.authority.domain.entity.common.DictionaryItem;
import com.wemirr.platform.authority.mapper.DictionaryItemMapper;
import com.wemirr.platform.authority.mapper.DictionaryMapper;
import com.wemirr.platform.authority.service.DictionaryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 业务实现类
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryItemServiceImpl extends SuperServiceImpl<DictionaryItemMapper, DictionaryItem> implements DictionaryItemService {

    private final DictionaryMapper dictionaryMapper;


    @Override
    public void addDictionaryItem(Long dictionaryId, DictionaryItem item) {
        final int count = this.baseMapper.selectCount(Wraps.<DictionaryItem>lbQ()
                .eq(DictionaryItem::getValue, item.getValue())
                .eq(DictionaryItem::getDictionaryId, dictionaryId));
        if (count > 0) {
            throw CheckedException.badRequest("编码已存在");
        }
        final Dictionary dictionary = Optional.ofNullable(this.dictionaryMapper.selectById(dictionaryId))
                .orElseThrow(() -> CheckedException.notFound("码表不存在"));
        item.setDictionaryId(dictionaryId);
        item.setDictionaryCode(dictionary.getCode());
        this.baseMapper.insert(item);
    }

    @Override
    public void editDictionaryItem(DictionaryItem item) {
        final int count = this.baseMapper.selectCount(Wraps.<DictionaryItem>lbQ()
                .eq(DictionaryItem::getValue, item.getValue())
                .ne(DictionaryItem::getId, item.getId()));
        if (count > 0) {
            throw CheckedException.badRequest("编码已存在");
        }
        this.baseMapper.updateById(item);
    }
}
