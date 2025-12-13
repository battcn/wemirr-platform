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

package com.wemirr.platform.iam.system.domain.dto.resp;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.ColumnWidth;
import com.wemirr.framework.boot.remote.dict.DictLoadService;
import com.wemirr.framework.commons.annotation.remote.Remote;
import com.wemirr.framework.excel.convert.InstantConverter;
import com.wemirr.framework.excel.convert.LocalDictConverter;
import com.wemirr.platform.iam.system.domain.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@ColumnWidth(30)
public class UserPageResp {

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

    @ExcelProperty(value = "性别", converter = LocalDictConverter.class)
    @Schema(description = "性别")
    private Sex sex;

    @ExcelIgnore
//    @Remote(beanClass = OssFileFeign.class, fields = {@Remote.FieldRef(source = "previewUrl", target = "ossPreviewUrl")}, split = true)
    @Schema(description = "头像")
    private String avatar;

//    @Schema(description = "预览地址")
//    private String ossPreviewUrl;

    @ExcelIgnore
    @Schema(description = "是否只读")
    private Boolean readonly;

    @ExcelIgnore
    @Schema(description = "状态")
    private Boolean status;

    @ExcelIgnore
    @Schema(description = "民族")
    @Remote(beanClass = DictLoadService.class, tag = "NATION", fields = {@Remote.FieldRef(target = "nationName")})
    private String nation;

    @Schema(description = "民族")
    @ExcelProperty("民族")
    private String nationName;

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
    private Long positionId;

    @ExcelIgnore
    @Schema(description = "职位状态")
    private String positionStatus;

    @ExcelProperty("描述")
    @Schema(description = "描述")
    private String description;

    @ExcelProperty(value = "创建时间", converter = InstantConverter.class)
    @Schema(description = "创建时间")
    private Instant createTime;

}
