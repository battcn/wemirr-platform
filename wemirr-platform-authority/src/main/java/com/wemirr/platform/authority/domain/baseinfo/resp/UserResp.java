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

package com.wemirr.platform.authority.domain.baseinfo.resp;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.wemirr.framework.excel.convert.DictConverter;
import com.wemirr.platform.authority.domain.baseinfo.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@ColumnWidth(30)
public class UserResp {

    @ExcelIgnore
    @Schema(description = "ID")
    private Long id;

    @ExcelProperty("账号")
    @Schema(description = "账号")
    private String username;

    @ExcelIgnore
    @Schema(description = "租户ID")
    private Long tenantId;

    @ExcelProperty("昵称")
    @Schema(description = "昵称")
    private String nickName;

    @ExcelProperty("身份证")
    @Schema(description = "身份证")
    private String idCard;

    @ExcelProperty("邮箱")
    @Schema(description = "邮箱")
    private String email;

    @ExcelProperty("手机号")
    @Schema(description = "手机号")
    private String mobile;

    @ExcelProperty(value = "性别", converter = DictConverter.class)
    @Schema(description = "性别")
    private Sex sex;

    @ExcelIgnore
    @Schema(description = "头像")
    private String avatar;

    @ExcelIgnore
    @Schema(description = "是否只读")
    private Boolean readonly;

    @ExcelIgnore
    @Schema(description = "状态")
    private Boolean status;

    @ExcelIgnore
    @Schema(description = "民族")
    private String nation;

    @ExcelIgnore
    @Schema(description = "学历")
    private String education;

    @ExcelProperty("生日")
    @Schema(description = "生日")
    private LocalDate birthday;

    @ExcelIgnore
    @Schema(description = "机构ID")
    private Long orgId;

    @ExcelIgnore
    @Schema(description = "岗位ID")
    private Long stationId;

    @ExcelIgnore
    @Schema(description = "职位状态")
    private String positionStatus;

    @ExcelProperty("描述")
    @Schema(description = "描述")
    private String description;

    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private Instant createdTime;

}
