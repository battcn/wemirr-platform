package com.wemirr.platform.authority.domain.converts;

import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.authority.domain.entity.common.Dictionary;
import com.wemirr.platform.authority.domain.entity.common.DictionaryItem;
import com.wemirr.platform.authority.domain.req.DictionaryItemReq;
import com.wemirr.platform.authority.domain.req.DictionarySaveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author Levin
 */
@Slf4j
public class DictionaryConverts {

    public static final DictionaryConverts.DictionaryDto2PoConverts DICTIONARY_DTO_2_PO_CONVERTS = new DictionaryConverts.DictionaryDto2PoConverts();

    public static class DictionaryDto2PoConverts implements BasePageConverts<DictionarySaveReq, Dictionary> {
        @Override
        public Dictionary convert(DictionarySaveReq source) {

            if (source == null) {
                return null;
            }
            Dictionary target = new Dictionary();
            BeanUtils.copyProperties(source, target);
            return target;
        }

        @Override
        public Dictionary convert(DictionarySaveReq source, Long id) {
            if (source == null) {
                return null;
            }
            Dictionary target = new Dictionary();
            BeanUtils.copyProperties(source, target);
            target.setId(id);
            return target;
        }
    }

    public static final DictionaryConverts.DictionaryItemDto2ItemPoConverts DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS = new DictionaryConverts.DictionaryItemDto2ItemPoConverts();

    public static class DictionaryItemDto2ItemPoConverts implements BasePageConverts<DictionaryItemReq, DictionaryItem> {

        @Override
        public DictionaryItem convert(DictionaryItemReq source) {
            if (source == null) {
                return null;
            }
            DictionaryItem target = new DictionaryItem();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }

}
