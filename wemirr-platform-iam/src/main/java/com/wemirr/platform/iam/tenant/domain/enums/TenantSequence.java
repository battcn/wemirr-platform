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

package com.wemirr.platform.iam.tenant.domain.enums;

import com.wemirr.framework.redis.plus.sequence.Sequence;
import lombok.RequiredArgsConstructor;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public enum TenantSequence implements Sequence {
    
    /**
     * 规费编号
     */
    PRODUCT_DEFINITION_NO("tenant:product-definition", "PD"),
    
    ;
    
    public final String key;
    public final String prefix;
    
    @Override
    public String key() {
        return key;
    }
    
    @Override
    public String prefix() {
        return prefix;
    }
}
