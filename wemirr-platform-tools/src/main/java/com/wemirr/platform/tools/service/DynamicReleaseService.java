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
package com.wemirr.platform.tools.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.tools.domain.entity.ExportExcelReq;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQueryDrag;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseLogTrackResp;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
public interface DynamicReleaseService<T> {
    
    /**
     * 获取 curdOptions
     *
     * @param model model
     * @return 查询结果
     */
    DynamicReleaseCurdOptionResp curdOptions(String model);
    
    /**
     * 保存
     *
     * @param model model
     * @param map   map
     */
    void save(String model, Map<String, Object> map);
    
    /**
     * 日志跟踪
     *
     * @param model model
     * @param id    id
     * @return 查询结果
     */
    List<DynamicReleaseLogTrackResp<T>> logTrack(String model, T id);
    
    /**
     * 推送信息
     *
     * @param model model
     * @param id    id
     */
    void pushTrack(String model, T id);
    
    /**
     * 根据ID删除
     *
     * @param model model
     * @param id    id
     */
    void deleteById(String model, T id);
    
    /**
     * 根据ID修改
     *
     * @param model model
     * @param id    id
     * @param map   map
     */
    void updateById(String model, T id, Map<String, Object> map);
    
    /**
     * 带条件分页查询
     *
     * @param model   model
     * @param request request
     * @param params  params
     * @return 查询结果
     */
    IPage<?> pages(String model, PageRequest request, Map<String, Object> params);
    
    /**
     * 带条件分页查询
     *
     * @param model model
     * @param drag  drag
     * @return 查询结果
     */
    IPage<?> pages(String model, DynamicReleaseQueryDrag drag);
    
    /**
     * 根据 model 获取表名
     *
     * @param model model
     * @return 结果
     */
    default String tableName(String model) {
        return model;
    }
    
    /**
     * 批量删除 keys
     *
     * @param model models
     * @param ids   ids
     */
    void batchDeleteByKeys(String model, List<T> ids);
    
    /**
     * 导出 excel
     *
     * @param model    model
     * @param req      req
     * @param response 响应结果
     */
    void exportExcel(String model, ExportExcelReq req, HttpServletResponse response);
    
}
