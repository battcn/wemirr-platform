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

package com.wemirr.platform.authority.repository.common;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.platform.authority.domain.common.entity.I18nData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface I18nDataMapper extends SuperMapper<I18nData> {
    
    /**
     * 加载 i18n 数据
     *
     * @return 查询结果
     */
    List<I18nMessage> loadI18nMessage();
    
}
