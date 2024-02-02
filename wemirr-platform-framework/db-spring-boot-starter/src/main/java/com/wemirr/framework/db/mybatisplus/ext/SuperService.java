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

package com.wemirr.framework.db.mybatisplus.ext;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author battcn
 */
public interface SuperService<T> extends IService<T> {
    
    /**
     * 默认一次处理 500 条,有特殊需要自己for处理去
     * mysql rewriteBatchedStatements=true 可以优化批量SQL，然后使用 IService.saveBatch
     * oracle 默认最大 1000
     * pgsql 按照内容大小,基本上别超出1000条数据
     *
     * @param list list
     * @return 成功与否
     */
    boolean insertBatch(List<T> list);
    
    /**
     * 默认一次处理 500 条,有特殊需要自己for处理去
     * mysql rewriteBatchedStatements=true 可以优化批量SQL，然后使用 IService.saveBatch
     * oracle 默认最大 1000
     * pgsql 按照内容大小,基本上别超出1000条数据
     *
     * @param list list
     * @return 成功与否
     */
    boolean updateBatch(List<T> list);
    
}