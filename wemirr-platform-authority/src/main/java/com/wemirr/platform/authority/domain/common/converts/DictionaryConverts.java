/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.platform.authority.domain.common.converts;

import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.authority.domain.common.entity.Dictionary;
import com.wemirr.platform.authority.domain.common.entity.DictionaryItem;
import com.wemirr.platform.authority.domain.common.req.DictionaryItemReq;
import com.wemirr.platform.authority.domain.common.req.DictionarySaveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author Levin
 */
@Slf4j
public class DictionaryConverts {
    
    public static final DictionaryConverts.DictionaryDto2PoConverts DICTIONARY_DTO_2_PO_CONVERTS = new DictionaryConverts.DictionaryDto2PoConverts();
    public static final DictionaryConverts.DictionaryItemDto2ItemPoConverts DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS = new DictionaryConverts.DictionaryItemDto2ItemPoConverts();


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
