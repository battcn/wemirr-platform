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

package com.wemirr.framework.db.mybatisplus.handler.type;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数组字符串互转
 *
 * @author Levin
 */
@MappedTypes(value = {Long[].class})
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class LongArrayTypeHandler extends BaseTypeHandler<Long[]> {
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, ArrayUtil.join(parameter, StrUtil.COMMA));
    }
    
    @Override
    @SneakyThrows
    public Long[] getNullableResult(ResultSet rs, String columnName) {
        String reString = rs.getString(columnName);
        return Convert.toLongArray(reString);
    }
    
    @Override
    @SneakyThrows
    public Long[] getNullableResult(ResultSet rs, int columnIndex) {
        String reString = rs.getString(columnIndex);
        return Convert.toLongArray(reString);
    }
    
    @Override
    @SneakyThrows
    public Long[] getNullableResult(CallableStatement cs, int columnIndex) {
        String reString = cs.getString(columnIndex);
        return Convert.toLongArray(reString);
    }
    
}
